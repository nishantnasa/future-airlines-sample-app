package au.com.future-airlines.entities.accruals;

/**
 * Created by boses on 21/06/2018.
 */
public class MemberTier {
    public String code;
    public String effectiveTier;
    public String expiryDate;
    public String name;

    public MemberTier() {
    }

    public MemberTier(String code, String effectiveTier, String expiryDate, String name) {
        this.code = code;
        this.effectiveTier = effectiveTier;
        this.expiryDate = expiryDate;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEffectiveTier() {
        return effectiveTier;
    }

    public void setEffectiveTier(String effectiveTier) {
        this.effectiveTier = effectiveTier;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
