package au.com.future-airlines.entities.accruals;


/**
 * Created by boses on 14/12/2017.
 */
public class MemberProfile {

    String memberId;
    String lastName;
    String firstName;
    MemberTier tier;

    public MemberProfile() {
    }

    public MemberProfile(String memberId, String lastName, String firstName, MemberTier tier) {
        this.memberId = memberId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.tier = tier;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public MemberTier getTier() {
        return tier;
    }

    public void setTier(MemberTier tier) {
        this.tier = tier;
    }
}
