package au.com.future-airlines.StepSupport;

import au.com.future-airlines.entities.cardDefinition.CardDefinitionPayload;
import junit.framework.Assert;

import java.util.List;

/**
 * Created by boses on 27/06/2018.
 */
public class VerifyCardDefinitionResponse {



    public void verifyCardDefinitionResponseFromHub(CardDefinitionPayload requestPayload, CardDefinitionPayload cardDefinitionResponsePayload){

        Assert.assertTrue("The Type doesn't match.", performStringValidation(requestPayload.getData().getType(), cardDefinitionResponsePayload.getData().getType()));
        Assert.assertTrue("The Type doesn't match.", performStringValidation(requestPayload.getData().getId(), cardDefinitionResponsePayload.getData().getId()));

        if(requestPayload.getData().getPayload()!=null){

            System.out.println("Validating NBA Content Object !!");
            Assert.assertTrue("The Offer Variant doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getOfferVariant(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getOfferVariant()));
            Assert.assertTrue("The Channel Placement doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getChannelPlacement(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getChannelPlacement()));
            Assert.assertTrue("The Registration Required doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getRegistrationRequired(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getRegistrationRequired()));
            Assert.assertTrue("The Fulfillment Required doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getFulfillmentRequired(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getFulfillmentRequired()));
            Assert.assertTrue("The Fulfillment Code doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getFulfillmentCode(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getFulfillmentCode()));
            Assert.assertTrue("The Logo Required doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getLogoRequired(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getLogoRequired()));
            Assert.assertTrue("The Hero Title doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getHeroTitle(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getHeroTitle()));
            Assert.assertTrue("The Hero Subtitle doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getHeroSubtitle(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getHeroSubtitle()));
            Assert.assertTrue("The Hero Image doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getHeroImage(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getHeroImage()));
            Assert.assertTrue("The Hero Image Alt doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getHeroImageAlt(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getHeroImageAlt()));
            Assert.assertTrue("The Hero Image Link doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getHeroImageLink(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getHeroImageLink()));
            Assert.assertTrue("The Primary CTA Text Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getPrimaryCTATextWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getPrimaryCTATextWeb()));
            Assert.assertTrue("The Primary CTA Alt Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getPrimaryCTAAltWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getPrimaryCTAAltWeb()));
            Assert.assertTrue("The Primary CTA Link Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getPrimaryCTALinkWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getPrimaryCTALinkWeb()));
            Assert.assertTrue("The Primary CTA Link Cat Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getPrimaryCTALinkCatWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getPrimaryCTALinkCatWeb()));
            Assert.assertTrue("The Primary CTA Fulfil Flag Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getPrimaryCTAFulfilFlagWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getPrimaryCTAFulfilFlagWeb()));
            Assert.assertTrue("The Footer Terms doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getFooterTerms(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getFooterTerms()));
            Assert.assertTrue("The Disclaimer HCP doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getDisclaimerHCP(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getDisclaimerHCP()));
            Assert.assertTrue("The Logo Image Path doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getLogoImagePath(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getLogoImagePath()));
            Assert.assertTrue("The Logo Image Alt doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContent().getLogoImageAlt(), cardDefinitionResponsePayload.getData().getPayload().getNbaContent().getLogoImageAlt()));


            System.out.println("Validating NBA Content Post Rego Object !!");
            Assert.assertTrue("The Channel Placement doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getChannelPlacement(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getChannelPlacement()));
            Assert.assertTrue("The Logo Required doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getLogoRequired(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getLogoRequired()));
            Assert.assertTrue("The Hero Title doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getHeroTitle(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getHeroTitle()));
            Assert.assertTrue("The Hero Subtitle doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getHeroSubtitle(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getHeroSubtitle()));
            Assert.assertTrue("The Hero Image doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getHeroImage(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getHeroImage()));
            Assert.assertTrue("The Hero Image Alt doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getHeroImageAlt(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getHeroImageAlt()));
            Assert.assertTrue("The Hero Image Link doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getHeroImageLink(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getHeroImageLink()));
            Assert.assertTrue("The Primary CTA Text Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTATextWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTATextWeb()));
            Assert.assertTrue("The Primary CTA Alt Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTAAltWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTAAltWeb()));
            Assert.assertTrue("The Primary CTA Link Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTALinkWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTALinkWeb()));
            Assert.assertTrue("The Primary CTA Link Cat Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTALinkCatWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTALinkCatWeb()));
            Assert.assertTrue("The Primary CTA Fulfil Flag Web doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTAFulfilFlagWeb(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getPrimaryCTAFulfilFlagWeb()));
            Assert.assertTrue("The Disclaimer HCP doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getDisclaimerHCP(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getDisclaimerHCP()));
            Assert.assertTrue("The Logo Image Path doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getLogoImagePath(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getLogoImagePath()));
            Assert.assertTrue("The Logo Image Alt doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getLogoImageAlt(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getLogoImageAlt()));
            Assert.assertTrue("The Body Copy Rego Details doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaContentPostRego().getBodyCopyRegoDetails(), cardDefinitionResponsePayload.getData().getPayload().getNbaContentPostRego().getBodyCopyRegoDetails()));

            System.out.println("Validating NBA Meta Action Object !!");
            Assert.assertTrue("The Action Id doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionId(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionId()));
            Assert.assertTrue("The Action Name doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionName(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionName()));
            Assert.assertTrue("The Content Id doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getContentId(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getContentId()));
            Assert.assertTrue("The Content Version doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getContentVersion(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getContentVersion()));
            Assert.assertTrue("The Action Mode doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionMode(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionMode()));
            Assert.assertTrue("The Delivery doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getDelivery(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getDelivery()));
            Assert.assertTrue("The Action Type doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionType(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionType()));
            Assert.assertTrue("The Action Parent Ids doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionParentIds(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionParentIds()));
            Assert.assertTrue("The Action Parent Min Followup doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionParenttMinFollowup(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionParenttMinFollowup()));
            Assert.assertTrue("The Action Parent Max Followup doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionParenttMaxFollowup(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionParenttMaxFollowup()));
            Assert.assertTrue("The Action Template Type doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getActionTemplateType(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getActionTemplateType()));
            Assert.assertTrue("The Subscription Bucket Primary doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getSubscriptionBucketPrimary(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getSubscriptionBucketPrimary()));
            Assert.assertTrue("The Exclusions doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getExclusions(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getExclusions()));
            Assert.assertTrue("The Geofence doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getGeofence(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getGeofence()));
            Assert.assertTrue("The Feedback Type doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getFeedbackType(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getFeedbackType()));
            Assert.assertTrue("The Success Metric doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getSuccessMetric(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getSuccessMetric()));
            Assert.assertTrue("The Card Order doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getCardOrder(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getCardOrder()));
            Assert.assertTrue("The Tags for Expansion doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaAction().getTagsForExpansion(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaAction().getTagsForExpansion()));

            System.out.println("Validating NBA Meta Offer Object !!");
            Assert.assertTrue("The Currency doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getCurrency(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getCurrency()));
            Assert.assertTrue("The Incentive Amount doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getIncentiveType(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getIncentiveType()));
            Assert.assertTrue("The Incentive Amount doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getIncentiveAmount(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getIncentiveAmount()));
            Assert.assertTrue("The Is Rolling Purchase doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getIsRollingPurchase(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getIsRollingPurchase()));
            Assert.assertTrue("The Purchase Eligibility Window doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getPurchaseEligibilityWindow(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getPurchaseEligibilityWindow()));
            Assert.assertTrue("The Purchase Start Date doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getPurchaseStartDate(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getPurchaseStartDate()));
            Assert.assertTrue("The Purchase End Date doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getPurchaseEndDate(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getPurchaseEndDate()));
            Assert.assertTrue("The Offer Start Date doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getOfferStartDate(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getOfferStartDate()));
            Assert.assertTrue("The Offer End Date doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getOfferEndDate(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getOfferEndDate()));
            Assert.assertTrue("The Offer Conditions doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getOfferConditions(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getOfferConditions()));
            Assert.assertTrue("The Is Rolling Travel doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getIsRollingTravel(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getIsRollingTravel()));
            Assert.assertTrue("The Travel Eligibility Window doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getTravelEligibilityWindow(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getTravelEligibilityWindow()));
            Assert.assertTrue("The Travel Start Date doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getTravelStartDate(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getTravelStartDate()));
            Assert.assertTrue("The Travel End Date doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getTravelEndDate(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getTravelEndDate()));
            Assert.assertTrue("The Days Until Purchase Start doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getDaysUntilPurchaseStart(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getDaysUntilPurchaseStart()));
            Assert.assertTrue("The Days Until Travel Start doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getDaysUntilTravelStart(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getDaysUntilTravelStart()));
            Assert.assertTrue("The Flight Origin doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getFlightOrigin(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getFlightOrigin()));
            Assert.assertTrue("The Flight Destination doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getFlightDestination(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getFlightDestination()));
            Assert.assertTrue("The Flight Cabin doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getFlightCabin(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getFlightCabin()));
            Assert.assertTrue("The Flight Type doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getFlightType(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getFlightType()));
            Assert.assertTrue("The Flight Terms doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaOffer().getFlightTerms(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaOffer().getFlightTerms()));

            System.out.println("Validating NBA Meta Product Object !!");
            Assert.assertTrue("The Product Family doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaProduct().getProductFamily(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaProduct().getProductFamily()));
            Assert.assertTrue("The Product Vertical doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaProduct().getProductVertical(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaProduct().getProductVertical()));
            Assert.assertTrue("The Product Label doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaProduct().getProductLabel(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaProduct().getProductLabel()));
            Assert.assertTrue("The Product Brand doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaProduct().getProductBrand(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaProduct().getProductBrand()));
            Assert.assertTrue("The Product SKU doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaProduct().getProductSKU(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaProduct().getProductSKU()));
            Assert.assertTrue("The Product Offering doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaProduct().getProductOffering(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaProduct().getProductOffering()));

            System.out.println("Validating NBA Meta Tags Object !!");
            Assert.assertTrue("The Content Theme doesn't match.", performStringValidation(requestPayload.getData().getPayload().getNbaMetaTags().getContentTheme(), cardDefinitionResponsePayload.getData().getPayload().getNbaMetaTags().getContentTheme()));

            System.out.println("Validating Info Object !!");
            Assert.assertTrue("The Name doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getName(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getName()));
            Assert.assertTrue("The Content Categories List doesn't match.", performListOfStringValidation(requestPayload.getData().getPayload().getInfo().getContentCategories(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getContentCategories()));
            Assert.assertTrue("The List of Tags doesn't match.", performListOfStringValidation(requestPayload.getData().getPayload().getInfo().getTags(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getTags()));
            Assert.assertTrue("The Service Message Flag doesn't match.", requestPayload.getData().getPayload().getInfo().getServiceMessage().equals(cardDefinitionResponsePayload.getData().getPayload().getInfo().getServiceMessage()));
            Assert.assertTrue("The Start Date doesn't match.", requestPayload.getData().getPayload().getInfo().getStartDate().intValue() == cardDefinitionResponsePayload.getData().getPayload().getInfo().getStartDate().intValue());
            Assert.assertTrue("The End Date doesn't match.", requestPayload.getData().getPayload().getInfo().getEndDate().intValue() == cardDefinitionResponsePayload.getData().getPayload().getInfo().getEndDate().intValue());
            Assert.assertTrue("The TTL doesn't match.", requestPayload.getData().getPayload().getInfo().getTtl().intValue() == cardDefinitionResponsePayload.getData().getPayload().getInfo().getTtl().intValue());
            Assert.assertTrue("The Delivery doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getDelivery(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getDelivery()));
            Assert.assertTrue("The Priority doesn't match.", requestPayload.getData().getPayload().getInfo().getPriority()==cardDefinitionResponsePayload.getData().getPayload().getInfo().getPriority());
            Assert.assertTrue("The Sensitive Content doesn't match.", requestPayload.getData().getPayload().getInfo().getSensitiveContent()==cardDefinitionResponsePayload.getData().getPayload().getInfo().getSensitiveContent());
            Assert.assertTrue("The Target Audience doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getTargetAudience(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getTargetAudience()));
            Assert.assertTrue("The Channel List doesn't match.", performListOfStringValidation(requestPayload.getData().getPayload().getInfo().getChannel(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getChannel()));
            Assert.assertTrue("The T&C's doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getTermsAndConditions(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getTermsAndConditions()));
            Assert.assertTrue("The Feedback Type doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getFeedbackType(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getFeedbackType()));
            Assert.assertTrue("The Pool doesn't match.", requestPayload.getData().getPayload().getInfo().getPool()== cardDefinitionResponsePayload.getData().getPayload().getInfo().getPool());
            Assert.assertTrue("The Vertical doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getVertical(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getVertical()));
            Assert.assertTrue("The Business doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getBusiness(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getBusiness()));
            Assert.assertTrue("The Display Condition List doesn't match.", performListOfStringValidation(requestPayload.getData().getPayload().getInfo().getDisplayCondition(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getDisplayCondition()));
            Assert.assertTrue("The Campaign Flag doesn't match.", requestPayload.getData().getPayload().getInfo().getCampaign().equals(cardDefinitionResponsePayload.getData().getPayload().getInfo().getCampaign()));
            Assert.assertTrue("The Geofence List doesn't match.", performListOfStringValidation(requestPayload.getData().getPayload().getInfo().getGeofence(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getGeofence()));
            Assert.assertTrue("The Region doesn't match.", performStringValidation(requestPayload.getData().getPayload().getInfo().getRegion(), cardDefinitionResponsePayload.getData().getPayload().getInfo().getRegion()));

        }

        System.out.println("Congratulations !! Content Definition validation has been successful !!");
    }


    public boolean performStringValidation(String expectedString, String actualString){
        boolean result = false;
        if(expectedString.equalsIgnoreCase(actualString)){
            result = true;
        } else {
            System.out.println("Expected : " + expectedString + "  where as Actual Received : " + actualString);
        }
        return result;
    }

    public boolean performListOfStringValidation(List<String> expectedStringList, List<String> actualStringList){
        boolean result = true;

        Assert.assertEquals("The number of records in the list does not match!!", expectedStringList.size(),actualStringList.size());

        for (String s : expectedStringList) {
            if(!actualStringList.contains(s)){
                result = false;
                return result;
            }

        }
        return result;
    }

}
