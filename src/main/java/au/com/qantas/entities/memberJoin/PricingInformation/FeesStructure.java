package au.com.future-airlines.entities.memberJoin.PricingInformation;

/**
 * Created by boses on 29/08/2017.
 */
public class FeesStructure {

    public String feeCode;
    public Double netAmount;
    public Double gstAmount;
    public Double gstRate;
    public String modeOfPaymentType;
    public Double totalPointAmount;

    public FeesStructure() {
    }

    public FeesStructure(String feeCode, Double netAmount, Double gstAmount, Double gstRate, String modeOfPaymentType, Double totalPointAmount) {
        this.feeCode = feeCode;
        this.netAmount = netAmount;
        this.gstAmount = gstAmount;
        this.gstRate = gstRate;
        this.modeOfPaymentType = modeOfPaymentType;
        this.totalPointAmount = totalPointAmount;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(Double gstAmount) {
        this.gstAmount = gstAmount;
    }

    public Double getGstRate() {
        return gstRate;
    }

    public void setGstRate(Double gstRate) {
        this.gstRate = gstRate;
    }

    public String getModeOfPaymentType() {
        return modeOfPaymentType;
    }

    public void setModeOfPaymentType(String modeOfPaymentType) {
        this.modeOfPaymentType = modeOfPaymentType;
    }

    public Double getTotalPointAmount() {
        return totalPointAmount;
    }

    public void setTotalPointAmount(Double totalPointAmount) {
        this.totalPointAmount = totalPointAmount;
    }
}
