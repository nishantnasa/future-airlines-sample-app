package au.com.future-airlines.entities.memberJoin.MemberInformation;

import java.util.List;

/**
 * Created by boses on 30/08/2017.
 */
public class Preferences {

    public List<String> airlinesLoyaltyCodes;
    public List<String> emailCodes;

    public Preferences() {
    }

    public Preferences(List<String> airlinesLoyaltyCodes, List<String> emailCodes) {
        this.airlinesLoyaltyCodes = airlinesLoyaltyCodes;
        this.emailCodes = emailCodes;
    }

    public List<String> getAirlinesLoyaltyCodes() {
        return airlinesLoyaltyCodes;
    }

    public void setAirlinesLoyaltyCodes(List<String> airlinesLoyaltyCodes) {
        this.airlinesLoyaltyCodes = airlinesLoyaltyCodes;
    }

    public List<String> getEmailCodes() {
        return emailCodes;
    }

    public void setEmailCodes(List<String> emailCodes) {
        this.emailCodes = emailCodes;
    }
}
