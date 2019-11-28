#!/bin/bash
chown -R jenkins:jenkins ${SIMULATOR_HOME}
date_input_generated=`TZ=Australia/Sydney date -d ${DATE_GENERATED} +%Y%m%d`
aws $ENDPOINT_URL s3 cp s3://dsc-${ENVIRONMENT}-input/eligibility/${DATE_GENERATED}/eligibility_offers_${date_input_generated}.csv ./fileContainer/eligibility_offers_${date_input_generated}.csv
spark-submit $EXECUTE_RUNNER ${date_input_generated}
aws $ENDPOINT_URL s3 cp ./fileContainer/eligibility_offers_${date_input_generated}_parquet/ s3://dsc-${ENVIRONMENT}-input/eligibility/eligibility_offers_${date_input_generated}_parquet/ --recursive --exclude '*.crc'
