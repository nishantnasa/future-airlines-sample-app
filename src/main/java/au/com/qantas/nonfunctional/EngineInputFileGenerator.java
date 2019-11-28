package au.com.future-airlines.nonfunctional;

import au.com.future-airlines.utils.CommonMethods;
import au.com.future-airlines.utils.DateFunctions;
import au.com.future-airlines.utils.PropsUtil;
import net.thucydides.core.Thucydides;

import java.io.*;
import java.util.*;


public class EngineInputFileGenerator {


    private static CommonMethods _commonMethods ;
    private static Long eventKey = 1000100000l;
    private static Integer countOfConversionEventsPerMember = 0;
    private EngineFileWriter _engineFileWriter;
    private FileWriter writer = null;
    private List<Offer> allOffersPerMember = new ArrayList<>();
    private DateFunctions dtFunctions = new DateFunctions();
    private EngineOutputFileGenerator _engineOutputFileGenerator;
    private AiOutputFileGenerator _aiOutputFileGenerator;
    private future-airlinesPropensityScoresFileGenerator _future-airlinesPropensityScoresFileGenerator;
    private Map<String, Object> _properties;

    public EngineInputFileGenerator() {
        _properties = PropsUtil.loadProperties();
        _engineFileWriter = new EngineFileWriter();
        _engineFileWriter.init();
        _engineOutputFileGenerator = new EngineOutputFileGenerator();
        _aiOutputFileGenerator = new AiOutputFileGenerator();
        _future-airlinesPropensityScoresFileGenerator = new future-airlinesPropensityScoresFileGenerator();
        _commonMethods = new CommonMethods();
    }

    public List<String> readExistingOfferIds() {
        List<String> offersList = new ArrayList<String>();
        String path = System.getProperty("user.dir");
        File file = new File(path + "/src/main/resources/Test_Offers.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                offersList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thucydides.getCurrentSession().put("existingOffersList", offersList);
        return offersList;
    }

    public void createEngineInputFiles(List<Offer> offersList, Integer numberOfMembers, Boolean isEngineCodeNew) {
        List<Member> members = readTestMembers(numberOfMembers);
        Eligibility eligibility = null;
        OfferHistory offerHistory = null;
        AiOutput aiOutput = null;
        MemberPropensityScores memberPropensityScores = null;
        Map<Integer, Integer> mapOfSendsPerWeek;
        Long leadKey = 100001000100000l;
        try {
            _engineFileWriter.writeHeaders();
            String offers_allocation_per_member = (String) _properties.get("Offer.per.Member");
            for (Member member : members) {

                mapOfSendsPerWeek = new HashMap<>();
                generateExperimentDesignOutput(member, _engineFileWriter.getExperimentDesignFileWriter());
                _commonMethods.writeToFile(_engineFileWriter.getConversionModelFileWriter(), member.getPrimPartyId() + "|" + _commonMethods.getRandomValueFromLIstOfString(Arrays.asList("conversion,click".split(","))));
                _commonMethods.writeToFile(_engineFileWriter.getTimeOfDayEmailSendInputFileWriter(), member.getPrimPartyId() + ",10,pers");
                allOffersPerMember = _commonMethods.getOffersSubset(offersList, Integer.parseInt(offers_allocation_per_member));
                countOfConversionEventsPerMember = 0;
                for (Offer offer : allOffersPerMember) {
                    eligibility = createEligibility( leadKey, member, offer);
                    generateOfferHistoryForDaySpecific(leadKey, member, offer, mapOfSendsPerWeek);
                    //ContextManger
                    generateContextManagerOutputFiles(eligibility, offer);
                    //AI
                    generateAIOutputFilesWithClickAndConversionModels(aiOutput,member,offer);
                    leadKey = ++leadKey;

                }
                if (Integer.parseInt(member.getFrequestFlyerId()) % 100000 == 0) {
                    System.out.println("Executing for member Id range.... !!!" + member.getFrequestFlyerId());
                    _engineFileWriter.flushEngineFileWriters();
                }
            }
            _engineOutputFileGenerator.addFooterToWebContextManagerOutputFile(_engineFileWriter.getContextMgrWebOutputFileWriters(), _engineFileWriter.getContextMgrWebOutputWriter());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _engineFileWriter.closeEngineFilewriters();
        }
    }

    private List<Member> readTestMembers(Integer numberOfMembers) {
        List<Member> membersList = new LinkedList<Member>();
        Integer startingNumber = 10000000;
        if (numberOfMembers == 0) {
            getMembersFromCsv(membersList);
        } else {
            for (int i = 0; i < numberOfMembers; i++) {
                Member member = new Member();
                member.setPrimPartyId(startingNumber);
                member.setFrequestFlyerId(startingNumber.toString());
                membersList.add(member);
                member = null;
                startingNumber += 1;
            }
        }
        Thucydides.getCurrentSession().put("membersList", membersList);
        return membersList;
    }

    private void generateExperimentDesignOutput(Member member, FileWriter experimentDesignFileWriter) {
        List<ExperimentDesign> experimentDesignPerCustomer = getExperimentDesignPerCustomer(member.getPrimPartyId());
        for (ExperimentDesign experimentDesign : experimentDesignPerCustomer) {
            _commonMethods.writeToFile(experimentDesignFileWriter, experimentDesign.toString());
        }

    }

    private Eligibility createEligibility(Long leadKey, Member member, Offer offer) {
        Eligibility eligibility = getEligibility(leadKey, member, offer);
        FileWriter eligibilityFileWriter = _engineFileWriter.getEligibilityFileWriter();
        if (_engineFileWriter.isSplitSwitch()) {
            if (member.getPrimPartyId() < 11000000) {
                eligibilityFileWriter = _engineFileWriter.getMapOfEligibilityFileWriters().get(0);
            } else {
                eligibilityFileWriter = _engineFileWriter.getMapOfEligibilityFileWriters().get((member.getPrimPartyId() - 10000000) / 1000000);
            }
        }
        _commonMethods.writeToFile(eligibilityFileWriter, eligibility.getNewEligibilityString());
        return eligibility;
    }

    private Map<Integer, Integer> generateOfferHistoryForDaySpecific( Long leadKey, Member member, Offer offer, Map<Integer, Integer> mapOfSendsPerWeek) {


        OfferHistory offerHistory = new OfferHistory();
        Integer sendsPerWeekCount = 0;
//        if (member.getPrimPartyId() <= 10450000) {
        if (offer.getOFFER_CATEGORY().equalsIgnoreCase("INITIAL")) {
            List<String> options = Arrays.asList("false", "true", "false");
            for (Integer numberOfWeeks : _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().keySet()) {
                if (numberOfWeeks > 12) {
                    if (mapOfSendsPerWeek.get(numberOfWeeks) == null || mapOfSendsPerWeek.get(numberOfWeeks) < 6) {
                        if (_commonMethods.getRandomValueFromLIstOfString(options).equalsIgnoreCase("true")) {
                            offerHistory = new OfferHistory();
                            offerHistory = createOfferHistoryDaySpecific(member.getPrimPartyId(), leadKey, offer.getACTION_ID(), _engineFileWriter.getMapOfWriters(), offerHistory, _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().get(numberOfWeeks));
                            sendsPerWeekCount = mapOfSendsPerWeek.get(numberOfWeeks) == null ? 0 : mapOfSendsPerWeek.get(numberOfWeeks);
                            mapOfSendsPerWeek.put(numberOfWeeks, sendsPerWeekCount + 1);
                        }
                    }
                }
            }
        } else if (offer.getOFFER_CATEGORY().equalsIgnoreCase("INITIAL_PARENT")) {
            for (Integer numberOfWeeks : _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().keySet()) {
                if (numberOfWeeks > 14) {
                    if (mapOfSendsPerWeek.get(numberOfWeeks) == null || mapOfSendsPerWeek.get(numberOfWeeks) < 6) {
                        offerHistory = new OfferHistory();
                        offerHistory = createOfferHistoryDaySpecific(member.getPrimPartyId(), leadKey, offer.getACTION_ID(), _engineFileWriter.getMapOfWriters(), offerHistory, _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().get(numberOfWeeks));
                        sendsPerWeekCount = mapOfSendsPerWeek.get(numberOfWeeks) == null ? 0 : mapOfSendsPerWeek.get(numberOfWeeks);
                        mapOfSendsPerWeek.put(numberOfWeeks, sendsPerWeekCount + 1);
                    }
                }
            }
        } else if (offer.getOFFER_CATEGORY().equalsIgnoreCase("MULTI_STEP_OFFER")) {
            for (Integer numberOfWeeks : _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().keySet()) {
                if (numberOfWeeks == 4) {
                    if (mapOfSendsPerWeek.get(numberOfWeeks) == null || mapOfSendsPerWeek.get(numberOfWeeks) < 6) {
                        offerHistory = new OfferHistory();
                        offerHistory = createOfferHistoryDaySpecific(member.getPrimPartyId(), leadKey, offer.getACTION_ID(), _engineFileWriter.getMapOfWriters(), offerHistory, _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().get(numberOfWeeks));
                        sendsPerWeekCount = mapOfSendsPerWeek.get(numberOfWeeks) == null ? 0 : mapOfSendsPerWeek.get(numberOfWeeks);
                        mapOfSendsPerWeek.put(numberOfWeeks, sendsPerWeekCount + 1);
                    }
                }
            }
        }
        // TODO : Enable this to generate Conversion Events data for AI predictions data set generation
        if (offerHistory.getLEAD_KEY() != null) {
            generateConversionEventsPerMember(offer, _engineFileWriter.getConversionEventsWriter(), offerHistory, _engineFileWriter.getFileCreationDate());
        }

        /*} else {
            // TODO : Throwaway code for 1 million scalability. Additional 550k members will not have any history.
            if (offer.getOFFER_CATEGORY().equalsIgnoreCase("INITIAL")) {
                for (Integer numberOfWeeks : _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().keySet()) {
                    if (numberOfWeeks < 3) {
                        if (mapOfSendsPerWeek.get(numberOfWeeks) == null || mapOfSendsPerWeek.get(numberOfWeeks) < 6) {
                            offerHistory = new OfferHistory();
                            offerHistory = createOfferHistoryDaySpecific(member.getPrimPartyId(), leadKey, offer.getACTION_ID(), mapOfWriters, offerHistory, _engineFileWriter.getMapOfWeekAndDaysForLastNWeeks().get(numberOfWeeks));
                            sendsPerWeekCount = mapOfSendsPerWeek.get(numberOfWeeks) == null ? 0 : mapOfSendsPerWeek.get(numberOfWeeks);
                            mapOfSendsPerWeek.put(numberOfWeeks, sendsPerWeekCount + 1);
                        }
                    }
                }
            }


        }*/
        return mapOfSendsPerWeek;
    }

    private void generateContextManagerOutputFiles(Eligibility eligibility, Offer offer) {

        _engineOutputFileGenerator.createAndWriteOutputRecords(eligibility,
                _engineFileWriter.getFirst4Weekdays(),
                _engineFileWriter.getEngineIntermediateOutputEmailFileWriter(),
                _engineFileWriter.getEngineIntermediateOutputWebFileWriter(),
                _engineFileWriter.getDateSuffixForEngagementId(),
                offer,
                _engineFileWriter.getFileCreationDate(),
                _engineFileWriter.getContextMgrEmailOutputWriter(),
                _engineFileWriter.getContextMgrWebOutputWriter(),
                _engineFileWriter.getContextMgrEmailOutputFileWriters(),
                _engineFileWriter.getContextMgrWebOutputFileWriters(), _engineFileWriter.isSplitSwitch());

    }

    private void generateAIOutputFilesWithClickAndConversionModels(AiOutput aiOutput, Member member, Offer offer) {
        // Logic to generate the AI output File

        String modelTypes = "click,hybrid";
        List<String> numberOfModelTypes = _commonMethods.getListOfStrings(modelTypes);

        if(_commonMethods.getRandomValueFromLIstOfString(numberOfModelTypes).equalsIgnoreCase("click")){
            _aiOutputFileGenerator.createAndWriteOutputRecords(member.getPrimPartyId(), offer.getACTION_ID(),
                    aiOutput, _engineFileWriter.getMapOfAiOutputClickModelFileWriters(), _engineFileWriter.getAiOutputClickModelFileWriter(), _engineFileWriter.isSplitSwitch());

        } else {
            _aiOutputFileGenerator.createAndWriteOutputRecords(member.getPrimPartyId(), offer.getACTION_ID(),
                    aiOutput, _engineFileWriter.getMapOfAiOutputConversionModelFileWriters(), _engineFileWriter.getAiOutputConversionModelFileWriter(), _engineFileWriter.isSplitSwitch());
        }
    }

    private void getMembersFromCsv(List<Member> membersList) {
        String path = System.getProperty("user.dir");
        File file = new File(path + "/src/main/resources/testMembers/Test_Members.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                Member member = new Member();
                List<String> details = Arrays.asList(line.split(","));
                member.setPrimPartyId(Integer.parseInt(details.get(0)));
                member.setFrequestFlyerId(details.get(1));
                membersList.add(member);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ExperimentDesign> getExperimentDesignPerCustomer(Integer primPartyId) {
        String flightOfferCountOptions = "0,1,2,3";
        List<String> numberOfFlightOptions = _commonMethods.getListOfStrings(flightOfferCountOptions);
        String nonFlightOfferCountOptions = "1,2,3";
        List<String> numberOfNonFlightOptions = _commonMethods.getListOfStrings(nonFlightOfferCountOptions);
        List<ExperimentDesign> experimentDesignPerCustomer = new ArrayList<ExperimentDesign>();
        ExperimentDesign design = new ExperimentDesign();
        design.setPrim_party_id(primPartyId);
        design.setAction_group_type("flight");
        design.setNumber_of_actions(_commonMethods.getRandomValueFromLIstOfString(numberOfFlightOptions));
        experimentDesignPerCustomer.add(design);
        design = new ExperimentDesign();
        design.setPrim_party_id(primPartyId);
        design.setAction_group_type("nonflight");
        design.setNumber_of_actions(_commonMethods.getRandomValueFromLIstOfString(numberOfNonFlightOptions));
        experimentDesignPerCustomer.add(design);
        return experimentDesignPerCustomer;

    }

    private Eligibility getEligibility(Long leadKey, Member member, Offer offer) {
        Eligibility eligibility;
        eligibility = new Eligibility();
        eligibility.setLEAD_KEY_ID(leadKey);
        eligibility.setPRIM_PARTY_ID(member.getPrimPartyId());
        eligibility.setACCOUNT_NUMBER(member.getFrequestFlyerId());
        eligibility.setACTION_ID(offer.getACTION_ID());
        eligibility.setHUB_CARD_KEY("NBA-" + offer.getACTION_ID());
        // TODO : Enable the following line if you want to generate web only offers as well
        List<String> deliveryMode = _commonMethods.getListOfStrings("web,email_web");
//        List<String> deliveryMode = _commonMethods.getListOfStrings("web");
        eligibility.setCHANNEL(_commonMethods.getRandomValueFromLIstOfString(deliveryMode));
        return eligibility;
    }

    private OfferHistory createOfferHistoryDaySpecific(Integer partyId, Long leadKeyId, String offerCode, Map<String, FileWriter> mapOfWriters, OfferHistory offerHistory, List<String> last7Days) {
        List<String> MediumList = _commonMethods.getListOfStrings("EMAIL,QF.COM");
        List<String> StatusList = new ArrayList<>();
        offerHistory.setPARTY_ID(partyId);
        offerHistory.setLEAD_KEY(leadKeyId);
        offerHistory.setACTION_ID(offerCode);
        // TODO : Get Action History for last 7 days from the file generation date.
        String historyDate = _commonMethods.getRandomValueFromLIstOfString(last7Days);
        offerHistory.setDATE_TM(historyDate);
        String medium = _commonMethods.getRandomValueFromLIstOfString(MediumList);
        offerHistory.setMEDIUM(medium);
        if (medium.equalsIgnoreCase("EMAIL")) {
            StatusList = _commonMethods.getListOfStrings("SENT,Open,Email click");
            offerHistory.setSTATUS(_commonMethods.getRandomValueFromLIstOfString(StatusList));
        } else {
            StatusList = _commonMethods.getListOfStrings("Requested,Register,ClickThrough");
            offerHistory.setSTATUS(_commonMethods.getRandomValueFromLIstOfString(StatusList));
        }
        _commonMethods.writeToFile(mapOfWriters.get(historyDate), offerHistory.toString());
        return offerHistory;
    }

    private void generateConversionEventsPerMember(Offer offer, FileWriter conversionEventsWriter, OfferHistory offerHistory, String fileCreationDate) {
        if (countOfConversionEventsPerMember <= allOffersPerMember.size() * 0.03) {  // TODO : 3% offers are assumed to be converted.
            generateConversionEvents(conversionEventsWriter, offerHistory.getPARTY_ID().toString(), offer, offerHistory.getLEAD_KEY(), offerHistory.getMEDIUM(), eventKey, fileCreationDate, offerHistory.getDATE_TM());
            eventKey += 1;
            countOfConversionEventsPerMember += 1;
        }

    }

    private void generateConversionEvents(FileWriter conversionEventsWriter, String primPartyId, Offer offer, Long leadKey, String offerChannel, Long eventKey, String fileCreationDate, String historyCreatedDate) {
        ConversionEvents conversionEvents = new ConversionEvents();
        conversionEvents.setPRIM_PARTY_ID(primPartyId);
        conversionEvents.setLEAD_KEY(leadKey.toString());
        conversionEvents.setOFFER_CODE(offer.getACTION_ID());
        conversionEvents.setEVENT_CATEGORY("CONVERSION_EVENT");
//        conversionEvents.setEVNT_TIMESTAMP(dtFunctions.getFormattedDateInyyyyMMdd(dtFunctions.getDateMinusNDaysFromGivenDate(fileCreationDate, 10)) + " 00:00:00");    //  ???????
        // Generate conversion after 2 days of the history generated date
        conversionEvents.setEVNT_TIMESTAMP(dtFunctions.getFormattedDateInyyyyMMdd(dtFunctions.getDatePlusNDaysFromGivenDate(historyCreatedDate, 2)) + " 00:00:00");
        conversionEvents.setCHANNEL(offerChannel);
        conversionEvents.setEVENT_KEY(eventKey.toString());
        conversionEvents.setEVENT_KEY_TYPE("TRAVEL_PLAN_ID");
        conversionEvents.setDESTINATION("");    //  Currently not used by AI
        conversionEvents.setDESTINATION_TYPE("");    //  Currently not used by AI
        conversionEvents.setGRP_NAME("TRGT");
        conversionEvents.setCONVERSION_FLAG("Y");
        conversionEvents.setTARGETED_FLAG("Y");
        conversionEvents.setDAYS_SINCE_TRGT("20"); // Currently not used by AI
        _commonMethods.writeToFile(conversionEventsWriter, conversionEvents.toString());
    }




}
