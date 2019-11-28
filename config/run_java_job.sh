#!/bin/bash -e

chown -R jenkins:jenkins ${SIMULATOR_HOME}
# mvn clean install -Djbehave.story.name=$JBEHAVE_STORY -Dspring.profiles.active=$ENVIRONMENT -Dfile.Run.Date=$DATE_GENERATED -Ddummy.Offer.Count=$OFFER_COUNT -DOffer.per.Member=$OFFER_ALLOCATION_PER_MEMBER -Ddummy.FFUser.Count=$FF_USER_COUNT

mvn -q -f ${SIMULATOR_HOME} clean install -X -Djbehave.story.name=$JBEHAVE_STORY -Dspring.profiles.active=$ENVIRONMENT -Dfile.Run.Date=$DATE_GENERATED -Ddummy.Offer.Count=$OFFER_COUNT -DOffer.per.Member=$OFFER_ALLOCATION_PER_MEMBER -Ddummy.FFUser.Count=$FF_USER_COUNT

echo  "Started uploading files to dsc-${ENVIRONMENT}-input bucket !!"

date_input_generated=`TZ=Australia/Sydney date -d ${DATE_GENERATED} +%Y%m%d`

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/action_catalogue_flat/ --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/VHB0002_NBA_OFFR_CTLG_TO_ENGN_${date_input_generated}.txt  s3://dsc-${ENVIRONMENT}-input/action_catalogue_flat/

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/eligibility/ --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/eligibility_offers_${date_input_generated}.csv  s3://dsc-${ENVIRONMENT}-input/eligibility/${DATE_GENERATED}/

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/feedback/action_events/ --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/Action_History/  s3://dsc-${ENVIRONMENT}-input/feedback/action_events/ --recursive

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/ai/click/${date_input_generated} --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/part-00003-5650e57f-7dab-c000.csv  s3://dsc-${ENVIRONMENT}-input/ai/click/${DATE_GENERATED}/

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/ai/conversion/${date_input_generated} --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/part-00004-5650e57f-7dab-c000.csv  s3://dsc-${ENVIRONMENT}-input/ai/conversion/${DATE_GENERATED}/

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/experiment_design_output/ --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/part-00002-faf0774b-f31f-47f2-aaea-27a8927bd4c8-c000.csv  s3://dsc-${ENVIRONMENT}-input/experiment_design_output/

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/customer_model_mapping/ --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/strategic_insights_loy.lts02_click_conversions_v3.csv  s3://dsc-${ENVIRONMENT}-input/customer_model_mapping/

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-input/measurement-team-input/ --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/Measurement_Team_Input.csv  s3://dsc-${ENVIRONMENT}-input/measurement-team-input/

echo  "Finished uploading files to dsc-${ENVIRONMENT}-input bucket !!"

echo  "Started uploading files to dsc-${ENVIRONMENT}-output bucket !!"

date_output_generated=`date +%Y-%m-%d`

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-output/engine-intermediate-output/${date_output_generated}/ --recursive
aws $ENDPOINT_URL s3 cp ./fileContainer/part-00199-9d0a7e0a-529d-40fe-9832-56a27e60e110-c000.csv  s3://dsc-${ENVIRONMENT}-output/engine-intermediate-output/${date_output_generated}/eligibility/check_end/email/
aws $ENDPOINT_URL s3 cp ./fileContainer/part-00000-fe51646d-5948-4db7-91ee-48676a84de89-c000.csv  s3://dsc-${ENVIRONMENT}-output/engine-intermediate-output/${date_output_generated}/eligibility/check_end/web/

aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-output/context-output/ --recursive
aws $ENDPOINT_URL s3 rm s3://dsc-${ENVIRONMENT}-output/dashboard/engine-intermediate-output/${date_output_generated}/ --recursive

echo  "Finished uploading files to dsc-${ENVIRONMENT}-output bucket !!"
