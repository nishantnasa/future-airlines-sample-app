package au.com.future-airlines.entities.accruals;

import java.math.BigInteger;

/**
 * Created by boses on 14/12/2017.
 */
public class PartnerTransaction {
    public BigInteger basePoints;
    public BigInteger bonusPoints;
    public String description;
    public String productCode;
    public String referenceNumber;
    public BigInteger totalPoints;
    public String transactionDate;

    public PartnerTransaction() {
    }

    public PartnerTransaction(BigInteger basePoints, BigInteger bonusPoints, String description, String productCode, String referenceNumber, BigInteger totalPoints, String transactionDate) {
        this.basePoints = basePoints;
        this.bonusPoints = bonusPoints;
        this.description = description;
        this.productCode = productCode;
        this.referenceNumber = referenceNumber;
        this.totalPoints = totalPoints;
        this.transactionDate = transactionDate;
    }

    public BigInteger getBasePoints() {
        return basePoints;
    }

    public void setBasePoints(BigInteger basePoints) {
        this.basePoints = basePoints;
    }

    public BigInteger getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(BigInteger bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public BigInteger getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(BigInteger totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
