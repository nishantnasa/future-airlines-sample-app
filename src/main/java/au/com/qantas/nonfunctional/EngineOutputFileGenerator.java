package au.com.future-airlines.nonfunctional;

import au.com.future-airlines.utils.CommonMethods;
import au.com.future-airlines.utils.DateFunctions;
import au.com.future-airlines.utils.SystemProperties;

import java.io.FileWriter;
import java.util.*;

/**
 * Created by boses on 3/07/2018.
 */
public class EngineOutputFileGenerator {

    Map<String, Integer> rankings = new HashMap<String, Integer>();
    CommonMethods commonMethods = new CommonMethods();
    DateFunctions dtFunctions = new DateFunctions();

    Integer emailOutputGeneratedForFFMember = 0;
    List<Integer> listOfUpperBound = new LinkedList<>();

    private static Map<Integer, Integer> _footerCountMap = new HashMap<>();

    public Integer getNextRank(Integer groupId, String ChannelCode) {
        Integer currentRank = 0;
        Integer nextEmailRank = 1;
        Integer nextWebRank = 1;
        Integer nextRank = 1;
        if (rankings.get(groupId + "_" + ChannelCode) != null) {
            currentRank = rankings.get(groupId + "_" + ChannelCode);
            if (ChannelCode.contains("email")) {
                nextEmailRank = currentRank + 1;
                nextRank = nextEmailRank;
            } else {
                nextWebRank = currentRank + 1;
                nextRank = nextWebRank;
            }
        }
        rankings.put(groupId + "_" + ChannelCode, nextRank);
        return nextRank;
    }


    public void createAndWriteOutputRecords(Eligibility eligibility, List<String> first4Weekdays,
                                            FileWriter engineIntermediateOutputEmailFileWriter, FileWriter engineIntermediateOutputWebFileWriter,
                                            String dateSuffixForEngagementId, Offer offer, String fileCreationDate,
                                            FileWriter contextMgrEmailOutputWriter, FileWriter contextMgrWebOutputWriter,
                                            final Map<Integer, FileWriter> contextMgrEmailOutputFileWriters, final
                                            Map<Integer, FileWriter> contextMgrWebOutputFileWriters, boolean splitSwitch) {

        PipelineOutput pipelineOutput = new PipelineOutput();
        int primPartyId = eligibility.getPRIM_PARTY_ID();
        Long leadKeyId = eligibility.getLEAD_KEY_ID();
        pipelineOutput.setEngagement_Id("3333" + leadKeyId + dateSuffixForEngagementId);
        pipelineOutput.setLead_Key_Id(leadKeyId);
        pipelineOutput.setParty_Id(primPartyId);
        pipelineOutput.setRank(getNextRank(offer.getOffer_Group_Id(), eligibility.getCHANNEL())); // Allocating ranks to offers
        pipelineOutput.setRankingGroup(offer.getOffer_Group_Id());
        pipelineOutput.setOffer_Code(offer.getACTION_ID());

        if (eligibility.getCHANNEL().contains("email")) {
            pipelineOutput.setStartDate(commonMethods.getRandomValueFromLIstOfString(first4Weekdays));
            pipelineOutput.setChannel("email");
            // TODO : Enable if you want engine output
//                commonMethods.writeToFile(engineIntermediateOutputEmailFileWriter, pipelineOutput.emailOutputString());
            // This check is make sure we generate one email actions per member to avoid duplicate emails in AC(Adobe compaign)
            if (emailOutputGeneratedForFFMember != primPartyId) {
                String contextManagerEmailOutput = getContextManagerEmailOutput(offer, pipelineOutput, fileCreationDate);
                // TODO : Enable if you want ContextManager output

//                commonMethods.writeToFile(contextMgrWebOutputWriter, contextManagerEmailOutput);
                writeEmailFiles(contextMgrEmailOutputFileWriters, contextMgrEmailOutputWriter , contextManagerEmailOutput, primPartyId,splitSwitch);
                emailOutputGeneratedForFFMember = primPartyId;
            }

        } else {
            pipelineOutput.setChannel("web");
            pipelineOutput.setStartDate("2018-05-04T14:00:01");
            pipelineOutput.setEndDate("2018-12-20T14:00:01");
            if (emailOutputGeneratedForFFMember != primPartyId) {
                // TODO : Enable if you want engine output
//                commonMethods.writeToFile(engineIntermediateOutputWebFileWriter, pipelineOutput.webOutputString());
                String contextManagerWebOutput = getContextManagerWebOutput(offer, pipelineOutput, fileCreationDate);
                // TODO : Enable if you want ContextManager output
//            commonMethods.writeToFile(contextMgrWebOutputWriter, contextManagerWebOutput);
//                writeWebFiles(contextMgrWebOutputFileWriters, contextMgrWebOutputWriter, contextManagerWebOutput, primPartyId, splitSwitch);
                writeWebFiles(contextMgrWebOutputFileWriters, primPartyId, contextMgrWebOutputWriter, contextManagerWebOutput);
                emailOutputGeneratedForFFMember = primPartyId;
            }

        }
    }

    private void writeEmailFiles(Map<Integer, FileWriter> contextMgrEmailOutputWriters,
                                 FileWriter contextMgrEmailOutputWriter, String contextManagerEmailOutput,
                                 int primPartyId, boolean splitSwitch) {

        //Always split CM output files
        if (splitSwitch) {
            if (primPartyId < 11000000) {
                contextMgrEmailOutputWriter = contextMgrEmailOutputWriters.get(0);
            } else {
                contextMgrEmailOutputWriter = contextMgrEmailOutputWriters.get((primPartyId - 10000000) / 1000000);
            }
        }
        commonMethods.writeToFile(contextMgrEmailOutputWriter, contextManagerEmailOutput);
    }

/*    private void writeWebFiles(Map<Integer, FileWriter> contextMgrwebOutputWriters,
                               FileWriter contextMgrWebOutputWriter, String contextManagerWebOutput,
                               int primPartyId, boolean splitSwitch) {

        int fileNumber = 0;
        //Always split CM output files
        if (splitSwitch) {
            if (primPartyId < 11000000) {
                contextMgrWebOutputWriter = contextMgrwebOutputWriters.get(0);
            } else {
                fileNumber = (primPartyId - 10000000) / 1000000;
                contextMgrWebOutputWriter = contextMgrwebOutputWriters.get((fileNumber));
            }
        }
        commonMethods.writeToFile(contextMgrWebOutputWriter, contextManagerWebOutput);
//        _footerCountMap.put(contextMgrWebOutputWriter, getCounter(fileNumber));
    }*/


    public void writeWebFiles(Map<Integer, FileWriter> contextMgrWebOutputWriters, int primPartyId,
                     FileWriter contextMgrWebOutputWriter, String contextManagerWebOutput) {

        Integer numberOfFiles = contextMgrWebOutputWriters.size() == 0 ? 1 : contextMgrWebOutputWriters.size();

        Integer fileNumber = 0;


//        Integer totalNumberOfPPID = 10000000 - Integer.parseInt(SystemProperties.getFFUserCount());
        Integer totalNumberOfPPID = Integer.parseInt(SystemProperties.getFFUserCount());
        Integer counter = totalNumberOfPPID / numberOfFiles;  //

        Integer simulatorPPID = 10000000;
        Integer upperBound = simulatorPPID;


        if (numberOfFiles > 1) {
            if (counter <= numberOfFiles) {
                contextMgrWebOutputWriter = contextMgrWebOutputWriters.get(0);
                if (_footerCountMap.get(0) == null) {
                    _footerCountMap.put(0, 1);
                } else {
                    _footerCountMap.put(0, _footerCountMap.get(0).intValue() + 1);
                }

            } else {
                if (listOfUpperBound.size()==0) {
                    for (int i = 0; i < numberOfFiles; i++) {
                        upperBound = upperBound + counter;
                        listOfUpperBound.add(upperBound);
//                        System.out.println(i + "  --  " + upperBound);
                    }
                }

                for (int j = 0; j < listOfUpperBound.size(); j++) {

                    if (primPartyId < listOfUpperBound.get(j)) {
//                        System.out.println(primPartyId + "  --  " + listOfUpperBound.get(j));
                        contextMgrWebOutputWriter = contextMgrWebOutputWriters.get(j);
                        fileNumber = j;
                        if (_footerCountMap.get(fileNumber) == null) {
                            _footerCountMap.put(fileNumber, 1);
                        } else {
                            _footerCountMap.put(fileNumber, _footerCountMap.get(fileNumber).intValue() + 1);
                        }
                        break;
                    }
                }
            }
            commonMethods.writeToFile(contextMgrWebOutputWriter, contextManagerWebOutput);
        } else {
            commonMethods.writeToFile(contextMgrWebOutputWriter, contextManagerWebOutput);
            if (_footerCountMap.get(0) == null) {
                _footerCountMap.put(0, 1);
            } else {
                _footerCountMap.put(0, _footerCountMap.get(0).intValue() + 1);
            }
        }


    }


    public void addFooterToWebContextManagerOutputFile(Map<Integer, FileWriter> contextMgrwebOutputWriters, FileWriter contextMgrWebOutputWriter) {
        if (contextMgrwebOutputWriters.size() == 0) {
            String footer = "99~{\"INSERT_REC_QTY\":" + _footerCountMap.get(0).intValue() + "}";
            commonMethods.writeToFile(contextMgrWebOutputWriter, footer);
        }

        if (contextMgrwebOutputWriters.size() == 1) {
            String footer = "99~{\"INSERT_REC_QTY\":" + _footerCountMap.get(0).intValue() + "}";
            commonMethods.writeToFile(contextMgrwebOutputWriters.get(0), footer);
        }
        if (contextMgrwebOutputWriters.size() > 1) {

            for (Map.Entry<Integer, Integer> entry : _footerCountMap.entrySet()) {
                String footer = "99~{\"INSERT_REC_QTY\":" + entry.getValue() + "}";
                commonMethods.writeToFile(contextMgrwebOutputWriters.get(entry.getKey()), footer);

            }
        }
    }

    private String getContextManagerEmailOutput(Offer offer, PipelineOutput pipelineOutput, String fileCreationDate) {
        ContextManagerOutput contextManagerOutputEmail = new ContextManagerOutput();
        contextManagerOutputEmail.setLEAD_KEY(pipelineOutput.getLead_Key_Id());
        contextManagerOutputEmail.setParty_Id(pipelineOutput.getParty_Id());
        contextManagerOutputEmail.setACTION_ID(pipelineOutput.getOffer_Code());
        contextManagerOutputEmail.setStartDate(dtFunctions.getNextSaturdayInCurrentWeek(fileCreationDate));
        contextManagerOutputEmail.setEngagement_Id(pipelineOutput.getEngagement_Id());
//        contextManagerOutputEmail.setP_rolling_flag(offer.getPURCHASE_ROLLING_FLAG());
        contextManagerOutputEmail.setP_rolling_flag("0");
//        contextManagerOutputEmail.setT_rolling_flag(offer.getTRAVEL_ROLLING_FLAG());
        contextManagerOutputEmail.setT_rolling_flag("0");
//        contextManagerOutputEmail.setT_start_date();
//        contextManagerOutputEmail.setT_end_date();
        return contextManagerOutputEmail.toStringEmail();
    }


    private String getContextManagerWebOutput(Offer offer, PipelineOutput pipelineOutput, String fileCreationDate) {
        ContextManagerOutput contextManagerOutputWeb = new ContextManagerOutput();
        contextManagerOutputWeb.setLEAD_KEY(pipelineOutput.getLead_Key_Id());
        contextManagerOutputWeb.setParty_Id(pipelineOutput.getParty_Id());
        contextManagerOutputWeb.setACTION_ID(pipelineOutput.getOffer_Code());
        contextManagerOutputWeb.setStartDate(dtFunctions.getNextSaturdayInCurrentWeek(fileCreationDate));
        contextManagerOutputWeb.setEngagement_Id(pipelineOutput.getEngagement_Id());
        return contextManagerOutputWeb.toStringWeb();
    }


}
