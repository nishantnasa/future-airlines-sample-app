package au.com.future-airlines.bdd.steps;

import au.com.future-airlines.entities.memberJoin.MemberInformation.*;
import au.com.future-airlines.entities.memberJoin.MemberJoinPayload;
import au.com.future-airlines.entities.memberJoin.PointOfSale;
import au.com.future-airlines.entities.memberJoin.PricingInformation.FeesStructure;
import au.com.future-airlines.entities.memberJoin.PricingInformation.Pricing;
import au.com.future-airlines.utils.HttpClientUtil;
import au.com.future-airlines.utils.PropsUtil;
import au.com.future-airlines.utils.SimuLogger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.thucydides.core.Thucydides;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * Created by boses on 29/08/2017.
 */
public class MemberJoinSteps {


    private static HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, Object> properties = PropsUtil.loadProperties();
    private static String memberJoinApi;

    @Given("I try to add a member with the following details: $dataTable")
    public void addMember(ExamplesTable dataTable) {

        String payload = "";
        Map<String,String> FFMemberDetails = new HashMap<String, String>();
        String FFId = "";
        for (Map<String, String> row : dataTable.getRows()) {
            Integer counter = 0;
            if(row.get("Counter")!=null){
                counter = Integer.parseInt(row.get("Counter"));
            }

            for(int i = 0; i<=counter; i++){
                payload = getMemberJoinIniFlyPayload(row, convert(i));
                System.out.println("Payload : " + payload);
                FFId = addAMemberInIFly(payload);
                if (FFId != null) {
                    FFMemberDetails.put(FFId, row.get("PIN"));
                }
            }

        }

        for (Map.Entry<String, String> entry : FFMemberDetails.entrySet()) {
            System.out.println("FF Member Id : " + entry.getKey() + " PIN : " + entry.getValue());
        }
    }

    public String addAMemberInIFly(String payload){
        properties = PropsUtil.loadProperties();
        memberJoinApi = (String) properties.get("member.join");
//        memberJoinApi = (String) properties.get("member.program.join");

        try {
            SimuLogger.LOG.info("URL is : {} "+ memberJoinApi +" Payload : {} "+payload);

            System.out.println("memberJoinApi = " + memberJoinApi);
            ResponseEntity<String> response = httpClientUtil.executePostAndFetchResponse(memberJoinApi, payload);
            System.out.println("response : " + response.getBody().toString());
            String[] parsedResponse = response.getBody().toString().split("/");
            System.out.println("Generated FF Member Id : " + parsedResponse[3]);
            Thucydides.getCurrentSession().put("memberJoinResponse", response);
            return parsedResponse[3];
        } catch (Exception e) {
            System.out.println(e.getMessage());
            SimuLogger.LOG.info("Member creation failed for : "+ payload);
        }
        return null;
    }

    public String getMemberJoinIniFlyPayload(Map<String, String> row, String appender){

        MemberJoinPayload memberJoinPayload = new MemberJoinPayload();

            MemberProfile memberProfile = new MemberProfile();

            memberProfile.setFirstName(row.get("mp.FirstName") + appender);
            memberProfile.setPreferredName(row.get("mp.PreferredName"));
            memberProfile.setLastName(row.get("mp.LastName"));
            memberProfile.setEmail(row.get("mp.Email"));
            memberProfile.setTitle(row.get("mp.Title"));
            memberProfile.setGender(row.get("mp.Gender"));
            memberProfile.setPreferredAddress(row.get("mp.PreferredAddress"));
            memberProfile.setEmailType(row.get("mp.EmailType"));
            memberProfile.setDateOfBirth(row.get("mp.DateOfBirth"));
            memberProfile.setMotherMaidenName(row.get("mp.MotherMaidenName"));
            memberProfile.setCountryOfResidency(row.get("mp.CountryOfResidency"));

            Company company = new Company();
            company.setName(row.get("cmp.Name"));
            company.setPositionInCompany(row.get("cmp.PositionInCompany"));
            company.setAbn(row.get("cmp.Abn"));

            memberProfile.setCompany(company);

            Phone phone = new Phone();
            List<Phone> phones = new ArrayList();
            phone.setType(row.get("phone1.Type"));
            phone.setNumber(row.get("phone1.Number"));
            phone.setAreaCode(row.get("phone1.AreaCode"));
            phone.setIdd(row.get("phone1.Idd"));
            phones.add(phone);

            memberProfile.setPhoneDetails(phones);

            Address address = new Address();
            List<Address> addresses = new ArrayList();
            address.setType(row.get("add1.Type"));
            address.setLineOne(row.get("add1.LineOne"));
            address.setSuburb(row.get("add1.Suburb"));
            address.setPostCode(row.get("add1.PostCode"));
            address.setState(row.get("add1.State"));
            address.setCountryCode(row.get("add1.CountryCode"));
            addresses.add(address);
            memberProfile.setAddresses(addresses);

            Preferences preferences = new Preferences();
            // Arrays.asList(row.get("mp.pref.airlinesLoyaltyCodes").split(","));
            preferences.setAirlinesLoyaltyCodes(Arrays.asList(row.get("mp.pref.airlinesLoyaltyCodes").split(",")));
            preferences.setEmailCodes(Arrays.asList(row.get("mp.pref.emailCodes").split(",")));
            memberProfile.setPreferences(preferences);

            ProgramDetail programDetail = new ProgramDetail();
            programDetail.setAccountStatus("ACTIVE");
            programDetail.setProgramCode("QCASH");
            programDetail.setProgramName("future-airlines Cash");
            programDetail.setEnrollmentDate("2018-06-21");
            programDetail.setExpiryDate("2023-04-30");
    //        programDetail.setQcEnrolType();
            memberProfile.setProgramDetail(programDetail);

            Pricing pricing = new Pricing();
            pricing.setProductCode(row.get("pricing.ProductCode"));
            pricing.setPromoCode(row.get("pricing.PromoCode"));
            pricing.setCurrency(row.get("pricing.Currency"));
            pricing.setTaxResidencyCountry(row.get("pricing.TaxResidencyCountry"));

            FeesStructure feesStructure = new FeesStructure();
            feesStructure.setFeeCode(row.get("pricing.FeeStr.FeeCode"));
            feesStructure.setGstAmount(Double.parseDouble(row.get("pricing.FeeStr.GstAmount")));
            feesStructure.setGstRate(Double.parseDouble(row.get("pricing.FeeStr.GstRate")));
            feesStructure.setModeOfPaymentType(row.get("pricing.FeeStr.ModeOfPaymentType"));
            feesStructure.setNetAmount(Double.parseDouble(row.get("pricing.FeeStr.NetAmount")));
            feesStructure.setTotalPointAmount(Double.parseDouble(row.get("pricing.FeeStr.TotalPointAmount")));

            pricing.setFeesStructure(feesStructure);

            PointOfSale pointOfSale = new PointOfSale();
            pointOfSale.setPointOfInjection(row.get("pos.PointOfInjection"));
            pointOfSale.setStaffId(row.get("pos.StaffId"));

            memberJoinPayload.setMemberProfile(memberProfile);
            memberJoinPayload.setPricing(pricing);
            memberJoinPayload.setPointOfSale(pointOfSale);

            memberJoinPayload.setPin(Integer.parseInt(row.get("PIN")));
            memberJoinPayload.setConfirmedPin(Integer.parseInt(row.get("ConfirmedPIN")));
            memberJoinPayload.setEmailVerification(row.get("EmailVerification"));
            memberJoinPayload.setCaptchaResponse(row.get("CaptchaResponse"));


        ObjectMapper mapper = new ObjectMapper();
        // mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);

        String memberJoinPayloadString = null;
        try {
            memberJoinPayloadString = mapper.writeValueAsString(memberJoinPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Member Join Payload String :: \n " + memberJoinPayloadString);
        return memberJoinPayloadString;

    }


    @Then("I verify the response received from the member join service")
    public void verifyResponse() {
        verifyMemberJoinResponseAndPickFFId();
    }

    public void verifyMemberJoinResponseAndPickFFId(){

        ResponseEntity<String>  response = (ResponseEntity<String>) Thucydides.getCurrentSession().get("memberJoinResponse");
        System.out.println("Response : " + response);

        if(response.getStatusCode().value()==400){
            if(response.getBody().contains("The Member you are trying to create already exist.")){
                System.out.println("The Member you are trying to create already exist.");
            } else {
                System.out.println("The payload has errors !!");
            }

        }

    }


    final private  static String[] units = {"Zero","One","Two","Three","Four",
            "Five","Six","Seven","Eight","Nine","Ten",
            "Eleven","Twelve","Thirteen","Fourteen","Fifteen",
            "Sixteen","Seventeen","Eighteen","Nineteen"};
    final private static String[] tens = {"","","Twenty","Thirty","Forty","Fifty",
            "Sixty","Seventy","Eighty","Ninety"};


    public static String convert(Integer i) {
        //
        if( i < 20)  return units[i];
        if( i < 100) return tens[i/10] + ((i % 10 > 0)? "" + convert(i % 10):"");
        if( i < 1000) return units[i/100] + "Hundred" + ((i % 100 > 0)?"" + convert(i % 100):"");
        if( i < 1000000) return convert(i / 1000) + "Thousand " + ((i % 1000 > 0)? "" + convert(i % 1000):"") ;
        return convert(i / 1000000) + "Million" + ((i % 1000000 > 0)? "" + convert(i % 1000000):"") ;
    }


}
