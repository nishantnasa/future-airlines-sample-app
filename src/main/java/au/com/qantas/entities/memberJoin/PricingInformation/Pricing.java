package au.com.future-airlines.entities.memberJoin.PricingInformation;

/**
 * Created by boses on 29/08/2017.
 */
public class Pricing {

    public String productCode;
    public String promoCode;
    public String currency;
    public String taxResidencyCountry;
    public FeesStructure feesStructure;

    public Pricing() {
    }

    public Pricing(String productCode, String promoCode, String currency, String taxResidencyCountry, FeesStructure feesStructure) {
        this.productCode = productCode;
        this.promoCode = promoCode;
        this.currency = currency;
        this.taxResidencyCountry = taxResidencyCountry;
        this.feesStructure = feesStructure;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxResidencyCountry() {
        return taxResidencyCountry;
    }

    public void setTaxResidencyCountry(String taxResidencyCountry) {
        this.taxResidencyCountry = taxResidencyCountry;
    }

    public FeesStructure getFeesStructure() {
        return feesStructure;
    }

    public void setFeesStructure(FeesStructure feesStructure) {
        this.feesStructure = feesStructure;
    }
}
