package au.com.future-airlines.nonfunctional;

import au.com.future-airlines.utils.CommonMethods;
import au.com.future-airlines.utils.DateFunctions;
import net.thucydides.core.Thucydides;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by boses on 30/10/2018.
 */
public class CreateOffers {

    private static final String OFFER_FILE_HEADER = "ACTION_ID|VRS_NUM|PRODUCT_DEPARTMENT|PRODUCT_VERTICAL|PRODUCT_OFFERING|PRODUCT_TAGS|ACTION_TYPE|INCENTIVE_AMOUNT|INCENTIVE_TYPE|OFFER_START_DATE|OFFER_END_DATE|PURCHASE_START_DATE|PURCHASE_END_DATE|TRAVEL_START_DATE|TRAVEL_END_DATE|PURCHASE_ROLLING_FLAG|PURCHASE_ROLLING_START_DAYS|PURCHASE_ROLLING_END_DAYS|TRAVEL_ROLLING_FLAG|TRAVEL_ROLLING_START_DAYS|TRAVEL_ROLLING_END_DAYS|CONTENT_TAGS|JSON_FOR_EXPANSION|OFFER_VARIANT|VERTICAL_FOR_BUSINESS|action_parent_id|action_followup_tmin|action_followup_tmax|ACTION_NAME|PRICING_DATA";
    private static Integer Action_Id_Start = 100000;
    private static Integer Rolling_Dates_Action_Id_Start = 110000;
    private static Map<String, Integer> mapOfProductAndGroup = new HashMap<String, Integer>();
    private static List<Offer> offers = new ArrayList<Offer>();
    private static List<Offer> parentOffers = new ArrayList<Offer>();
    private static CommonMethods commonMethods = new CommonMethods();
    private final String PRODUCT_DEPARTMENT = "Airline,Insurance,EpiQure,Loyalty,Financial_Services,Frequent Flyer,future-airlines_Business_Rewards";  // Retail
    private final String PRICING_DATA_50_TILES = "[{\\\"id\\\": \\\"SYD-SIN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SIN-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SIN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SIN-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CMB-ECO-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CMB-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CMB-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-HKT-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-HKT-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PEN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PEN-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PEN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-MNL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SGN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SGN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-CMB-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-CMB-BUS-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-HKT-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-HKT-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-KUL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-KUL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-KUL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-KUL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-PEN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-PEN-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-PEN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-MNL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-SGN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"ACT-SGN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"MEL-SIN-ECO-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-SIN-PRE-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-SIN-BUS-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-CMB-BUS-N\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-CMB-FIR-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-HKT-ECO-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-HKT-PRE-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-KUL-BUS-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-KUL-ECO-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-KUL-PRE-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-KUL-FIR-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-PEN-ECO-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-PEN-PRE-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-PEN-BUS-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-MNL-ECO-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-MNL-PRE-Y\\\", \\\"state\\\": \\\"VIC\\\"}, {\\\"id\\\": \\\"MEL-MNL-BUS-Y\\\", \\\"state\\\": \\\"VIC\\\"}]";

    private final String PRICING_DATA_150_TILES = "[{\\\"id\\\": \\\"SYD-MNL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SGN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SGN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SIN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SIN-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SIN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CMB-ECO-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CMB-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CMB-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CMB-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-HKT-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-HKT-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-HKT-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-HKT-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-KUL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PEN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PEN-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PEN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PEN-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CNS-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CNS-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CNS-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-CNS-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BNE-ECO-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BNE-PRE-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BNE-BUS-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BNE-FIR-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BME-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BME-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BME-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BME-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DPO-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DPO-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DPO-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DPO-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DRW-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DRW-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DRW-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-DRW-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-OOL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-OOL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-OOL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-OOL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NTL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NTL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NTL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NTL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZNE-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZNE-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZNE-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZNE-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ADL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ADL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ADL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ADL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ABX-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ABX-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ABX-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ABX-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ASP-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ASP-PRE-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ASP-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ASP-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARM-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARM-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARM-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARM-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BCI-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BCI-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BCI-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BCI-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZBL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZBL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZBL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZBL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BKQ-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BKQ-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BKQ-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BKQ-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BLT-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BLT-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BLT-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BLT-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ICN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ICN-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ICN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ICN-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BDB-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BDB-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BDB-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BDB-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BWT-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BWT-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BWT-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-BWT-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZRH-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZRH-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZRH-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ZRH-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-WLG-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-WLG-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-WLG-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-WLG-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-IAD-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-IAD-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-IAD-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-IAD-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-YYZ-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-YYZ-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-YYZ-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-YYZ-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NRT-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NRT-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NRT-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-NRT-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TPE-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TPE-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TPE-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TPE-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-STL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-STL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-STL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-STL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARN-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARN-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARN-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-ARN-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SCL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SCL-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SCL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-SCL-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-FCO-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-FCO-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-FCO-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-FCO-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PRG-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PRG-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PRG-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PRG-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PDX-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PDX-PRE-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PDX-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-PDX-FIR-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TXL-ECO-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TXL-PRE-N\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TXL-BUS-Y\\\", \\\"state\\\": \\\"NSW\\\"}, {\\\"id\\\": \\\"SYD-TXL-FIR-N\\\", \\\"state\\\": \\\"NSW\\\"}]";;

    public FileWriter writer = null;

    DateFunctions dtFunctions = new DateFunctions();

    public List<Offer> createOffer(Integer numberOfOffers) {
        FileWriter actionCatalogFileWriter = null;
        FileWriter variantGroupFileWriter = null;
        try {
            actionCatalogFileWriter = commonMethods.createFile("VHB0002_NBA_OFFR_CTLG_TO_ENGN_" + commonMethods.getFileCreationDate() + ".txt", writer);
            commonMethods.writeToFile(actionCatalogFileWriter, OFFER_FILE_HEADER);
            createSimpleOffers(numberOfOffers);
            createFreshOffers(numberOfOffers);
            createRollingDatesOffers(numberOfOffers);
            markSimpleOffersAsParentsOffers(numberOfOffers);
            createMultiStepOffers();
            // TODO : Enable this to implement offer variants
//            markSimpleOffersAsOfferVariants();
            variantGroupFileWriter = commonMethods.createFile("variant_group.csv", variantGroupFileWriter);
            commonMethods.writeToFile(variantGroupFileWriter, "action_id,variant_family_name");
            generateOfferVariantsConfigFile(variantGroupFileWriter);
            writeAllOffersToActionCatalog(offers, actionCatalogFileWriter);

        } finally {
            commonMethods.closeFileWriter(actionCatalogFileWriter);
            commonMethods.closeFileWriter(variantGroupFileWriter);
            commonMethods.closeFileWriter(writer);
        }
        Thucydides.getCurrentSession().put("offers", offers);
        return offers;
    }

    private void createMultiStepOffers() {
        for (int l = 0; l < parentOffers.size(); l++) {
            Offer multiStepOffer = new Offer();
            parentOffers.get(l).setOFFER_CATEGORY("INITIAL_PARENT");
            multiStepOffer = createMultiStepOffersWithoutHistory(parentOffers.get(l), multiStepOffer);
            offers.add(multiStepOffer);
        }
    }

    public List<Offer> createOfferForExistingActionIds(List<String> actionIdList) {
        List<Offer> offers = new ArrayList<Offer>();
        Integer Action_Id_Start = 100000;
        FileWriter fileWriter = null;
        try {
            fileWriter = commonMethods.createFile("VHB0002_NBA_OFFR_CTLG_TO_ENGN_" + commonMethods.getFileCreationDate() + ".txt", writer);
            commonMethods.writeToFile(fileWriter, OFFER_FILE_HEADER);
            for (String s : actionIdList) {
                Offer offer = new Offer();
                offer.setACTION_ID(s);
                offer.setVRS_NUM("1");
                List<String> listOfDepartments = commonMethods.getListOfStrings(PRODUCT_DEPARTMENT);
                for (int j = 1; j <= listOfDepartments.size(); j++) {
                    mapOfProductAndGroup.put(listOfDepartments.get(j - 1), j);
                }
                Thucydides.getCurrentSession().put("mapOfProductAndGroup", mapOfProductAndGroup);
                String PRODUCT = commonMethods.getRandomValueFromLIstOfString(listOfDepartments);
                offer.setPRODUCT_DEPARTMENT(PRODUCT);
                offer.setOffer_Group_Id(mapOfProductAndGroup.get(PRODUCT));
                offer = createOfferVerticalOfferingAndTags(offer, PRODUCT);
                commonMethods.writeToFile(fileWriter, offer.toString());
                offers.add(offer);

            }


        } finally {
            commonMethods.closeFileWriter(writer);
            commonMethods.closeFileWriter(fileWriter);
        }
        Thucydides.getCurrentSession().put("offers", offers);
        return offers;
    }

    private void writeAllOffersToActionCatalog(List<Offer> offers, FileWriter fileWriter) {
        for (Offer offer : offers) {
            commonMethods.writeToFile(fileWriter, offer.toString());
        }
    }

    private void markSimpleOffersAsParentsOffers(Integer numberOfOffers) {
        int tenPercentOfOffersList = (numberOfOffers * 10) / 100;
        for (int k = 0; k < tenPercentOfOffersList; k++) {
            parentOffers.add(offers.get(k));
        }
    }

    private void markSimpleOffersAsOfferVariants() {
        List<String> offer_Variants = new ArrayList<String>();
        Integer counter = 0;
        for (Offer offer : offers) {
            if (counter == 5) {
                break;
            }
            if (!offer.getOFFER_CATEGORY().equalsIgnoreCase("INITIAL_PARENT") && counter < 5) {
                offer_Variants.add(offer.getACTION_ID());
                offer.setOFFER_VARIANT(offer.getACTION_ID());
                counter += 1;
            }

        }
        for (String offer_variant : offer_Variants) {
            Integer repetition = 0;
            for (int i = offers.size() - 1; i > 0; i--) {
                if (!offers.get(i).getOFFER_CATEGORY().equalsIgnoreCase("INITIAL_PARENT") && !offers.get(i).getOFFER_CATEGORY().equalsIgnoreCase("MULTI_STEP_OFFER") && !offers.get(i).getOFFER_VARIANT().contains("REAN") && repetition < 3) {
                    offers.get(i).setOFFER_VARIANT(offer_variant);
                    repetition += 1;
                }
            }

        }


    }

    private void generateOfferVariantsConfigFile(FileWriter variantGroupFileWriter) {
        List<String> offer_Variants = new ArrayList<String>();
        int counter = 0;
        for (Offer offer : offers) {
            if (counter == 5) {
                break;
            }
            if (!offer.getOFFER_CATEGORY().equalsIgnoreCase("INITIAL_PARENT") && counter < 5) {
                offer_Variants.add(offer.getACTION_ID());
                offer.setOFFER_VARIANT(offer.getACTION_ID());
                commonMethods.writeToFile(variantGroupFileWriter, offer.getACTION_ID() + "," + offer.getACTION_ID());
                counter += 1;
            }
        }
        for (String offer_variant : offer_Variants) {
            int repetition = 0;
            for (int i = offers.size() - 1; i > 0; i--) {
                if (!offers.get(i).getOFFER_CATEGORY().equalsIgnoreCase("INITIAL_PARENT") &&
                        !offers.get(i).getOFFER_CATEGORY().equalsIgnoreCase("MULTI_STEP_OFFER") &&
                        !offers.get(i).getOFFER_VARIANT().contains("REAN") && repetition < 3) {
                    offers.get(i).setOFFER_VARIANT(offer_variant);
                    commonMethods.writeToFile(variantGroupFileWriter, offers.get(i).getACTION_ID() + "," + offer_variant);
                    repetition += 1;
                }
            }
        }
    }

    private void createRollingDatesOffers(int numberOfOffers) {
        Offer rollingOffer;
        int fivePercentOfOffersList = (numberOfOffers * 5) / 100;
        for (int j = 0; j < fivePercentOfOffersList; j++) {
            rollingOffer = createRollingDatesOfferWithoutHistory();
            offers.add(rollingOffer);
        }
    }

    private int createFreshOffers(Integer numberOfOffers) {
        Offer freshOffer;
        int fivePercentOfOffersList = (numberOfOffers * 5) / 100;
        for (int j = 0; j < fivePercentOfOffersList; j++) {
            freshOffer = createFreshOffersWithoutHistory();
            offers.add(freshOffer);
        }
        return fivePercentOfOffersList;
    }

    private void createSimpleOffers(Integer numberOfOffers) {
        Offer offer;
        for (int i = 1; i <= (numberOfOffers * 80) / 100; i++) {
            offer = new Offer();
            Action_Id_Start += 1;
            String ACTION_ID = "REAN" + (Action_Id_Start);
            offer.setACTION_ID(ACTION_ID);
            offer.setVRS_NUM("1");
            List<String> listOfDepartments = commonMethods.getListOfStrings(PRODUCT_DEPARTMENT);
            for (int j = 1; j <= listOfDepartments.size(); j++) {
                mapOfProductAndGroup.put(listOfDepartments.get(j - 1), j);
            }
            Thucydides.getCurrentSession().put("mapOfProductAndGroup", mapOfProductAndGroup);
            String PRODUCT = commonMethods.getRandomValueFromLIstOfString(listOfDepartments);
            offer.setPRODUCT_DEPARTMENT("");
            offer.setOffer_Group_Id(mapOfProductAndGroup.get(PRODUCT));
            offer.setACTION_NAME(ACTION_ID + "_R99_This is Action Name");
            offer.setPRICING_DATA("[]");
            offer = createOfferVerticalOfferingAndTags(offer, PRODUCT);
            offers.add(offer);
        }
    }

    private Offer createOfferCopy(Offer offer) {
        Offer newOffer = new Offer();
        newOffer.setACTION_ID(offer.getACTION_ID());
        newOffer.setAction_followup_tmin(offer.getAction_followup_tmin());
        newOffer.setVRS_NUM(offer.getVRS_NUM());
        newOffer.setAction_Parent_Id(offer.getAction_Parent_Id());
        newOffer.setOFFER_CATEGORY(offer.getOFFER_CATEGORY());
        newOffer.setACTION_TYPE(offer.getACTION_TYPE());
        newOffer.setAction_followup_tmax(offer.getAction_followup_tmax());
        newOffer.setCONTENT_TAGS(offer.getCONTENT_TAGS());
        newOffer.setINCENTIVE_AMOUNT(offer.getINCENTIVE_AMOUNT());
        newOffer.setINCENTIVE_TYPE(offer.getINCENTIVE_TYPE());
        newOffer.setJSON_FOR_EXPANSION(offer.getJSON_FOR_EXPANSION());
        newOffer.setOFFER_END_DATE(offer.getOFFER_END_DATE());
        newOffer.setOffer_Group_Id(offer.getOffer_Group_Id());
        newOffer.setOFFER_START_DATE(offer.getOFFER_START_DATE());
        newOffer.setOFFER_VARIANT(offer.getOFFER_VARIANT());
        newOffer.setPRODUCT_DEPARTMENT(offer.getPRODUCT_DEPARTMENT());
        newOffer.setPRODUCT_OFFERING(offer.getPRODUCT_OFFERING());
        newOffer.setPRODUCT_TAGS(offer.getPRODUCT_TAGS());
        newOffer.setPRODUCT_VERTICAL(offer.getPRODUCT_VERTICAL());
        newOffer.setPURCHASE_START_DATE(offer.getPURCHASE_START_DATE());
        newOffer.setPURCHASE_END_DATE(offer.getPURCHASE_END_DATE());
        newOffer.setPURCHASE_ROLLING_START_DAYS(offer.getPURCHASE_ROLLING_START_DAYS());
        newOffer.setPURCHASE_ROLLING_END_DAYS(offer.getPURCHASE_ROLLING_END_DAYS());
        newOffer.setPURCHASE_ROLLING_FLAG(offer.getPURCHASE_ROLLING_FLAG());
        newOffer.setTRAVEL_END_DATE(offer.getTRAVEL_END_DATE());
        newOffer.setTRAVEL_START_DATE(offer.getTRAVEL_START_DATE());
        newOffer.setTRAVEL_ROLLING_END_DAYS(offer.getTRAVEL_ROLLING_END_DAYS());
        newOffer.setTRAVEL_ROLLING_FLAG(offer.getTRAVEL_ROLLING_FLAG());
        newOffer.setTRAVEL_ROLLING_START_DAYS(offer.getTRAVEL_ROLLING_START_DAYS());
        newOffer.setVERTICAL_FOR_BUSINESS(offer.getVERTICAL_FOR_BUSINESS());
        newOffer.setACTION_NAME(offer.getACTION_NAME());
        newOffer.setPRICING_DATA(offer.getPRICING_DATA());
        return newOffer;
    }

    private Offer createFreshOffersWithoutHistory() {
        Offer newOffer = new Offer();
        Action_Id_Start += 1;
        int index = new Random().nextInt(offers.size());
        newOffer = createOfferCopy(offers.get(index));
        String ACTION_ID = "REAN" + (Action_Id_Start);
        newOffer.setACTION_ID(ACTION_ID);
        newOffer.setPURCHASE_ROLLING_FLAG("0");
        newOffer.setPURCHASE_ROLLING_START_DAYS("0");
        newOffer.setPURCHASE_ROLLING_END_DAYS("0");
        newOffer.setTRAVEL_ROLLING_FLAG("0");
        newOffer.setTRAVEL_ROLLING_START_DAYS("0");
        newOffer.setTRAVEL_ROLLING_END_DAYS("0");
        newOffer.setOFFER_CATEGORY("NEW_OFFERS");
        newOffer = getOfferStartAndEndDates(newOffer);
        return newOffer;
    }

    private Offer createRollingDatesOfferWithoutHistory() {
        Offer rollingOffer = new Offer();
        Rolling_Dates_Action_Id_Start += 1;
        int index = new Random().nextInt(offers.size());
        rollingOffer = createOfferCopy(offers.get(index));
        String ACTION_ID = "REAN" + (Rolling_Dates_Action_Id_Start);
        rollingOffer.setACTION_ID(ACTION_ID);
        rollingOffer.setPURCHASE_ROLLING_FLAG("1");
        rollingOffer.setPURCHASE_ROLLING_START_DAYS("0");
        rollingOffer.setPURCHASE_ROLLING_END_DAYS("7");
        rollingOffer.setTRAVEL_ROLLING_FLAG("1");
        rollingOffer.setTRAVEL_ROLLING_START_DAYS("8");
        rollingOffer.setTRAVEL_ROLLING_END_DAYS("90");
        rollingOffer.setOFFER_CATEGORY("ROLLING_OFFER");
        return rollingOffer;
    }

    private Offer createMultiStepOffersWithoutHistory(Offer parentOffer, Offer multiStepOffer) {
        Action_Id_Start += 1;
        String ACTION_ID = "REAN" + (Action_Id_Start);
        multiStepOffer = createOfferCopy(parentOffer);
        multiStepOffer.setACTION_ID(ACTION_ID);
        multiStepOffer.setAction_Parent_Id(parentOffer.getACTION_ID());
        multiStepOffer.setAction_followup_tmin("14");
        multiStepOffer.setAction_followup_tmax("0");
        multiStepOffer.setOFFER_CATEGORY("MULTI_STEP_OFFER");
        return multiStepOffer;
    }

    private Offer getOfferStartAndEndDates(Offer offer) {
        List<String> offerValidityOptions = commonMethods.getListOfStrings("1_Day,2_Day,3_Day,7_Day");
        String offerValidity = commonMethods.getRandomValueFromLIstOfString(offerValidityOptions);
        List<String> offerStartDateOptions = commonMethods.getListOfStrings("Saturday,Thursday");
        String offerStartDay = commonMethods.getRandomValueFromLIstOfString(offerStartDateOptions);
        Integer offerValidityDays = Integer.parseInt(Arrays.asList(offerValidity.split("_")).get(0));
        LocalDate startDay = null;
        if (offerStartDay.equalsIgnoreCase("Saturday")) {
            startDay = dtFunctions.getNextSaturdayInCurrentWeek();
        } else {
            startDay = dtFunctions.getNextThursdayInNextWeek();
        }
        offer.setOFFER_START_DATE(startDay.toString() + " 00:00:00");
        String endDate = "";
        if (offerValidityDays == 1) {
            endDate = startDay.toString();
        } else if (offerValidityDays == 2) {
            endDate = startDay.plusDays(2l).toString();
        } else if (offerValidityDays == 3) {
            endDate = startDay.plusDays(3l).toString();
        } else if (offerValidityDays == 7) {
            endDate = startDay.plusDays(7l).toString();
        }
        offer.setOFFER_END_DATE(endDate + " 23:59:59");
        // TODO : Comment out the previous block and enable the following 2 lines if you want to generate long validity offers
/*        offer.setOFFER_START_DATE("2018-08-01 00:00:00");
        offer.setOFFER_END_DATE("2018-12-25 23:59:59");*/
        return offer;
    }

    private String getContentTag() {
        String contentTag = "";
        List<String> PERSONA_LIST = commonMethods.getListOfStrings("persona/none,persona/mature_affluence,persona/prosperous_families,persona/none,persona/savvy_retirees,persona/none,persona/young_professionals,persona/urban_achievers,typology/nervous_traditionalists,typology/explorers,loyalty_segments/the_gamers");
        contentTag = commonMethods.getRandomValueFromLIstOfString(PERSONA_LIST);
        return contentTag;
    }

    private String getIncentiveType(String incentive) {
        String type = "";
        String INCENTIVE_TYPE = "bonus_points,price discount,Upgrade";
        List<String> INCENTIVE_TYPE_LIST = commonMethods.getListOfStrings(INCENTIVE_TYPE);
        if (incentive.equalsIgnoreCase("")) {
            type = "no_incentive";
        } else {
            Integer incentiveVal = Integer.parseInt(incentive);
            if (incentiveVal < 10) {
                type = "points per dollar";
            } else {
                type = commonMethods.getRandomValueFromLIstOfString(INCENTIVE_TYPE_LIST);
            }

        }
        return type;
    }
//    private Offer createOfferVerticalOfferingAndTags(Offer offer, String PRODUCT) {
//        List<String> OFFER_VARIANT_LIST = commonMethods.getListOfStrings("0,1,2,3,4,5,6,7");
//        List<String> AIRLINE_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(AIRLINE_PRODUCT_VERTICAL);
//        List<String> AIRLINE_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(AIRLINE_PRODUCT_TAGS);
//        List<String> INSURANCE_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(INSURANCE_PRODUCT_VERTICAL);
//        List<String> EPIQURE_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(EPIQURE_PRODUCT_TAGS);
//        List<String> EPIQURE_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(EPIQURE_PRODUCT_VERTICAL);
//        List<String> ACCOMODATION_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(ACCOMODATION_PRODUCT_TAGS);
//        List<String> CAR_HIRE_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(CAR_HIRE_PRODUCT_TAGS);
//        List<String> FINANCIAL_SERVICES_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(FINANCIAL_SERVICES_PRODUCT_TAGS);
//        List<String> FINANCIAL_SERVICES_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(FINANCIAL_SERVICES_PRODUCT_VERTICAL);
//        List<String> FF_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(FF_PRODUCT_TAGS);
//        List<String> FF_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(FF_PRODUCT_VERTICAL);
//        List<String> LOYALTY_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(LOYALTY_PRODUCT_TAGS);
//        List<String> LOYALTY_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(LOYALTY_PRODUCT_VERTICAL);
//        List<String> QBR_PRODUCT_TAGS_LIST_ACQUISITION = commonMethods.getListOfStrings(QBR_PRODUCT_TAGS_ACQUISITION);
//        List<String> QBR_PRODUCT_TAGS_LIST_FLIGHTS = commonMethods.getListOfStrings(QBR_PRODUCT_TAGS_FLIGHTS);
//        List<String> QBR_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(QBR_PRODUCT_VERTICAL);
//        List<String> RETAIL_PRODUCT_TAGS_LIST_WOOLIES = commonMethods.getListOfStrings(RETAIL_PRODUCT_TAGS_WOOLIES);
//        List<String> RETAIL_PRODUCT_TAGS_LIST_STORE = commonMethods.getListOfStrings(RETAIL_PRODUCT_TAGS_STORE);
//        List<String> RETAIL_PRODUCT_TAGS_LIST_PARTNERS = commonMethods.getListOfStrings(RETAIL_PRODUCT_TAGS_PARTNERS);
//        List<String> RETAIL_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(RETAIL_PRODUCT_VERTICAL);
//        List<String> ACTION_TYPE_LIST = commonMethods.getListOfStrings(ACTION_TYPE);
//        List<String> DIFFERENT_POINTS_LIST = commonMethods.getListOfStrings(DIFFERENT_POINTS);
//        if (PRODUCT.equalsIgnoreCase("insurance")) {
//            offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(INSURANCE_PRODUCT_VERTICAL_LIST));
//            List<String> INSURANCE_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("life_insurance,health_insurance,travel_insurance,income_insurance,wellness_app");
//            offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(INSURANCE_PRODUCT_OFFERING_LIST));
//            offer.setPRODUCT_TAGS("");
//            offer.setVERTICAL_FOR_BUSINESS("future-airlines-insurance");
//
//        } else if (PRODUCT.equalsIgnoreCase("Airline")) {
//            offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(AIRLINE_PRODUCT_VERTICAL_LIST));
//            offer.setPRODUCT_OFFERING("commercial_fare_all_cabins");
//            List<String> tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(AIRLINE_PRODUCT_TAGS_LIST, 4);
//            offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(tagsList));
//            offer.setVERTICAL_FOR_BUSINESS("flights");
//
//        } else if (PRODUCT.equalsIgnoreCase("accommodation")) {
//            offer.setPRODUCT_VERTICAL("accommodation");
//            List<String> ACCOMODATION_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("future-airlines_hotels,airbnb,classic_hotel_reward");
//            offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(ACCOMODATION_PRODUCT_OFFERING_LIST));
//            List<String> tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(ACCOMODATION_PRODUCT_TAGS_LIST, 4);
//            offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(tagsList));
//            offer.setVERTICAL_FOR_BUSINESS("accommodation");
//        } else if (PRODUCT.equalsIgnoreCase("car_hire")) {
//            offer.setPRODUCT_VERTICAL("car_hire");
//            offer.setPRODUCT_OFFERING("car_hire");
//            List<String> tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(CAR_HIRE_PRODUCT_TAGS_LIST, 4);
//            offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(tagsList));
//            offer.setVERTICAL_FOR_BUSINESS("car_hire");
//        } else if (PRODUCT.equalsIgnoreCase("EpiQure")) {
//            offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(EPIQURE_PRODUCT_VERTICAL_LIST));
//            List<String> EPIQURE_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("cabernet_sauvignon,champagne,riesling,shiraz,spirits,white_wine,wine");
//            offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(EPIQURE_PRODUCT_OFFERING_LIST));
//            String tags = "future-airlines_Epiqure";
//            List<String> tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(EPIQURE_PRODUCT_TAGS_LIST, 5);
//            offer.setPRODUCT_TAGS(tags + "|" + commonMethods.getPipeSeparatedList(tagsList));
//            offer.setVERTICAL_FOR_BUSINESS("future-airlines-epiqure");
//        } else if (PRODUCT.equalsIgnoreCase("Financial_Services")) {
//            offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(FINANCIAL_SERVICES_PRODUCT_VERTICAL_LIST));
//            List<String> FIN_SERVICES_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("future-airlines_Premier_Platinum_Credit_Card,Credit_Card,Premier_Platinum");
//            offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(FIN_SERVICES_PRODUCT_OFFERING_LIST));
//            List<String> tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(FINANCIAL_SERVICES_PRODUCT_TAGS_LIST, 6);
////            tagsList.add("Personal_credit_card");
//            offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(tagsList));
//            offer.setVERTICAL_FOR_BUSINESS(offer.getPRODUCT_VERTICAL());
//        } else if (PRODUCT.equalsIgnoreCase("Frequent Flyer")) {
//            offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(FF_PRODUCT_VERTICAL_LIST));
//            offer.setPRODUCT_OFFERING("engagement_earn_points");
//            offer.setPRODUCT_TAGS("");
//            offer.setVERTICAL_FOR_BUSINESS("future-airlines-frequent-flyer");
//        } else if (PRODUCT.equalsIgnoreCase("Loyalty")) {
//            offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(LOYALTY_PRODUCT_VERTICAL_LIST));
//            offer.setPRODUCT_OFFERING("shopping_toys_games_and_baby");
//            String tags = "future-airlines_qtore";
//            List<String> tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(LOYALTY_PRODUCT_TAGS_LIST, 2);
//            offer.setPRODUCT_TAGS(tags + "|" + commonMethods.getPipeSeparatedList(tagsList));
//            offer.setVERTICAL_FOR_BUSINESS("future-airlines-shopping");
//        } else if (PRODUCT.equalsIgnoreCase("future-airlines_Business_Rewards")) {
//            offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(QBR_PRODUCT_VERTICAL_LIST));
//            List<String> QBR_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("commercial_fare_all_cabins,membership");
//            offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(QBR_PRODUCT_OFFERING_LIST));
//            String tags = "future-airlines_Business_Rewards";
//            List<String> tagsList = new ArrayList<String>();
//            if (offer.getPRODUCT_OFFERING().equalsIgnoreCase("membership")) {
//                offer.setVERTICAL_FOR_BUSINESS("future-airlines-business-rewards");
//                tagsList = Arrays.asList("");
//            } else if (offer.getPRODUCT_OFFERING().equalsIgnoreCase("commercial_fare_all_cabins")) {
//                offer.setVERTICAL_FOR_BUSINESS("flights");
//                tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(QBR_PRODUCT_TAGS_LIST_FLIGHTS, 3);
//            }
//            offer.setPRODUCT_TAGS(tags + "|" + commonMethods.getPipeSeparatedList(tagsList));
//
//        } else if (PRODUCT.equalsIgnoreCase("Retail")) {
//            String vertical = commonMethods.getRandomValueFromLIstOfString(RETAIL_PRODUCT_VERTICAL_LIST);
//            offer.setPRODUCT_VERTICAL(vertical);
//            List<String> RETAIL_PRODUCT_OFFERING_LIST = new ArrayList<String>();
//            List<String> tagsList = new ArrayList<String>();
//            if (vertical.contains("retail_partners")) {
//                RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("car_purchase_jaguar_landrover,energy_red_energy,entertainment_hoyts,pet_sitting,phone_plans_vodafone,shopping_kogan");
//                tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(RETAIL_PRODUCT_TAGS_LIST_WOOLIES, 3);
//                offer.setVERTICAL_FOR_BUSINESS("future-airlines-shopping");
//            } else if (vertical.contains("store")) {
//                RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("electronics,electronics_apple,electronics_bose,electronics_canon,electronics_computers,electronics_fitbit,electronics_gopro,electronics_lg,electronics_olympus,electronics_samsung,electronics_sony_bose,fashion_and_accessories,health_and_beauty,health_and_beauty_dyson,home_appliances,home_appliances_dyson,home_appliances_lg,home_appliances_samsung,homewares,homewares_weber,menswear_rm_williams,outdoor,shopping_multi");
//                tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(RETAIL_PRODUCT_TAGS_LIST_STORE, 3);
//                offer.setVERTICAL_FOR_BUSINESS("future-airlines-shopping");
//            } else if (vertical.contains("restaurants")) {
//                RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("restaurants,restaurants_rockpool_earn");
//                tagsList = Arrays.asList("");
//                offer.setVERTICAL_FOR_BUSINESS("restaurants");
//            } else {
//                RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("fuel_woolworths_caltex_join,groceries_woolworths_join,groceries_woolworths_spend,");
//                tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(RETAIL_PRODUCT_TAGS_LIST_PARTNERS, 3);
//                offer.setVERTICAL_FOR_BUSINESS("woolworths-rewards");
//            }
//            offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(RETAIL_PRODUCT_OFFERING_LIST));
//            offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(tagsList));
//
//        }
//        offer.setACTION_TYPE(commonMethods.getRandomValueFromLIstOfString(ACTION_TYPE_LIST));
//        offer.setINCENTIVE_AMOUNT(commonMethods.getRandomValueFromLIstOfString(DIFFERENT_POINTS_LIST));
//        offer.setINCENTIVE_TYPE(getIncentiveType(offer.getINCENTIVE_AMOUNT()));
//        if (offer.getINCENTIVE_TYPE().equalsIgnoreCase("no_incentive")) {
//            offer.setINCENTIVE_AMOUNT("0");
//        }
//        offer.setOFFER_START_DATE("2018-06-19 00:00:00");
//        offer.setOFFER_END_DATE("2019-10-30 13:59:59");
//        offer.setPURCHASE_START_DATE("");
//        offer.setPURCHASE_END_DATE("");
//        offer.setTRAVEL_START_DATE("");
//        offer.setTRAVEL_END_DATE("");
//        offer.setPURCHASE_ROLLING_FLAG("0");
//        offer.setPURCHASE_ROLLING_START_DAYS("0");
//        offer.setPURCHASE_ROLLING_END_DAYS("0");
//        offer.setTRAVEL_ROLLING_FLAG("0");
//        offer.setTRAVEL_ROLLING_START_DAYS("0");
//        offer.setTRAVEL_ROLLING_END_DAYS("0");
//        offer.setCONTENT_TAGS(getContentTag());
//        offer.setJSON_FOR_EXPANSION("");
//        offer.setOFFER_VARIANT(commonMethods.getRandomValueFromLIstOfString(OFFER_VARIANT_LIST));
//        offer.setAction_Parent_Id("");
//        offer.setAction_followup_tmin("");
//        offer.setAction_followup_tmax("");
//        offer.setOFFER_CATEGORY("INITIAL");
//        return offer;
//    }

    private Offer createOfferVerticalOfferingAndTags(Offer offer, String PRODUCT) {
//        String FF_PRODUCT_TAGS = "member_arketing,future-airlines_Frequent_Flyer_Program";
//        String QBR_PRODUCT_TAGS_ACQUISITION = "Acquisition,Free_Join";
//        List<String> FF_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(FF_PRODUCT_TAGS);
//        List<String> QBR_PRODUCT_TAGS_LIST_ACQUISITION = commonMethods.getListOfStrings(QBR_PRODUCT_TAGS_ACQUISITION);
        String ACTION_TYPE = "evergreen,offer|evergreen,offer|tactical";
        String DIFFERENT_POINTS = "0,1,2,3,100,,200,400,1000,8000,5000,10000,2000";
        List<String> OFFER_VARIANT_LIST = commonMethods.getListOfStrings("0,1,2,3,4,5,6,7");
        List<String> ACTION_TYPE_LIST = commonMethods.getListOfStrings(ACTION_TYPE);
        List<String> DIFFERENT_POINTS_LIST = commonMethods.getListOfStrings(DIFFERENT_POINTS);
        switch (PRODUCT.toUpperCase()) {
            case "INSURANCE":
                setINSURANCEProductOfferingsTags(offer);
                break;
            case "AIRLINE":
                setAIRLINEProductOfferingsTags(offer);
                break;
            case "ACCOMMODATION":
                setACCOMMODATIONProductOfferingsTags(offer);
                break;
            case "CAR_HIRE":
                setCAR_HIREProductOfferingsTags(offer);
                break;
            case "EPIQURE":
                setEPIQUREProductOfferingsTags(offer);
                break;
            case "FINANCIAL_SERVICES":
                setFINANCIAL_SERVICESProductOfferingsTags(offer);
                break;
            case "FREQUENT FLYER":
                setFREQUENT_FLYERSProductOfferingsTags(offer);
                break;
            case "LOYALTY":
                setLOYALTYSProductOfferingsTags(offer);
                break;
            case "future-airlines_BUSINESS_REWARDS":
                setfuture-airlines_BUSINESS_REWARDSProductOfferingsTags(offer);
                break;
            case "RETAIL":
                setRETAILProductOfferingsTags(offer);
                break;

        }
        offer.setACTION_TYPE(commonMethods.getRandomValueFromLIstOfString(ACTION_TYPE_LIST));
        offer.setINCENTIVE_AMOUNT(commonMethods.getRandomValueFromLIstOfString(DIFFERENT_POINTS_LIST));
        offer.setINCENTIVE_TYPE(getIncentiveType(offer.getINCENTIVE_AMOUNT()));
        if (offer.getINCENTIVE_TYPE().equalsIgnoreCase("no_incentive")) {
            offer.setINCENTIVE_AMOUNT("0");
        }
        offer.setOFFER_START_DATE("2018-06-19 00:00:00");
        offer.setOFFER_END_DATE("2019-10-30 13:59:59");
        offer.setPURCHASE_START_DATE("");
        offer.setPURCHASE_END_DATE("");
        offer.setTRAVEL_START_DATE("");
        offer.setTRAVEL_END_DATE("");
        offer.setPURCHASE_ROLLING_FLAG("0");
        offer.setPURCHASE_ROLLING_START_DAYS("0");
        offer.setPURCHASE_ROLLING_END_DAYS("0");
        offer.setTRAVEL_ROLLING_FLAG("0");
        offer.setTRAVEL_ROLLING_START_DAYS("0");
        offer.setTRAVEL_ROLLING_END_DAYS("0");
        offer.setCONTENT_TAGS(getContentTag());
        offer.setJSON_FOR_EXPANSION("");
        offer.setOFFER_VARIANT(commonMethods.getRandomValueFromLIstOfString(OFFER_VARIANT_LIST));
        offer.setAction_Parent_Id("");
        offer.setAction_followup_tmin("");
        offer.setAction_followup_tmax("");
        offer.setOFFER_CATEGORY("INITIAL");
        return offer;
    }

    private void setRETAILProductOfferingsTags(Offer offer) {
        String RETAIL_PRODUCT_TAGS_WOOLIES = "Retail_partners,woolworths,Caltex,Everyday_shopping,Supermarkets";
        String RETAIL_PRODUCT_TAGS_STORE = "future-airlines_store,Electronics,Headphones_&_speakers,Headphones,Bose,Bose_QC35_wireless_headphones_II,Store";
        String RETAIL_PRODUCT_TAGS_PARTNERS = "Retail_partners,Hotels,travel,packages,Jaguar_Landrover,100000_bonus_points,Avis,Budget,Avis_&_Budget_car_hire,Car_hire";
        String RETAIL_PRODUCT_VERTICAL = "store,retail_partners,resturants";
        List<String> RETAIL_PRODUCT_TAGS_LIST_WOOLIES = commonMethods.getListOfStrings(RETAIL_PRODUCT_TAGS_WOOLIES);
        List<String> RETAIL_PRODUCT_TAGS_LIST_STORE = commonMethods.getListOfStrings(RETAIL_PRODUCT_TAGS_STORE);
        List<String> RETAIL_PRODUCT_TAGS_LIST_PARTNERS = commonMethods.getListOfStrings(RETAIL_PRODUCT_TAGS_PARTNERS);
        List<String> RETAIL_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(RETAIL_PRODUCT_VERTICAL);
        String vertical = commonMethods.getRandomValueFromLIstOfString(RETAIL_PRODUCT_VERTICAL_LIST);
        offer.setPRODUCT_VERTICAL(vertical);
        List<String> RETAIL_PRODUCT_OFFERING_LIST = new ArrayList<String>();
        List<String> retail_tagsList = new ArrayList<String>();
        if (vertical.contains("retail_partners")) {
            RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("car_purchase_jaguar_landrover,energy_red_energy,entertainment_hoyts,pet_sitting,phone_plans_vodafone,shopping_kogan");
            retail_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(RETAIL_PRODUCT_TAGS_LIST_WOOLIES, 3);
            offer.setVERTICAL_FOR_BUSINESS("future-airlines-shopping");
        } else if (vertical.contains("store")) {
            RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("electronics,electronics_apple,electronics_bose,electronics_canon,electronics_computers,electronics_fitbit,electronics_gopro,electronics_lg,electronics_olympus,electronics_samsung,electronics_sony_bose,fashion_and_accessories,health_and_beauty,health_and_beauty_dyson,home_appliances,home_appliances_dyson,home_appliances_lg,home_appliances_samsung,homewares,homewares_weber,menswear_rm_williams,outdoor,shopping_multi");
            retail_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(RETAIL_PRODUCT_TAGS_LIST_STORE, 3);
            offer.setVERTICAL_FOR_BUSINESS("future-airlines-shopping");
        } else if (vertical.contains("restaurants")) {
            RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("restaurants,restaurants_rockpool_earn");
            retail_tagsList = Arrays.asList("");
            offer.setVERTICAL_FOR_BUSINESS("restaurants");
        } else {
            RETAIL_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("fuel_woolworths_caltex_join,groceries_woolworths_join,groceries_woolworths_spend,");
            retail_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(RETAIL_PRODUCT_TAGS_LIST_PARTNERS, 3);
            offer.setVERTICAL_FOR_BUSINESS("woolworths-rewards");
        }
        offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(RETAIL_PRODUCT_OFFERING_LIST));
        offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(retail_tagsList));
    }

    private void setfuture-airlines_BUSINESS_REWARDSProductOfferingsTags(Offer offer) {
        String QBR_PRODUCT_TAGS_FLIGHTS = "region/australia,region/new_zealand,region/south_pacific,region/asia,region/europe,region/africa,region/north_america,region/south_america";
        String QBR_PRODUCT_VERTICAL = "future-airlines_business_rewards";
        List<String> QBR_PRODUCT_TAGS_LIST_FLIGHTS = commonMethods.getListOfStrings(QBR_PRODUCT_TAGS_FLIGHTS);
        List<String> QBR_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(QBR_PRODUCT_VERTICAL);
        offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(QBR_PRODUCT_VERTICAL_LIST));
        List<String> QBR_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("commercial_fare_all_cabins,membership");
        offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(QBR_PRODUCT_OFFERING_LIST));
        String QBR_tags = "future-airlines_Business_Rewards";
        List<String> QBR_tagsList = new ArrayList<String>();
        if (offer.getPRODUCT_OFFERING().equalsIgnoreCase("membership")) {
            offer.setVERTICAL_FOR_BUSINESS("future-airlines-business-rewards");
            QBR_tagsList = Arrays.asList("");
        } else if (offer.getPRODUCT_OFFERING().equalsIgnoreCase("commercial_fare_all_cabins")) {
            offer.setVERTICAL_FOR_BUSINESS("flights");
            QBR_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(QBR_PRODUCT_TAGS_LIST_FLIGHTS, 3);
        }
        offer.setPRODUCT_TAGS(QBR_tags + "|" + commonMethods.getPipeSeparatedList(QBR_tagsList));
    }

    private void setLOYALTYSProductOfferingsTags(Offer offer) {
        String LOYALTY_PRODUCT_TAGS = "toys_games_and_baby,various";
        String LOYALTY_PRODUCT_VERTICAL = "store";
        List<String> LOYALTY_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(LOYALTY_PRODUCT_TAGS);
        List<String> LOYALTY_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(LOYALTY_PRODUCT_VERTICAL);
        offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(LOYALTY_PRODUCT_VERTICAL_LIST));
        offer.setPRODUCT_OFFERING("toys_games_and_baby");
        String loyaltyTags = "future-airlines_qtore";
        List<String> loyaltyTagsList = commonMethods.getListOfRandomValuesFromLIstOfString(LOYALTY_PRODUCT_TAGS_LIST, 2);
        offer.setPRODUCT_TAGS(loyaltyTags + "|" + commonMethods.getPipeSeparatedList(loyaltyTagsList));
        offer.setVERTICAL_FOR_BUSINESS("future-airlines-shopping");
    }

    private void setFREQUENT_FLYERSProductOfferingsTags(Offer offer) {
        String FF_PRODUCT_VERTICAL = "member_marketing";
        List<String> FF_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(FF_PRODUCT_VERTICAL);
        offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(FF_PRODUCT_VERTICAL_LIST));
        offer.setPRODUCT_OFFERING("engagement_earn_points");
        offer.setPRODUCT_TAGS("");
        offer.setVERTICAL_FOR_BUSINESS("future-airlines-frequent-flyer");
    }

    private void setFINANCIAL_SERVICESProductOfferingsTags(Offer offer) {
        String FINANCIAL_SERVICES_PRODUCT_TAGS = "Financial_services,Credit_card,HSBC_Platinum_future-airlines_credit_card,future-airlines_Money,Premier_Platinum,future-airlines_Cash,Travel_Money_card";
//        String FINANCIAL_SERVICES_PRODUCT_VERTICAL = "Financial_Services,future-airlines_Money";
        String FINANCIAL_SERVICES_PRODUCT_VERTICAL = "Financial_Services";
        List<String> FINANCIAL_SERVICES_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(FINANCIAL_SERVICES_PRODUCT_TAGS);
        List<String> FINANCIAL_SERVICES_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(FINANCIAL_SERVICES_PRODUCT_VERTICAL);
        offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(FINANCIAL_SERVICES_PRODUCT_VERTICAL_LIST));
        List<String> FIN_SERVICES_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("Credit_Card_future-airlines_Premier_Platinum");
        offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(FIN_SERVICES_PRODUCT_OFFERING_LIST));
        List<String> FS_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(FINANCIAL_SERVICES_PRODUCT_TAGS_LIST, 6);
        offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(FS_tagsList));
        offer.setVERTICAL_FOR_BUSINESS(offer.getPRODUCT_VERTICAL());
    }

    private void setEPIQUREProductOfferingsTags(Offer offer) {
        String EPIQURE_PRODUCT_TAGS = "Wine,Red_wine,Bonus_points,Pinot_Noir,Chardonnay,Cabernet_Sauvignon,Sauvignon_Blanc";
        String EPIQURE_PRODUCT_VERTICAL = "future-airlines_wine";
        List<String> EPIQURE_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(EPIQURE_PRODUCT_TAGS);
        List<String> EPIQURE_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(EPIQURE_PRODUCT_VERTICAL);
        offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(EPIQURE_PRODUCT_VERTICAL_LIST));
        List<String> EPIQURE_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("cabernet_sauvignon,champagne,riesling,shiraz,spirits,white_wine,wine");
        offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(EPIQURE_PRODUCT_OFFERING_LIST));
        String tags = "future-airlines_Epiqure";
        List<String> epiQure_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(EPIQURE_PRODUCT_TAGS_LIST, 5);
        offer.setPRODUCT_TAGS(tags + "|" + commonMethods.getPipeSeparatedList(epiQure_tagsList));
        offer.setVERTICAL_FOR_BUSINESS("future-airlines-epiqure");
    }

    private void setCAR_HIREProductOfferingsTags(Offer offer) {
        String CAR_HIRE_PRODUCT_TAGS = "ctrycod/nz,ctrycod/us,ctrycod/au,region/australia|region/new_zealand|region/south_pacific|region/asia|region/europe|region/africa|region/north_america|region/south_america";
        List<String> CAR_HIRE_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(CAR_HIRE_PRODUCT_TAGS);
        offer.setPRODUCT_VERTICAL("car_hire");
        offer.setPRODUCT_OFFERING("car_hire");
        List<String> car_hire_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(CAR_HIRE_PRODUCT_TAGS_LIST, 4);
        offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(car_hire_tagsList));
        offer.setVERTICAL_FOR_BUSINESS("car_hire");
    }

    private void setACCOMMODATIONProductOfferingsTags(Offer offer) {
        String ACCOMODATION_PRODUCT_TAGS = "special_region/anywhere,statecod/tasmania,region/australia|region/new_zealand|region/south_pacific|region/asia|region/europe|region/africa|region/north_america|region/south_america,ctrycod/gb|citycod/lon,statecod/queensland,ctrycod/au,statecod/victoria,statecod/western_australia,citycod/mel,statecod/new_south_wales,citycod/azr|citycod/alg|citycod/aae,citycod/hnl,citycod/lon,ctrycod/us,citycod/hti,citycod/nyc,citycod/akl,citycod/sin";
        List<String> ACCOMODATION_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(ACCOMODATION_PRODUCT_TAGS);
        offer.setPRODUCT_VERTICAL("accommodation");
        List<String> ACCOMODATION_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("future-airlines_hotels,airbnb,classic_hotel_reward");
        offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(ACCOMODATION_PRODUCT_OFFERING_LIST));
        List<String> accomodation_tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(ACCOMODATION_PRODUCT_TAGS_LIST, 4);
        offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(accomodation_tagsList));
        offer.setVERTICAL_FOR_BUSINESS("accommodation");
    }

    private void setAIRLINEProductOfferingsTags(Offer offer) {
        String AIRLINE_PRODUCT_TAGS = "citycod/dps,citycod/hkg,citycod/hnl,citycod/lax,citycod/lon,citycod/nyc,citycod/sfo,citycod/was,ctrycod/id,ctrycod/my,ctrycod/nz,ctrycod/za,region/africa,region/asia,region/australia,special_region/anywhere_excluding_australia,region/europe,region/new_zealand,region/north_america,region/south_america,region/south_pacific,special_region/anywhere";
        String AIRLINE_PRODUCT_VERTICAL = "flights";
        List<String> AIRLINE_PRODUCT_TAGS_LIST = commonMethods.getListOfStrings(AIRLINE_PRODUCT_TAGS);
        List<String> AIRLINE_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(AIRLINE_PRODUCT_VERTICAL);
        offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(AIRLINE_PRODUCT_VERTICAL_LIST));
        offer.setPRODUCT_OFFERING("commercial_fare_all_cabins");
        List<String> tagsList = commonMethods.getListOfRandomValuesFromLIstOfString(AIRLINE_PRODUCT_TAGS_LIST, 4);
        offer.setPRODUCT_TAGS(commonMethods.getPipeSeparatedList(tagsList));
        offer.setVERTICAL_FOR_BUSINESS("flights");
        // TODO : Injecting the Pricing Data for Airline offers
        if(commonMethods.getRandomValueFromLIstOfString(Arrays.asList("false,true,false,false,false,false,false,false,false,false,false,PRICING_DATA_50_TILES,PRICING_DATA_150_TILES".split(","))).equalsIgnoreCase("PRICING_DATA_50_TILES")) {
            offer.setPRICING_DATA(PRICING_DATA_50_TILES);
        }
        else if(commonMethods.getRandomValueFromLIstOfString(Arrays.asList("false,true,false,false,false,false,false,false,false,false,,PRICING_DATA_50_TILES,PRICING_DATA_150_TILES".split(","))).equalsIgnoreCase("PRICING_DATA_150_TILES")) {
            offer.setPRICING_DATA(PRICING_DATA_150_TILES);
        }
    }

    private void setINSURANCEProductOfferingsTags(Offer offer) {
        String INSURANCE_PRODUCT_VERTICAL = "insurance";
        List<String> INSURANCE_PRODUCT_VERTICAL_LIST = commonMethods.getListOfStrings(INSURANCE_PRODUCT_VERTICAL);
        offer.setPRODUCT_VERTICAL(commonMethods.getRandomValueFromLIstOfString(INSURANCE_PRODUCT_VERTICAL_LIST));
        List<String> INSURANCE_PRODUCT_OFFERING_LIST = commonMethods.getListOfStrings("life_insurance,health_insurance,travel_insurance,income_insurance,wellness_app");
        offer.setPRODUCT_OFFERING(commonMethods.getRandomValueFromLIstOfString(INSURANCE_PRODUCT_OFFERING_LIST));
        offer.setPRODUCT_TAGS("");
        offer.setVERTICAL_FOR_BUSINESS("future-airlines-insurance");
    }

}
