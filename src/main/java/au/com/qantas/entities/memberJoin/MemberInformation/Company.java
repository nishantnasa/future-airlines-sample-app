package au.com.future-airlines.entities.memberJoin.MemberInformation;

/**
 * Created by boses on 29/08/2017.
 */
public class Company {

    public String name;
    public String positionInCompany;
    public String abn;

    public Company() {
    }

    public Company(String name, String positionInCompany, String abn) {
        this.name = name;
        this.positionInCompany = positionInCompany;
        this.abn = abn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionInCompany() {
        return positionInCompany;
    }

    public void setPositionInCompany(String positionInCompany) {
        this.positionInCompany = positionInCompany;
    }

    public String getAbn() {
        return abn;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }
}
