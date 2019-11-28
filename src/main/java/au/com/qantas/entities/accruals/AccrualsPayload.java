package au.com.future-airlines.entities.accruals;

import java.math.BigInteger;

/**
 * Created by boses on 14/12/2017.
 */
public class AccrualsPayload {

    public String externalRefID;
    public MemberProfile memberProfile;
    public String partnerID;
    public PartnerTransaction partnerTransaction;

    public AccrualsPayload() {
    }

    public AccrualsPayload(String externalRefID, MemberProfile memberProfile, String partnerID, PartnerTransaction partnerTransaction) {
        this.externalRefID = externalRefID;
        this.memberProfile = memberProfile;
        this.partnerID = partnerID;
        this.partnerTransaction = partnerTransaction;
    }

    public String getExternalRefID() {
        return externalRefID;
    }

    public void setExternalRefID(String externalRefID) {
        this.externalRefID = externalRefID;
    }

    public MemberProfile getMemberProfile() {
        return memberProfile;
    }

    public void setMemberProfile(MemberProfile memberProfile) {
        this.memberProfile = memberProfile;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public PartnerTransaction getPartnerTransaction() {
        return partnerTransaction;
    }

    public void setPartnerTransaction(PartnerTransaction partnerTransaction) {
        this.partnerTransaction = partnerTransaction;
    }
}
