package au.com.future-airlines.StepSupport;

import au.com.future-airlines.entities.cardDefinition.*;
import au.com.future-airlines.utils.HttpClientUtil;
import au.com.future-airlines.utils.PropsUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.thucydides.core.Thucydides;
import org.jbehave.core.model.ExamplesTable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by boses on 26/06/2018.
 */
public class GenerateCardCreationRequest {


    private static HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, Object> properties = PropsUtil.loadProperties();


    public ResponseEntity<String> createCardDefinitionInHub(ExamplesTable dataTable){

        String endPoint = (String) properties.get("baseUrl") + properties.get("create.Card.Definition");

        ResponseEntity<String> response = httpClientUtil.executePostWithBasicAuth(endPoint, generateTheJsonBodyForDefinitionCreation(dataTable));

        return response;
    }

    public ResponseEntity<String> retrieveCardDefinitionFromHub(String transactionId){

        String endPoint = (String) properties.get("baseUrl") + properties.get("get.Card.Definition.Single") + transactionId;

        ResponseEntity<String> response = httpClientUtil.executeGet(endPoint);

        return response;
    }

    public String generateTheJsonBodyForDefinitionCreation(ExamplesTable dataTable) {
        String request = "";

        for (Map<String, String> row : dataTable.getRows()) {
            CardDefinitionPayload cdp = new CardDefinitionPayload();

            Data data = new Data();
            data.setType(row.get("type"));
            String transactionId = row.get("id");
            data.setId(transactionId);
            Thucydides.getCurrentSession().put("transactionId", transactionId);

            Payload payload = new Payload();
            NbaContent nbaContent = new NbaContent();
            nbaContent.setOfferVariant(row.get("nbaC.offerVariant"));
            String channelPlacement = row.get("nbaC.channelPlacement").replace("&", "|");
            nbaContent.setChannelPlacement(channelPlacement);
            nbaContent.setRegistrationRequired(row.get("nbaC.registrationReq"));
            nbaContent.setFulfillmentRequired(row.get("nbaC.fulfillmentReq"));
            nbaContent.setFulfillmentCode(" ");
            nbaContent.setLogoRequired(row.get("nbaC.logoRequired"));
            nbaContent.setHeroTitle(row.get("nbaC.heroTitle"));
            nbaContent.setHeroSubtitle(row.get("nbaC.heroSubtitle"));
            nbaContent.setHeroImage(row.get("nbaC.heroImage"));
            nbaContent.setHeroImageAlt(row.get("nbaC.heroImageAlt"));
            nbaContent.setHeroImageLink(row.get("nbaC.heroImageLink"));
            nbaContent.setPrimaryCTATextWeb(row.get("nbaC.primaryCTATextWeb"));
            nbaContent.setPrimaryCTAAltWeb(row.get("nbaC.primaryCTAAltWeb"));
            if (row.get("nbaC.primaryCTALinkWeb")!=null) {
                if (row.get("nbaC.primaryCTALinkWeb").equalsIgnoreCase("same")) {
                    nbaContent.setPrimaryCTALinkWeb(row.get("nbaC.heroImageLink"));
                } else {
                    nbaContent.setPrimaryCTALinkWeb(row.get("nbaC.primaryCTALinkWeb"));
                }
            }
            nbaContent.setPrimaryCTALinkCatWeb(row.get("nbaC.primaryCTALinkCatWeb"));
            nbaContent.setPrimaryCTAFulfilFlagWeb(row.get("nbaC.primaryCTAFulfilFlagWeb"));
            nbaContent.setFooterTerms(row.get("nbaC.footerTerms"));
            nbaContent.setDisclaimerHCP(" ");
            nbaContent.setLogoImagePath(" ");
            nbaContent.setLogoImageAlt(" ");
            nbaContent.setBodyCopyRegoDetails(" ");

            payload.setNbaContent(nbaContent);

            NbaContentPostRego nbaContentPostRego = new NbaContentPostRego();
            nbaContentPostRego.setChannelPlacement(" ");
            nbaContentPostRego.setLogoRequired("0");
            nbaContentPostRego.setHeroTitle(" ");
            nbaContentPostRego.setHeroSubtitle(" ");
            nbaContentPostRego.setHeroImage(" ");
            nbaContentPostRego.setHeroImageAlt(" ");
            nbaContentPostRego.setHeroImageLink(" ");
            nbaContentPostRego.setPrimaryCTATextWeb(" ");
            nbaContentPostRego.setPrimaryCTAAltWeb(" ");
            nbaContentPostRego.setPrimaryCTALinkWeb(" ");
            nbaContentPostRego.setPrimaryCTALinkCatWeb(" ");
            nbaContentPostRego.setPrimaryCTAFulfilFlagWeb("0");
            nbaContentPostRego.setDisclaimerHCP(" ");
            nbaContentPostRego.setLogoImagePath(" ");
            nbaContentPostRego.setLogoImageAlt(" ");
            nbaContentPostRego.setBodyCopyRegoDetails(" ");

            payload.setNbaContentPostRego(nbaContentPostRego);

            NbaMetaAction nbaMetaAction = new NbaMetaAction();
            nbaMetaAction.setActionId(row.get("nbaMA.actionId"));
            nbaMetaAction.setActionName(row.get("nbaMA.actionName"));
            nbaMetaAction.setContentId(" ");
            nbaMetaAction.setContentVersion(row.get("nbaMA.contentVersion"));
            nbaMetaAction.setActionMode(" ");
            String delivery = row.get("nbaMA.delivery").replace("&", "|");
            nbaMetaAction.setDelivery(delivery);
            String actionType = row.get("nbaMA.actionType").replace("&", "|");
            nbaMetaAction.setActionType(actionType);
            nbaMetaAction.setActionParentIds(" ");
            nbaMetaAction.setActionParenttMinFollowup(" ");
            nbaMetaAction.setActionParenttMaxFollowup(" ");
            nbaMetaAction.setActionTemplateType(row.get("nbaMA.actionTemplateType"));
            nbaMetaAction.setSubscriptionBucketPrimary(row.get("nbaMA.subscriptionBucketPrimary"));
            String exclusions = row.get("nbaMA.exclusions").replace("&", "|");
            nbaMetaAction.setExclusions(exclusions);
            nbaMetaAction.setGeofence(" ");
            nbaMetaAction.setFeedbackType(" ");
            nbaMetaAction.setSuccessMetric(row.get("nbaMA.successMetric"));
            nbaMetaAction.setCardOrder(" ");
            nbaMetaAction.setTagsForExpansion(" ");

            payload.setNbaMetaAction(nbaMetaAction);

            NbaMetaOffer nbaMetaOffer = new NbaMetaOffer();
            nbaMetaOffer.setCurrency(" ");
            nbaMetaOffer.setIncentiveType(" ");
            nbaMetaOffer.setIncentiveAmount(" ");
            nbaMetaOffer.setIsRollingPurchase("0");
            nbaMetaOffer.setPurchaseEligibilityWindow(" ");
            nbaMetaOffer.setPurchaseStartDate("2018-05-31T14:00:01.000Z");    // TODO : Generate in this format 2018-05-31T14:00:01.000Z
            nbaMetaOffer.setPurchaseEndDate("2019-03-31T12:59:59.000Z");     // TODO : Generate in this format 2019-03-31T12:59:59.000Z
            nbaMetaOffer.setOfferStartDate(" ");
            nbaMetaOffer.setOfferEndDate(" ");
            nbaMetaOffer.setOfferConditions(" ");
            nbaMetaOffer.setIsRollingTravel("0");
            nbaMetaOffer.setTravelEligibilityWindow(" ");
            nbaMetaOffer.setTravelStartDate(" ");
            nbaMetaOffer.setTravelEndDate(" ");
            nbaMetaOffer.setDaysUntilPurchaseStart(" ");
            nbaMetaOffer.setDaysUntilTravelStart(" ");
            nbaMetaOffer.setFlightOrigin(" ");
            nbaMetaOffer.setFlightDestination(" ");
            nbaMetaOffer.setFlightCabin(" ");
            nbaMetaOffer.setFlightType(" ");
            nbaMetaOffer.setFlightTerms(" ");

            payload.setNbaMetaOffer(nbaMetaOffer);

            NbaMetaProduct nbaMetaProduct = new NbaMetaProduct();
            nbaMetaProduct.setProductFamily(row.get("nbaMP.productFamily"));
            nbaMetaProduct.setProductVertical(row.get("nbaMP.productVertical"));
            nbaMetaProduct.setProductLabel(row.get("nbaMP.productLabel"));
            nbaMetaProduct.setProductBrand(" ");
            nbaMetaProduct.setProductSKU(" ");
            String productOffering = row.get("nbaMP.productOffering").replace("&", "|");
            nbaMetaProduct.setProductOffering(productOffering);

            payload.setNbaMetaProduct(nbaMetaProduct);

            NbaMetaTags nbaMetaTags = new NbaMetaTags();
            nbaMetaTags.setContentTheme(" ");
            payload.setNbaMetaTags(nbaMetaTags);

            Info info = new Info();
            info.setName(row.get("info.name"));
            List<String> contentCategories = Arrays.asList(row.get("info.contentCategories").split("&"));
            info.setContentCategories(contentCategories);
            List<String> tags = Arrays.asList(row.get("info.tags").split("&"));
            info.setTags(tags);
            Boolean serviceMessage = true;
            if (row.get("info.serviceMessage") != null) {
                if (row.get("info.serviceMessage").equalsIgnoreCase("false")) {
                    serviceMessage = false;
                } else {
                    serviceMessage = true;
                }
            }
            info.setServiceMessage(serviceMessage);


            info.setStartDate(1527775201000l);    // TODO : Generate in this format 1527775201000
            info.setEndDate(1554040799000l);    // TODO : Generate in this format 1554040799000
            info.setTtl(Integer.parseInt(row.get("info.ttl")));
            info.setDelivery(row.get("info.delivery"));
            info.setPriority(Integer.parseInt(row.get("info.priority")));
            info.setSensitiveContent(Integer.parseInt(row.get("info.sensitiveContent")));
            info.setTargetAudience("");
            List<String> channels = Arrays.asList(row.get("info.channel").split("&"));
            info.setChannel(channels);
            info.setTermsAndConditions("");
            info.setFeedbackType(row.get("info.feedbackType"));
            info.setPool(Integer.parseInt(row.get("info.pool")));
            info.setVertical(row.get("info.vertical"));
            info.setBusiness(row.get("info.business"));
            List<String> displayCondition = Arrays.asList("".split("&"));
            info.setDisplayCondition(displayCondition);
            Boolean campaign = true;
            if (row.get("info.campaign") != null) {
                if (row.get("info.campaign").equalsIgnoreCase("false")) {
                    campaign = false;
                } else {
                    campaign = true;
                }
            }
            info.setCampaign(campaign);
            List<String> geoFence = new ArrayList<String>();
            info.setGeofence(geoFence);
            info.setRegion(row.get("info.region"));

            payload.setInfo(info);
            data.setPayload(payload);
            cdp.setData(data);


        ObjectMapper mapper = new ObjectMapper();
        // mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);

        try {
            request = mapper.writeValueAsString(cdp);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Card Definition Request Payload String :: \n " + request);
        Thucydides.getCurrentSession().put("CardCreationRequest", cdp);

        }


        return request;
    }



    public ResponseEntity<String> createCardInHub(String offer){

        String endPoint = "https://staging.unique.future-airlines.com/hub/v1/card-definitions";

        ResponseEntity<String> response = httpClientUtil.executePostWithBasicAuth(endPoint, offer);

        return response;
    }

}
