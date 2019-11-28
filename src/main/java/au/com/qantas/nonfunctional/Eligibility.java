package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 29/06/2018.
 */
public class Eligibility {


    private Long LEAD_KEY_ID;
    private String HUB_CARD_KEY;
    private Integer PRIM_PARTY_ID;
    private String ACTION_ID;
    private String CHANNEL;
    private String ACCOUNT_NUMBER;

    public String getHUB_CARD_KEY() {
        return HUB_CARD_KEY;
    }

    public void setHUB_CARD_KEY(String HUB_CARD_KEY) {
        this.HUB_CARD_KEY = HUB_CARD_KEY;
    }

    public Integer getPRIM_PARTY_ID() {
        return PRIM_PARTY_ID;
    }

    public void setPRIM_PARTY_ID(Integer PRIM_PARTY_ID) {
        this.PRIM_PARTY_ID = PRIM_PARTY_ID;
    }

    public String getACTION_ID() {
        return ACTION_ID;
    }

    public void setACTION_ID(String ACTION_ID) {
        this.ACTION_ID = ACTION_ID;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public String getACCOUNT_NUMBER() {
        return ACCOUNT_NUMBER;
    }

    public void setACCOUNT_NUMBER(String ACCOUNT_NUMBER) {
        this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
    }

    public Long getLEAD_KEY_ID() {
        return LEAD_KEY_ID;
    }

    public void setLEAD_KEY_ID(Long LEAD_KEY_ID) {
        this.LEAD_KEY_ID = LEAD_KEY_ID;
    }


    public String getNewEligibilityString() {
        return PRIM_PARTY_ID +
                "," + ACTION_ID +
                "," + LEAD_KEY_ID +
                "," + CHANNEL +
                "," + ACCOUNT_NUMBER +
                "," + HUB_CARD_KEY ;
    }


}
