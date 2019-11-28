package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 12/10/2018.
 */
public class ContextManagerOutput {

    private Long LEAD_KEY;
    private Integer Party_Id;
    private String ACTION_ID;
    private String startDate;
    private String Engagement_Id;
    private String p_rolling_flag;   //": "0", "
    private String t_rolling_flag;   //": "1", "
    private String t_start_date;     //": "2018-10-13T06:00:00", "
    private String t_end_date;       //": "2018-10-14T06:00:00"}
    private String pricingData;

    public ContextManagerOutput() {
    }

    public Long getLEAD_KEY() {
        return LEAD_KEY;
    }

    public void setLEAD_KEY(Long LEAD_KEY) {
        this.LEAD_KEY = LEAD_KEY;
    }

    public Integer getParty_Id() {
        return Party_Id;
    }

    public void setParty_Id(Integer party_Id) {
        Party_Id = party_Id;
    }

    public String getACTION_ID() {
        return ACTION_ID;
    }

    public void setACTION_ID(String ACTION_ID) {
        this.ACTION_ID = ACTION_ID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEngagement_Id() {
        return Engagement_Id;
    }

    public void setEngagement_Id(String engagement_Id) {
        Engagement_Id = engagement_Id;
    }

    public String getP_rolling_flag() {
        return p_rolling_flag;
    }

    public void setP_rolling_flag(String p_rolling_flag) {
        this.p_rolling_flag = p_rolling_flag;
    }

    public String getT_rolling_flag() {
        return t_rolling_flag;
    }

    public void setT_rolling_flag(String t_rolling_flag) {
        this.t_rolling_flag = t_rolling_flag;
    }

    public String getT_start_date() {
        return t_start_date;
    }

    public void setT_start_date(String t_start_date) {
        this.t_start_date = t_start_date;
    }

    public String getT_end_date() {
        return t_end_date;
    }

    public void setT_end_date(String t_end_date) {
        this.t_end_date = t_end_date;
    }

    public String getPricingData() {
        return pricingData;
    }

    public void setPricingData(String pricingData) {
        this.pricingData = pricingData;
    }

    public String toStringEmail() {
        return LEAD_KEY +
                "µ" + Party_Id +
                "µ" + ACTION_ID +
                "µ" + startDate + "T06:00:00" +
                "µ" + Engagement_Id +
                "µ{\"p_rolling_flag\":\"" + p_rolling_flag + "\"" +
                ",\"t_rolling_flag\":\"" + t_rolling_flag + "\"}" +
                "µ" + "SYD-MNL-ECO-Y|SYD-SGN-ECO-Y|SYD-SGN-BUS-Y|SYD-SIN-ECO-Y|SYD-SIN-PRE-Y|SYD-SIN-BUS-Y|SYD-CMB-ECO-N|SYD-CMB-PRE-Y|SYD-CMB-BUS-Y|SYD-CMB-FIR-Y|SYD-HKT-ECO-Y|SYD-HKT-BUS-Y|SYD-HKT-PRE-Y|SYD-HKT-FIR-Y|SYD-KUL-BUS-Y|SYD-KUL-ECO-Y|SYD-KUL-PRE-Y|SYD-KUL-FIR-Y|SYD-PEN-ECO-Y|SYD-PEN-PRE-Y"
                ;
    }

    public String toStringWeb() {
        return "01~" + Engagement_Id +
                "~00" + Party_Id +
                "~NBA-" + ACTION_ID +
                "~{\"startDate\": \"" + startDate + " 00:00:01\"" +
                ",\"endDate\": \"2018-11-19 23:59:59\"" +
                ",\"leadkey\": \"" + LEAD_KEY + "\"" +
                '}';
    }
}
