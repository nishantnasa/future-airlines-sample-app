package au.com.future-airlines.bdd.steps;

import au.com.future-airlines.entities.accruals.AccrualsPayload;
import au.com.future-airlines.entities.accruals.MemberProfile;
import au.com.future-airlines.entities.accruals.PartnerTransaction;
import au.com.future-airlines.utils.DateFunctions;
import au.com.future-airlines.utils.HttpClientUtil;
import au.com.future-airlines.utils.PropsUtil;
import au.com.future-airlines.utils.SimuLogger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import junit.framework.Assert;
import net.thucydides.core.Thucydides;
import org.jbehave.core.annotations.Then;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by boses on 14/12/2017.
 */
public class MemberAccrualSteps {


    private static HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, Object> properties = PropsUtil.loadProperties();
    private ResponseEntity<String> models = null;
    DateFunctions dateFunctions = new DateFunctions();

    @Then("I update the future-airlines_POINTS in member details to be base $basePoints and bonus $bonusPoints")
    public void memberAccruals(String basePoints, String bonusPoints){

        Date date = new Date();
        AccrualsPayload accrualsPayload = new AccrualsPayload();
        accrualsPayload.setExternalRefID("AT" + date.getTime()/1000);

        MemberProfile memberProfile = new MemberProfile();
        String memberId = (String) Thucydides.getCurrentSession().get("memberId");
        String lastName = (String) Thucydides.getCurrentSession().get("lastName");
        /*MemberTier tierDetails = memberRetrieveSupport.getFFMemberTierDetails();
        memberProfile.setTier(tierDetails);*/
        memberProfile.setMemberId(memberId);
        memberProfile.setLastName(lastName);

        BigInteger base = BigInteger.valueOf(Long.parseLong(basePoints));
        BigInteger bonus = BigInteger.valueOf(Long.parseLong(bonusPoints));
        BigInteger total = base.add(bonus);

        accrualsPayload.setMemberProfile(memberProfile);
        accrualsPayload.setPartnerID("ACCOR00");
        PartnerTransaction partnerTransaction = new PartnerTransaction();
        partnerTransaction.setProductCode("ACCOR00");    // CAMP01 is for Campaigns ACCOR00 is a Hotel Partner and QLSME01 is the future-airlines Business Rewards program

        partnerTransaction.setBasePoints(base);
        partnerTransaction.setBonusPoints(bonus);
        partnerTransaction.setTotalPoints(total);
        partnerTransaction.setTransactionDate(dateFunctions.getCurrentDateInyyyyMMddFormat());
        partnerTransaction.setDescription("THIS IS TESTING");
        Random rand = new Random();
        Integer num = rand.nextInt(900000000) + 100000000;
        partnerTransaction.setReferenceNumber(num.toString());
        accrualsPayload.setPartnerTransaction(partnerTransaction);

        String authenticationToken = "";
        String accrualsRequest = getPayloadString(accrualsPayload);
        String endPoint = "";
        try {
            authenticationToken = (String) Thucydides.getCurrentSession().get("authenticationToken");
            endPoint = (String) properties.get("realTime.Accruals");
            SimuLogger.LOG.info("URL is : "+ endPoint+ " and Member Id  : "+memberId +" and Auth Token : "+ authenticationToken);

            System.out.println("End point URL is : " + endPoint);
           models = httpClientUtil.executePostWithBasicAuth(endPoint, accrualsRequest);
            System.out.println("models = " + models);
            Thucydides.getCurrentSession().put("models", models);

        } catch (Exception e) {
            e.printStackTrace();
            HttpStatusCodeException exception = (HttpStatusCodeException) e;
            SimuLogger.LOG.info("Failed to Execute POST with the Accruals Request Payload !! " + accrualsRequest);
            Assert.fail("The failure reason is :  \n" + exception.getResponseBodyAsString());
        }


    }


    public String getPayloadString(AccrualsPayload accrualsPayload){

        ObjectMapper mapper = new ObjectMapper();
        // mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);

        String accrualsRequest = null;
        try {
            accrualsRequest = mapper.writeValueAsString(accrualsPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Quotation Request String :: \n " + accrualsRequest);
        return accrualsRequest;
    }


}
