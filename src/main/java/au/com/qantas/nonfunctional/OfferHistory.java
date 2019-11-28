package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 29/06/2018.
 */
public class OfferHistory {


    private Integer PARTY_ID;
    private Long LEAD_KEY;
    private String ACTION_ID;
    private String STATUS;
    private String DATE_TM;
    private String MEDIUM;

    public Integer getPARTY_ID() {
        return PARTY_ID;
    }

    public void setPARTY_ID(Integer PARTY_ID) {
        this.PARTY_ID = PARTY_ID;
    }

    public Long getLEAD_KEY() {
        return LEAD_KEY;
    }

    public void setLEAD_KEY(Long LEAD_KEY) {
        this.LEAD_KEY = LEAD_KEY;
    }

    public String getACTION_ID() {
        return ACTION_ID;
    }

    public void setACTION_ID(String ACTION_ID) {
        this.ACTION_ID = ACTION_ID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getDATE_TM() {
        return DATE_TM;
    }

    public void setDATE_TM(String DATE_TM) {
        this.DATE_TM = DATE_TM;
    }

    public String getMEDIUM() {
        return MEDIUM;
    }

    public void setMEDIUM(String MEDIUM) {
        this.MEDIUM = MEDIUM;
    }


    @Override
    public String toString() {
        return PARTY_ID +
                "," + LEAD_KEY +
                "," + ACTION_ID +
                "," + STATUS +
                "," + DATE_TM +
                "," + MEDIUM
                ;
    }
}
