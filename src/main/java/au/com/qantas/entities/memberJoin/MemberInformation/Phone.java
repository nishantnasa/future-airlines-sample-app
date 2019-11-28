package au.com.future-airlines.entities.memberJoin.MemberInformation;

/**
 * Created by boses on 29/08/2017.
 */
public class Phone {

    public String type;
    public String number;
    public String areaCode;
    public String idd;

    public Phone() {
    }

    public Phone(String type, String number, String areaCode, String idd) {
        this.type = type;
        this.number = number;
        this.areaCode = areaCode;
        this.idd = idd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }
}
