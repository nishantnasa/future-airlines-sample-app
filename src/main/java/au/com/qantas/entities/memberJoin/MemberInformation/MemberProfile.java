package au.com.future-airlines.entities.memberJoin.MemberInformation;

import java.util.List;

/**
 * Created by boses on 29/08/2017.
 */
public class MemberProfile {

    public String firstName;
    public String preferredName;
    public String lastName;
    public String email;
    public String title;
    public String gender;

    public Company company;
    public List<Phone> phoneDetails;
    public List<Address> addresses;
    public String preferredAddress;
    public String emailType;
    public Preferences preferences;
    public String dateOfBirth;    /*  Example:  "1990-01-03" */
    public String motherMaidenName;
    public String countryOfResidency;   /*  Example:  "AU" */
    public ProgramDetail programDetail;


    public MemberProfile() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Phone> getPhoneDetails() {
        return phoneDetails;
    }

    public void setPhoneDetails(List<Phone> phoneDetails) {
        this.phoneDetails = phoneDetails;
    }

    public String getPreferredAddress() {
        return preferredAddress;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setPreferredAddress(String preferredAddress) {
        this.preferredAddress = preferredAddress;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getCountryOfResidency() {
        return countryOfResidency;
    }

    public void setCountryOfResidency(String countryOfResidency) {
        this.countryOfResidency = countryOfResidency;
    }

    public ProgramDetail getProgramDetail() {
        return programDetail;
    }

    public void setProgramDetail(ProgramDetail programDetail) {
        this.programDetail = programDetail;
    }
}
