package au.com.future-airlines.entities.memberJoin;

import au.com.future-airlines.entities.memberJoin.MemberInformation.MemberProfile;
import au.com.future-airlines.entities.memberJoin.PricingInformation.Pricing;

/**
 * Created by boses on 29/08/2017.
 */
public class MemberJoinPayload {

    public MemberProfile memberProfile;
    public Pricing pricing;
    public PointOfSale pointOfSale;

    public Integer pin;
    public Integer confirmedPin;
    public String emailVerification;
    public String captchaResponse;


    public MemberJoinPayload() {
    }

    public MemberJoinPayload(MemberProfile memberProfile, Pricing pricing, PointOfSale pointOfSale, Integer pin, Integer confirmedPin, String emailVerification, String captchaResponse) {
        this.memberProfile = memberProfile;
        this.pricing = pricing;
        this.pointOfSale = pointOfSale;
        this.pin = pin;
        this.confirmedPin = confirmedPin;
        this.emailVerification = emailVerification;
        this.captchaResponse = captchaResponse;
    }

    public MemberProfile getMemberProfile() {
        return memberProfile;
    }

    public void setMemberProfile(MemberProfile memberProfile) {
        this.memberProfile = memberProfile;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public PointOfSale getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Integer getConfirmedPin() {
        return confirmedPin;
    }

    public void setConfirmedPin(Integer confirmedPin) {
        this.confirmedPin = confirmedPin;
    }

    public String getEmailVerification() {
        return emailVerification;
    }

    public void setEmailVerification(String emailVerification) {
        this.emailVerification = emailVerification;
    }

    public String getCaptchaResponse() {
        return captchaResponse;
    }

    public void setCaptchaResponse(String captchaResponse) {
        this.captchaResponse = captchaResponse;
    }
}
