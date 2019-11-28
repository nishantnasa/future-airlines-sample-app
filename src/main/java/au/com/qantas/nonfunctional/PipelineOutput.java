package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 3/07/2018.
 */
public class PipelineOutput {

    private String Engagement_Id;
    private Long Lead_Key_Id;
    private Integer Party_Id;
    private Integer Rank;
    private Integer RankingGroup;
    private String Offer_Code;
    private String Channel;
    private String startDate;
    private String endDate;
    private String pricingData;

    public String getEngagement_Id() {
        return Engagement_Id;
    }

    public void setEngagement_Id(String engagement_Id) {
        Engagement_Id = engagement_Id;
    }

    public Long getLead_Key_Id() {
        return Lead_Key_Id;
    }

    public void setLead_Key_Id(Long lead_Key_Id) {
        Lead_Key_Id = lead_Key_Id;
    }

    public Integer getParty_Id() {
        return Party_Id;
    }

    public void setParty_Id(Integer party_Id) {
        Party_Id = party_Id;
    }

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer rank) {
        Rank = rank;
    }

    public Integer getRankingGroup() {
        return RankingGroup;
    }

    public void setRankingGroup(Integer rankingGroup) {
        RankingGroup = rankingGroup;
    }

    public String getOffer_Code() {
        return Offer_Code;
    }

    public void setOffer_Code(String offer_Code) {
        Offer_Code = offer_Code;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPricingData() {
        return pricingData;
    }

    public void setPricingData(String pricingData) {
        this.pricingData = pricingData;
    }

    public String emailOutputString() {
        return Lead_Key_Id +
                "|" + Party_Id +
                "|" + RankingGroup +
                "|" + Rank +
                "|" + Offer_Code +
                "|" + Channel +
                "|" + startDate +
                "|" + Engagement_Id
                ;
    }

    public String webOutputString() {
        return Lead_Key_Id +
                "|" + Party_Id +
                "|" + RankingGroup +
                "|" + Rank +
                "|" + Offer_Code +
                "|" + Channel +
                "|" + startDate +
                "|" + endDate +
                "|" + Engagement_Id
                ;
    }

    public String emailOutputString_Old() {
        return Lead_Key_Id +
                "," + Party_Id +
                "," + RankingGroup +
                "," + Rank +
                "," + Offer_Code +
                "," + Channel +
                "," + startDate +
                "," + Engagement_Id
                ;
    }

    public String webOutputString_Old() {
        return Lead_Key_Id +
                "," + Party_Id +
                "," + RankingGroup +
                "," + Rank +
                "," + Offer_Code +
                "," + Channel +
                "," + startDate +
                "," + endDate +
                "," + Engagement_Id
                ;
    }

}
