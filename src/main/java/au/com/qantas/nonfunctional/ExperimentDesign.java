package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 22/08/2018.
 */
public class ExperimentDesign {

    private Integer prim_party_id;
    private String action_group_type;
    private String number_of_actions;

    public Integer getPrim_party_id() {
        return prim_party_id;
    }

    public void setPrim_party_id(Integer prim_party_id) {
        this.prim_party_id = prim_party_id;
    }

    public String getAction_group_type() {
        return action_group_type;
    }

    public void setAction_group_type(String action_group_type) {
        this.action_group_type = action_group_type;
    }

    public String getNumber_of_actions() {
        return number_of_actions;
    }

    public void setNumber_of_actions(String number_of_actions) {
        this.number_of_actions = number_of_actions;
    }

    @Override
    public String toString() {
        return prim_party_id +
                "|" + action_group_type +
                "|" + number_of_actions;
    }
}
