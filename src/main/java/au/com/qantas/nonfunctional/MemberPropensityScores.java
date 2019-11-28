package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 20/07/2018.
 */
public class MemberPropensityScores {

    private Integer PARTY_ID;
    private String PropensityScore;
    private String Vertical;
    private String PropensityModel;


    public MemberPropensityScores() {
    }

    public Integer getPARTY_ID() {
        return PARTY_ID;
    }

    public void setPARTY_ID(Integer PARTY_ID) {
        this.PARTY_ID = PARTY_ID;
    }

    public String getPropensityScore() {
        return PropensityScore;
    }

    public void setPropensityScore(String propensityScore) {
        PropensityScore = propensityScore;
    }

    public String getVertical() {
        return Vertical;
    }

    public void setVertical(String vertical) {
        Vertical = vertical;
    }

    public String getPropensityModel() {
        return PropensityModel;
    }

    public void setPropensityModel(String propensityModel) {
        PropensityModel = propensityModel;
    }

    @Override
    public String toString() {
        return PARTY_ID +
                "|" + PropensityScore +
                "|" + Vertical +
                "|" + PropensityModel
                ;
    }

}
