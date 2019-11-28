package au.com.future-airlines.entities.memberJoin.MemberInformation;

/**
 * Created by boses on 29/08/2017.
 */
public class Address {

    public String type;
    public String lineOne;
    public String suburb;
    public String postCode;
    public String state;
    public String countryCode;

    public Address() {
    }

    public Address(String type, String lineOne, String suburb, String postCode, String state, String countryCode) {
        this.type = type;
        this.lineOne = lineOne;
        this.suburb = suburb;
        this.postCode = postCode;
        this.state = state;
        this.countryCode = countryCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
