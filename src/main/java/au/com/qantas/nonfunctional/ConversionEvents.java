package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 27/11/2018.
 */
public class ConversionEvents {

    private String PRIM_PARTY_ID;
    private String LEAD_KEY;
    private String OFFER_CODE;
    private String EVENT_CATEGORY;
    private String EVNT_TIMESTAMP;
    private String CHANNEL;
    private String EVENT_KEY;
    private String EVENT_KEY_TYPE;
    private String DESTINATION;
    private String DESTINATION_TYPE;
    private String GRP_NAME;
    private String CONVERSION_FLAG;
    private String TARGETED_FLAG;
    private String DAYS_SINCE_TRGT;

    public String getPRIM_PARTY_ID() {
        return PRIM_PARTY_ID;
    }

    public void setPRIM_PARTY_ID(String PRIM_PARTY_ID) {
        this.PRIM_PARTY_ID = PRIM_PARTY_ID;
    }

    public String getLEAD_KEY() {
        return LEAD_KEY;
    }

    public void setLEAD_KEY(String LEAD_KEY) {
        this.LEAD_KEY = LEAD_KEY;
    }

    public String getOFFER_CODE() {
        return OFFER_CODE;
    }

    public void setOFFER_CODE(String OFFER_CODE) {
        this.OFFER_CODE = OFFER_CODE;
    }

    public String getEVENT_CATEGORY() {
        return EVENT_CATEGORY;
    }

    public void setEVENT_CATEGORY(String EVENT_CATEGORY) {
        this.EVENT_CATEGORY = EVENT_CATEGORY;
    }

    public String getEVNT_TIMESTAMP() {
        return EVNT_TIMESTAMP;
    }

    public void setEVNT_TIMESTAMP(String EVNT_TIMESTAMP) {
        this.EVNT_TIMESTAMP = EVNT_TIMESTAMP;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public String getEVENT_KEY() {
        return EVENT_KEY;
    }

    public void setEVENT_KEY(String EVENT_KEY) {
        this.EVENT_KEY = EVENT_KEY;
    }

    public String getEVENT_KEY_TYPE() {
        return EVENT_KEY_TYPE;
    }

    public void setEVENT_KEY_TYPE(String EVENT_KEY_TYPE) {
        this.EVENT_KEY_TYPE = EVENT_KEY_TYPE;
    }

    public String getDESTINATION() {
        return DESTINATION;
    }

    public void setDESTINATION(String DESTINATION) {
        this.DESTINATION = DESTINATION;
    }

    public String getDESTINATION_TYPE() {
        return DESTINATION_TYPE;
    }

    public void setDESTINATION_TYPE(String DESTINATION_TYPE) {
        this.DESTINATION_TYPE = DESTINATION_TYPE;
    }

    public String getGRP_NAME() {
        return GRP_NAME;
    }

    public void setGRP_NAME(String GRP_NAME) {
        this.GRP_NAME = GRP_NAME;
    }

    public String getCONVERSION_FLAG() {
        return CONVERSION_FLAG;
    }

    public void setCONVERSION_FLAG(String CONVERSION_FLAG) {
        this.CONVERSION_FLAG = CONVERSION_FLAG;
    }

    public String getTARGETED_FLAG() {
        return TARGETED_FLAG;
    }

    public void setTARGETED_FLAG(String TARGETED_FLAG) {
        this.TARGETED_FLAG = TARGETED_FLAG;
    }

    public String getDAYS_SINCE_TRGT() {
        return DAYS_SINCE_TRGT;
    }

    public void setDAYS_SINCE_TRGT(String DAYS_SINCE_TRGT) {
        this.DAYS_SINCE_TRGT = DAYS_SINCE_TRGT;
    }

    @Override
    public String toString() {
        return PRIM_PARTY_ID + ',' +
                LEAD_KEY + ',' +
                OFFER_CODE + ',' +
                EVENT_CATEGORY + ',' +
                EVNT_TIMESTAMP + ',' +
                CHANNEL + ',' +
                EVENT_KEY + ',' +
                EVENT_KEY_TYPE + ',' +
                DESTINATION + ',' +
                DESTINATION_TYPE + ',' +
                GRP_NAME + ',' +
                CONVERSION_FLAG + ',' +
                TARGETED_FLAG + ',' +
                DAYS_SINCE_TRGT
                ;
    }
}
