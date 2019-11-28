package au.com.future-airlines.entities.memberJoin.MemberInformation;

/**
 * Created by boses on 13/10/2017.
 */
public class ProgramDetail {

    public String programCode;
    public String programName;
    public String enrollmentDate;
    public String accountStatus;
    public String expiryDate;
    public String qcEnrolType;

    public ProgramDetail() {
    }

    public ProgramDetail(String programCode, String programName, String enrollmentDate, String accountStatus, String expiryDate, String qcEnrolType) {
        this.programCode = programCode;
        this.programName = programName;
        this.enrollmentDate = enrollmentDate;
        this.accountStatus = accountStatus;
        this.expiryDate = expiryDate;
        this.qcEnrolType = qcEnrolType;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getQcEnrolType() {
        return qcEnrolType;
    }

    public void setQcEnrolType(String qcEnrolType) {
        this.qcEnrolType = qcEnrolType;
    }
}
