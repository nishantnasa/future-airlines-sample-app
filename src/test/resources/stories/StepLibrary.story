Meta:
@ignore
@pending

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Draft step to Join a Member in iFly system
Given I try to add a member with the following details:
|mp.FirstName|mp.PreferredName|mp.LastName|mp.Email                       |mp.Title|mp.Gender|mp.PreferredAddress|mp.EmailType|mp.DateOfBirth|mp.MotherMaidenName|mp.CountryOfResidency|PIN |ConfirmedPIN|EmailVerification              |CaptchaResponse|cmp.Name|cmp.PositionInCompany|cmp.Abn  |phone1.Type|phone1.Number|phone1.AreaCode|phone1.Idd|mp.pref.airlinesLoyaltyCodes|mp.pref.emailCodes                               |pricing.ProductCode|pricing.PromoCode|pricing.Currency|pricing.TaxResidencyCountry|pricing.FeeStr.FeeCode|pricing.FeeStr.NetAmount|pricing.FeeStr.GstAmount|pricing.FeeStr.GstRate|pricing.FeeStr.ModeOfPaymentType|pricing.FeeStr.TotalPointAmount|pos.StaffId|pos.PointOfInjection|add1.Type|add1.LineOne |add1.Suburb|add1.PostCode|add1.State|add1.CountryCode|Counter|
|PERB        |A               |TESTUSR    |subhadip.bose@future-airlinesloyalty.com|MR      |MALE     |HOME               |HOME        |1985-01-01    |Mom                |AU                   |1234|1234        |subhadip.bose@future-airlinesloyalty.com|               |        |                     |         |HOME       |2417999      |456            |+61       |SQ                          |ENLT,EPIQ,FFOF,NFAM,NTRP,PRNW,QCSH,QECO,QFNW,TRPT|FFJ                |FAMILYFREE       |AUD             |AU                         |FFJOIN                |0                       |0                       |0                     |CASH                            |0.0                            |           |FFSC                |HOME     |1 DUMB STREET|Rockdale   |2216         |NSW       |AU              |0      |

Scenario: Verify that a user is able to Create a card definition and retrieve the same
Given I create a card definition in HUB with the following details:
|type|id         |nbaC.offerVariant|nbaC.channelPlacement|nbaC.registrationReq|nbaC.fulfillmentReq|nbaC.fulfillmentCode|nbaC.logoRequired|nbaC.heroTitle          |nbaC.heroSubtitle                |nbaC.heroImage                                                 |nbaC.heroImageAlt       |nbaC.heroImageLink                                                                                                                                            |nbaC.primaryCTATextWeb|nbaC.primaryCTAAltWeb|nbaC.primaryCTALinkWeb|nbaC.primaryCTALinkCatWeb|nbaC.primaryCTAFulfilFlagWeb|nbaC.footerTerms        |nbaMA.actionId|nbaMA.actionName                                    |nbaMA.contentVersion|nbaMA.delivery|nbaMA.actionType|nbaMA.actionTemplateType|nbaMA.subscriptionBucketPrimary           |nbaMA.exclusions                                                |nbaMA.successMetric|nbaMO.offerStartDate|nbaMO.offerEndDate|nbaMP.productFamily|nbaMP.productVertical|nbaMP.productLabel|nbaMP.productOffering               |info.name                                           |info.contentCategories |info.tags|info.serviceMessage|info.startDate|info.endDate|info.ttl|info.delivery|info.priority|info.sensitiveContent|info.channel|info.feedbackType|info.pool|info.vertical|info.business|info.campaign|info.region|
|nba |NBA-HTAN999|1                |web&email            |0                   |0                  |                    |0                |Need a hotel in the USA?|Earn 3 future-airlines Points per $1 spent|https://www.future-airlines.com/images/per/jpg/homepagepromo.desktop.jpg|Need a hotel in the USA?|https://www.future-airlines.com/au/en/book-a-trip/hotels-and-airbnb.html?alt_cam=au:qd:accommodation:edm:rede:per_20180611_htan012_release-4:n:ff-usa-accommodation:n:n|Search future-airlines Hotels  |Search future-airlines Hotels |Same                  |                         |0                           |You must be a QFF member|HTAN999       |RELEASE_4_Airline_Accommodation_USA_Hotels_Evergreen|1                   |web&email     |offer&evergreen |Loyalty                 |Exclusive offers from our program partners|nonAU&<18 yo&Age unknown&Chairmans Lounge Member - No Promotions|Hotel booking to US|                    |                  |Airline            |Ancillary            |Accommodation     |Airline&Airline_Direct&Accommodation|RELEASE_4_Airline_Accommodation_USA_Hotels_Evergreen|travel&offers-discounts|nba      |false              |              |            |999     |activity_feed|1            |1                    |future-airlines.com  |none             |0        |accommodation|airline      |true         |ALL        |
When I retrieve a card definition from HUB with the generated transaction Id
Then I verify that HUB is publishing the card definition as expected

Scenario: Verify that a user is able to generate input files to trigger Engine
Given I generate the input and output files for engine run with auto-generated offers and frequent flyer members for NEW Code Repo

Scenario: Verify that a user is able to generate input files to trigger Engine
Given I generate the input and output files for engine run against existing offers for 10 frequent flyer members

Scenario: Verify that a user is able to generate input files to trigger Engine
Meta:
@ignore
@pending
Given I generate the input and output files for engine run against existing offers for existing frequent flyer members for OLD Code Repo

Scenario: Test
Given I retrieve the card definitions from HUB for the following Action Id's :
|CardId|
|CHRN001|


Scenario: Verify that a user is able to generate input files to trigger Engine
Given I generate the input and output files for engine run with auto-generated offers and frequent flyer members for NEW Code Repo and delta extract with date 2018-11-30
