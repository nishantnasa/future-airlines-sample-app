package au.com.future-airlines.bdd.steps;

import au.com.future-airlines.StepSupport.GenerateCardCreationRequest;
import au.com.future-airlines.StepSupport.VerifyCardDefinitionResponse;
import au.com.future-airlines.entities.cardDefinition.CardDefinitionPayload;
import au.com.future-airlines.utils.CommonMethods;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.thucydides.core.Thucydides;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by boses on 26/06/2018.
 */
public class CardDefinitionCreationSteps {


    GenerateCardCreationRequest generateCardCreationRequest = new GenerateCardCreationRequest();
    VerifyCardDefinitionResponse verifyCardDefinitionResponse = new VerifyCardDefinitionResponse();
    private CommonMethods _commonMethods = new CommonMethods();

    @Given("I create a card definition in HUB with the following details: $dataTable")
    public void createCardDefinition(ExamplesTable dataTable){

        ResponseEntity<String> response = generateCardCreationRequest.createCardDefinitionInHub(dataTable);
        System.out.println("The response from HUB API is : \n" + response.getBody());
        Thucydides.getCurrentSession().put("CardCreationResponse", response.getBody());
        Assert.assertTrue("The card creation Failed !!", response.getStatusCode().value() != 400);

    }

    @Given("I retrieve a card definition from HUB with the generated transaction Id")
    @When("I retrieve a card definition from HUB with the generated transaction Id")
    public void retrieveCardDefinition(){
//        String definitionId = (String) Thucydides.getCurrentSession().get("transactionId");
        String definitionId = "NBA-HTAN999";
        ResponseEntity<String> response = generateCardCreationRequest.retrieveCardDefinitionFromHub(definitionId);
        Thucydides.getCurrentSession().put("GETCardDefinitionResponse", response);
        System.out.println("The Card Definition Fetch response from HUB API is : \n" + response.getBody());

    }

    @Given("I retrieve the card definitions from HUB for the following Action Id's : $dataTable")
    @When("I retrieve the card definitions from HUB for the following Action Id's : $dataTable")
    public void fetchCardDefinitions(ExamplesTable dataTable){

        String definitionId = "";
        ResponseEntity<String> response = null;
        for (Map<String, String> stringMap : dataTable.getRows()) {
            definitionId =  "NBA-" + stringMap.get("CardId");
            response = generateCardCreationRequest.retrieveCardDefinitionFromHub(definitionId);
//            String  offerText = response.getBody().replace(stringMap.get("CardId"),"QEANTST");
            if (response!=null) {
//                System.out.println("Fetched Card : " + response.getBody());

                //            System.out.println(offerText);
            /*if (response!=null) {
                if (checkIfPatternMatches(response.getBody())) {
                    System.out.println("The Retrieved Card Definition response from HUB API is : \n" + response.getBody());
                    System.out.println("Pattern Found In : " + definitionId);
                } else {
//                    System.out.println("The Retrieved Card Definition response from HUB API is : \n" + response.getBody());
                    System.out.println("Pattern Not Matched in : " + definitionId);
                }
            } else {
                System.out.println("Card Definition not found for : " + definitionId);
            }*/

            ResponseEntity<String> response1 = generateCardCreationRequest.createCardInHub(response.getBody());
                if (response1!=null) {
//                    System.out.println(response1.getBody());
                } else {
                    System.out.println("Card Definition not Created for : " + stringMap.get("CardId"));
                }

            } else {
                System.out.println("Card definition not found for : " + stringMap.get("CardId"));
            }

        }


    }

    public Boolean checkIfPatternMatches(String responseBody) {
        Boolean matches = false;
        if (responseBody.contains("https://www.future-airlines.com/au/en/flight-deals.html/") && !responseBody.contains("https://www.future-airlines.com/au/en/flight-deals.html/any/")) {
            matches = true;
        }
        return matches;
    }


    @Then("I verify that HUB is publishing the card definition as expected")
    public void verifyCardDefinition(){

        CardDefinitionPayload requestPayload = (CardDefinitionPayload) Thucydides.getCurrentSession().get("CardCreationRequest");

        ResponseEntity<String> response = (ResponseEntity<String>) Thucydides.getCurrentSession().get("GETCardDefinitionResponse");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        Gson g = gsonBuilder.create();

        CardDefinitionPayload cardDefinitionResponsePayload = null;
        try {
            cardDefinitionResponsePayload = g.fromJson(response.getBody(), CardDefinitionPayload.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Assert.fail("The payload returned from API is not parsable !!");
        }

        verifyCardDefinitionResponse.verifyCardDefinitionResponseFromHub(requestPayload, cardDefinitionResponsePayload);

    }



    @Given("I retrieve the card definitions from HUB for the following Action Id's and create test Offers : $dataTable")
    public void fetchCardDefinitionsFromHubAndCreateTestOffers(ExamplesTable dataTable){

        String definitionId = "";
        ResponseEntity<String> response = null;

        List<FileWriter> allOfferFileWriters = new ArrayList<>();
        FileWriter writer = null;
        try {
            for (Map<String, String> stringMap : dataTable.getRows()) {
                definitionId =  "NBA-" + stringMap.get("CardId");
                response = generateCardCreationRequest.retrieveCardDefinitionFromHub(definitionId);
                System.out.println("Retrieving Card : " + definitionId);
                if (response != null) {
                    JSONObject jsonObject = new JSONObject(response.getBody());
                    jsonObject.getJSONObject("data").remove("schema");
                    writer = _commonMethods.createOffersJsonFile(stringMap.get("CardId") + ".json");
                    allOfferFileWriters.add(writer);
                    _commonMethods.writeToFile(writer, response.getBody());
                    _commonMethods.flushMultipleFileWriters(allOfferFileWriters);

                } else {
                    System.out.println("Card definition not found for : " + stringMap.get("CardId"));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            _commonMethods.closeMultipleFileWriters(allOfferFileWriters);

        }

    }




}
