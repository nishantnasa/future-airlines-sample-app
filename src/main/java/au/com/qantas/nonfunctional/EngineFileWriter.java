package au.com.future-airlines.nonfunctional;

import au.com.future-airlines.utils.CommonMethods;
import au.com.future-airlines.utils.DateFunctions;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineFileWriter {

    private static final String OfferHistory_Header = "PRIM_PARTY_ID,LEAD_KEY,OFFER_CODE,EVENT_CATEGORY,EVNT_TIMESTAMP,CHANNEL";
    private static final String OUTPUT_EMAIL_FILE_HEADER = "lead_key_id|prim_party_id|action_group|action_rank|action_id|channel|delivery_date|engagement_id";
    private static final String OUTPUT_WEB_FILE_HEADER = "lead_key_id|prim_party_id|action_group|action_rank|action_id|channel|start_date|end_date|engagement_id";
    private static final String OUTPUT_AI_FILE_HEADER = "prim_party_id|action_id|prob_sample|prob_mean|prob_sd";
    private static final String EXPERIMENT_DESIGN_FILE_HEADER = "prim_party_id|action_group_type|number_of_actions";
    private static final String ELIGIBILITY_FILE_HEADER = "\"LEAD_KEY_ID\",\"PARTY_ID\",\"ACCT_NUMBER\",\"OFFER_CODE\",\"CREATE_DATE\",\"OFFER_CHANNEL_CODE\",\"UDI\",\"LAST_MODIFIED_DATE\"";
    private static final String CONVERSION_EVENTS_FILE_HEADER = "PRIM_PARTY_ID,LEAD_KEY,OFFER_CODE,EVENT_CATEGORY,EVNT_TIMESTAMP,CHANNEL,EVENT_KEY,EVENT_KEY_TYPE,DESTINATION,DESTINATION_TYPE,GRP_NAME,CONVERSION_FLAG,TARGETED_FLAG,DAYS_SINCE_TRGT";


    private String _fileCreationDate;
    private String _dateSuffixForEngagementId;
    private FileWriter eligibilityFileWriter;
    private List<String> first4Weekdays;
    private List<String> last7Days;
    private Map<Integer, List<String>> mapOfWeekAndDaysForLastNWeeks;
    private Map<String, FileWriter> mapOfWriters;
    private Map<Integer, FileWriter> mapOfEligibilityFileWriters;
    private FileWriter engineIntermediateOutputEmailFileWriter;
    private FileWriter writer = null;
    private FileWriter _engineIntermediateOutputWebFileWriter;
    private FileWriter _aiOutputClickModelFileWriter;
    private FileWriter _aiOutputConversionModelFileWriter;
    private Map<Integer, FileWriter> _mapOfAiOutputClickModelFileWriters;
    private Map<Integer, FileWriter> _mapOfAiOutputConversionModelFileWriters;
    private FileWriter _experimentDesignFileWriter;
    private FileWriter _conversionModelFileWriter;
    private FileWriter _timeOfDayEmailSendInputFileWriter;
    private Map<Integer, FileWriter> _contextMgrEmailOutputFileWriters;
    private Map<Integer, FileWriter> _contextMgrWebOutputFileWriters;
    private FileWriter _contextMgrEmailOutputWriter;
    private FileWriter _contextMgrWebOutputWriter;
    private FileWriter _conversionEventsWriter;
    private CommonMethods _commonMethods;
    private DateFunctions _dateFunctions;
    private boolean _splitSwitch = false;

    public EngineFileWriter() {
        _dateFunctions = new DateFunctions();
        _commonMethods = new CommonMethods();
        init();
    }


    public void init() {
        _fileCreationDate = _commonMethods.getFileCreationDate();
        _dateSuffixForEngagementId = _dateFunctions.getFormattedDateInyyMMdd(_fileCreationDate);
        eligibilityFileWriter = _commonMethods.createFile("eligibility_offers_" + _fileCreationDate + ".csv");
        first4Weekdays = _dateFunctions.getMondayToThursdayDatesInCurrentWeek(_fileCreationDate);
        last7Days = _dateFunctions.getLast7DaysInCurrentWeek(_fileCreationDate);

        /* Generate history for last n weeks. Pass the number of weeks as an argument  */
        mapOfWeekAndDaysForLastNWeeks = _dateFunctions.get7DaysForEachWeekInLastNWeeks(_fileCreationDate, 20);
        mapOfWriters = getBufferedWritersForEligibilityDays(mapOfWeekAndDaysForLastNWeeks);
        /* Splitting eligibility file to avoid disc fragmentation issue on DEV box */
        mapOfEligibilityFileWriters = getEligibilityFileWriters(_fileCreationDate);
        engineIntermediateOutputEmailFileWriter = _commonMethods.createFile("part-00199-9d0a7e0a-529d-40fe-9832-56a27e60e110-c000.csv");
        _engineIntermediateOutputWebFileWriter = _commonMethods.createFile("part-00000-fe51646d-5948-4db7-91ee-48676a84de89-c000.csv");
        _aiOutputClickModelFileWriter = _commonMethods.createFile("part-00003-5650e57f-7dab-c000.csv");
        _aiOutputConversionModelFileWriter = _commonMethods.createFile("part-00004-5650e57f-7dab-c000.csv");

        /* Splitting AI Output files to avoid disc fragmentation issue on DEV box */
        _mapOfAiOutputClickModelFileWriters = getAIClickModelFileWriters();
        _mapOfAiOutputConversionModelFileWriters = getAIConversionModelFileWriters();
        _experimentDesignFileWriter = _commonMethods.createFile("part-00002-faf0774b-f31f-47f2-aaea-27a8927bd4c8-c000.csv");
        _conversionModelFileWriter = _commonMethods.createFile("strategic_insights_loy.lts02_click_conversions_v3.csv");
        _timeOfDayEmailSendInputFileWriter = _commonMethods.createFile("Measurement_Team_Input.csv");
        //ContextManagerOutput Files
        _contextMgrEmailOutputFileWriters = getContextMgrEmailOutputFileWriters( 1);
        _contextMgrWebOutputFileWriters = getContextMgrWebOutputFileWriters( 10);
        _contextMgrEmailOutputWriter = _commonMethods.createFile("email-actions.txt");
        _contextMgrWebOutputWriter = _commonMethods.createFile("C2_HUB_MBR_CARD_NBA_" + _fileCreationDate + "_060000.txt");
        _conversionEventsWriter = _commonMethods.createFile("CONVERSION_EVENTS_" + _fileCreationDate + ".csv");

    }

    private Map<String, FileWriter> getBufferedWritersForEligibilityDays(Map<Integer, List<String>> mapOfWeekAndDaysForLast15Weeks) {
        Map<String, FileWriter> mapOfWriters = new HashMap<String, FileWriter>();
        for (Integer integer : mapOfWeekAndDaysForLast15Weeks.keySet()) {
            for (String s : mapOfWeekAndDaysForLast15Weeks.get(integer)) {
                FileWriter fileWriter = _commonMethods.createActionHistoryFile("OFFR_ACTN_HIST_" + s.substring(0, 10).replace("-", "") + ".csv", writer);
                mapOfWriters.put(s, fileWriter);
            }
        }
        return mapOfWriters;
    }

    private Map<Integer, FileWriter> getEligibilityFileWriters(String fileCreationDate) {
        Map<Integer, FileWriter> mapOfWriters = new HashMap<Integer, FileWriter>();
        if (_splitSwitch) {
            for (int i = 0; i < 10; i++) {
                FileWriter fileWriter = _commonMethods.createFile("eligibility_offers_" + fileCreationDate + "_" + i + ".csv");
                mapOfWriters.put(i, fileWriter);
            }
        }
        return mapOfWriters;
    }

    private Map<Integer, FileWriter> getAIClickModelFileWriters() {
        Map<Integer, FileWriter> mapOfWriters = new HashMap<Integer, FileWriter>();
        if (_splitSwitch) {
            for (int i = 0; i < 10; i++) {
                FileWriter fileWriter = _commonMethods.createFile("part-00003-5650e57f-7dab-c000_" + i + ".csv");
                mapOfWriters.put(i, fileWriter);
            }
        }
        return mapOfWriters;
    }

    private Map<Integer, FileWriter> getAIConversionModelFileWriters() {
        Map<Integer, FileWriter> mapOfWriters = new HashMap<Integer, FileWriter>();
        if (_splitSwitch) {
            for (int i = 0; i < 10; i++) {
                FileWriter fileWriter = _commonMethods.createFile("part-00004-5650e57f-7dab-c000_" + i + ".csv");
                mapOfWriters.put(i, fileWriter);
            }
        }
        return mapOfWriters;
    }

    private Map<Integer, FileWriter> getContextMgrEmailOutputFileWriters( int numberOfFiles) {
        Map<Integer, FileWriter> mapOfWriters = new HashMap<Integer, FileWriter>();
        if (_splitSwitch) {
            for (int i = 0; i < numberOfFiles; i++) {
                FileWriter fileWriter = _commonMethods.createFile("email-actions_" + _fileCreationDate + "_" + i + ".txt");
                mapOfWriters.put(i, fileWriter);
            }
        }
        return mapOfWriters;
    }

    private Map<Integer, FileWriter> getContextMgrWebOutputFileWriters(int numberOfFiles) {
        Map<Integer, FileWriter> mapOfWriters = new HashMap<Integer, FileWriter>();
        if (_splitSwitch) {
            for (int i = 0; i < numberOfFiles; i++) {
                FileWriter fileWriter = _commonMethods.createFile("C2_HUB_MBR_CARD_NBA_" + _fileCreationDate + "_060000_" + i + ".txt");
                _commonMethods.writeToFile(fileWriter, "00~{\"EXTRACT_NAME\":\"C2_HUB_MBR_CARD_" + _fileCreationDate + "\",\"EXTRACT_DATE\":\"" + _dateFunctions.getFormattedDateInyyyyMMdd(_fileCreationDate) + "\",\"EXTRACT_SEQ\":1}");
                mapOfWriters.put(i, fileWriter);
            }
        }
        return mapOfWriters;
    }

    public void flushEngineFileWriters() {
        _commonMethods.flushFileWriter(_experimentDesignFileWriter);
        _commonMethods.flushFileWriter(_conversionModelFileWriter);
        _commonMethods.flushFileWriter(_timeOfDayEmailSendInputFileWriter);
        _commonMethods.flushFileWriter(_contextMgrEmailOutputWriter);
        _commonMethods.flushFileWriter(_contextMgrWebOutputWriter);
        _commonMethods.flushFileWriter(_conversionEventsWriter);
        _commonMethods.flushMultipleFileWriters(mapOfWriters.values());
        _commonMethods.flushMultipleFileWriters(mapOfEligibilityFileWriters.values());
        _commonMethods.flushMultipleFileWriters(_mapOfAiOutputClickModelFileWriters.values());
        _commonMethods.flushMultipleFileWriters(_mapOfAiOutputConversionModelFileWriters.values());
        _commonMethods.flushMultipleFileWriters(_contextMgrEmailOutputFileWriters.values());
        _commonMethods.flushMultipleFileWriters(_contextMgrWebOutputFileWriters.values());
        _commonMethods.flushFileWriter(eligibilityFileWriter);
        _commonMethods.flushFileWriter(_aiOutputClickModelFileWriter);
        _commonMethods.flushFileWriter(_aiOutputConversionModelFileWriter);

    }

    public void closeEngineFilewriters() {
        _commonMethods.closeFileWriter(writer);
        _commonMethods.closeFileWriter(engineIntermediateOutputEmailFileWriter);
        _commonMethods.closeFileWriter(_aiOutputClickModelFileWriter);
        _commonMethods.closeFileWriter(_aiOutputConversionModelFileWriter);
        _commonMethods.closeFileWriter(_engineIntermediateOutputWebFileWriter);
        _commonMethods.closeFileWriter(_experimentDesignFileWriter);
        _commonMethods.closeFileWriter(_conversionModelFileWriter);
        _commonMethods.closeFileWriter(_timeOfDayEmailSendInputFileWriter);
        _commonMethods.closeFileWriter(_contextMgrEmailOutputWriter);
        _commonMethods.closeFileWriter(_contextMgrWebOutputWriter);
        _commonMethods.closeFileWriter(_conversionEventsWriter);
        _commonMethods.closeMultipleFileWriters(mapOfWriters.values());
        _commonMethods.closeMultipleFileWriters(mapOfEligibilityFileWriters.values());
        _commonMethods.closeMultipleFileWriters(_mapOfAiOutputClickModelFileWriters.values());
        _commonMethods.closeMultipleFileWriters(_mapOfAiOutputConversionModelFileWriters.values());
        _commonMethods.closeMultipleFileWriters(_contextMgrEmailOutputFileWriters.values());
        _commonMethods.closeMultipleFileWriters(_contextMgrWebOutputFileWriters.values());
        _commonMethods.closeFileWriter(eligibilityFileWriter);
    }

    public void writeHeaders() {
        _commonMethods.writeToFile(_contextMgrWebOutputWriter, "00~{\"EXTRACT_NAME\":\"C2_HUB_MBR_CARD_" + _fileCreationDate + "\",\"EXTRACT_DATE\":\"" + _dateFunctions.getFormattedDateInyyyyMMdd(_fileCreationDate) + "\",\"EXTRACT_SEQ\":1}");
        _commonMethods.writeToFile(getExperimentDesignFileWriter(), EXPERIMENT_DESIGN_FILE_HEADER);
        _commonMethods.writeToFile(getConversionModelFileWriter(), "prim_party_id|model_type");
        _commonMethods.writeToFile(getTimeOfDayEmailSendInputFileWriter(), "PRIM_PARTY_ID,timeslot,selection");
        _commonMethods.writeToFile(getEligibilityFileWriter(), "prim_party_id,action_id,lead_key_id,channel,account_number,hub_card_key");
        _commonMethods.writeToFile(getConversionEventsWriter(), CONVERSION_EVENTS_FILE_HEADER);
        _commonMethods.writeToFile(getAiOutputClickModelFileWriter(), OUTPUT_AI_FILE_HEADER);
        _commonMethods.writeToFile(getAiOutputConversionModelFileWriter(), OUTPUT_AI_FILE_HEADER);
        for (FileWriter fileWriter : getMapOfWriters().values()) {
            _commonMethods.writeToFile(fileWriter, OfferHistory_Header);
        }
    }

    public FileWriter getExperimentDesignFileWriter() {
        return _experimentDesignFileWriter;
    }

    public FileWriter getConversionModelFileWriter() {
        return _conversionModelFileWriter;
    }

    public FileWriter getTimeOfDayEmailSendInputFileWriter() {
        return _timeOfDayEmailSendInputFileWriter;
    }

    public FileWriter getEligibilityFileWriter() {
        return eligibilityFileWriter;
    }

    public FileWriter getConversionEventsWriter() {
        return _conversionEventsWriter;
    }

    public FileWriter getAiOutputClickModelFileWriter() {
        return _aiOutputClickModelFileWriter;
    }

    public FileWriter getAiOutputConversionModelFileWriter() {
        return _aiOutputConversionModelFileWriter;
    }

    public Map<String, FileWriter> getMapOfWriters() {
        return mapOfWriters;
    }

    public String getFileCreationDate() {
        return _fileCreationDate;
    }

    public String getDateSuffixForEngagementId() {
        return _dateSuffixForEngagementId;
    }

    public List<String> getFirst4Weekdays() {
        return first4Weekdays;
    }

    public List<String> getLast7Days() {
        return last7Days;
    }

    public Map<Integer, List<String>> getMapOfWeekAndDaysForLastNWeeks() {
        return mapOfWeekAndDaysForLastNWeeks;
    }

    public Map<Integer, FileWriter> getMapOfEligibilityFileWriters() {
        return mapOfEligibilityFileWriters;
    }

    public Map<Integer, FileWriter> getMapOfAiOutputClickModelFileWriters() {
        return _mapOfAiOutputClickModelFileWriters;
    }

    public Map<Integer, FileWriter> getMapOfAiOutputConversionModelFileWriters() {
        return _mapOfAiOutputConversionModelFileWriters;
    }

    public Map<Integer, FileWriter> getContextMgrEmailOutputFileWriters() {
        return _contextMgrEmailOutputFileWriters;
    }

    public Map<Integer, FileWriter> getContextMgrWebOutputFileWriters() {
        return _contextMgrWebOutputFileWriters;
    }

    public FileWriter getContextMgrEmailOutputWriter() {
        return _contextMgrEmailOutputWriter;
    }

    public FileWriter getContextMgrWebOutputWriter() {
        return _contextMgrWebOutputWriter;
    }

    public CommonMethods getCommonMethods() {
        return _commonMethods;
    }

    public DateFunctions getDateFunctions() {
        return _dateFunctions;
    }

    public boolean isSplitSwitch() {
        return _splitSwitch;
    }

    public FileWriter getEngineIntermediateOutputEmailFileWriter() {
        return engineIntermediateOutputEmailFileWriter;
    }

    public FileWriter getEngineIntermediateOutputWebFileWriter() {
        return _engineIntermediateOutputWebFileWriter;
    }

}
