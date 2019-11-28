package au.com.future-airlines.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class CsvToParquet {

    private final String user_dir = "user.dir";
    private String _engine_run_date = new CommonMethods().getFileCreationDate();
    private String _fileContainer = System.getProperty(user_dir) + "/fileContainer/";
    private String _ai_click_file_path = _fileContainer + "part-00003-5650e57f-7dab-c000.csv";
    private String _ai_conversion_file_path = _fileContainer + "part-00004-5650e57f-7dab-c000.csv";
    private String _eligibility_offers_path = _fileContainer + "eligibility_offers_" + _engine_run_date + ".csv";
    private SparkSession _sparkSession;


    public CsvToParquet loadSparkSession() {
        Logger.getLogger("org").setLevel(Level.ERROR);
        this._sparkSession = SparkSession.builder().appName("Simulator").master("local[*]").getOrCreate();
        return this;
    }

    public void generateParquetFiles() {
        System.out.println("Reading _ai_click_file_path : " + _ai_click_file_path);
        System.out.println("Reading _ai_conversion_file_path : " + _ai_conversion_file_path);
        System.out.println("Reading _eligibility_offers_path : " + _eligibility_offers_path);
        System.out.println("");
        System.out.println("");
        aiClickCsvToParquet(this._sparkSession);
        aiConversionCsvToParquet(this._sparkSession);
        eligibilityOffersCsvToParquet(this._sparkSession);
    }

    private void aiClickCsvToParquet(SparkSession session) {
        System.out.println("Converting aiClickCsvToParquet at - "+_fileContainer + "ai_conversion_parquet/");
        Dataset<Row> df1 = session.read().schema(getAiSchema()).option("header", true).option("delimiter", "|").option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ").csv(_ai_click_file_path);
        df1.write().mode(SaveMode.Overwrite).parquet(_fileContainer + "ai_click_parquet/");
    }

    private void aiConversionCsvToParquet(SparkSession session) {
        System.out.println("Converting aiConversionCsvToParquet at - "+_fileContainer + "ai_conversion_parquet/");
        Dataset<Row> df1 = session.read().schema(getAiSchema()).option("header", true).option("delimiter", "|").option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ").csv(_ai_conversion_file_path);
        df1.write().mode(SaveMode.Overwrite).parquet(_fileContainer + "ai_conversion_parquet/");
    }

    private void eligibilityOffersCsvToParquet(SparkSession session) {
        System.out.println("Converting eligibilityOffersCsvToParquet at  - "+_fileContainer + "eligibility_offers_" + _engine_run_date + "_parquet/");
        Dataset<Row> df1 = session.read().schema(eligibilitySchema()).option("header", true).option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ").csv(_eligibility_offers_path);
        df1.write().mode(SaveMode.Overwrite).parquet(_fileContainer + "eligibility_offers_" + _engine_run_date + "_parquet/");
    }

    private StructType getAiSchema() {
        return DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("prim_party_id", DataTypes.IntegerType, true),
                DataTypes.createStructField("action_id", DataTypes.StringType, true),
                DataTypes.createStructField("prob_sample", DataTypes.DoubleType, true),
                DataTypes.createStructField("prob_mean", DataTypes.DoubleType, true),
                DataTypes.createStructField("prob_sd", DataTypes.DoubleType, true),
        });
    }

    private StructType eligibilitySchema() {
        return DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("prim_party_id", DataTypes.IntegerType, true),
                DataTypes.createStructField("action_id", DataTypes.StringType, true),
                DataTypes.createStructField("lead_key_id", DataTypes.LongType, true),
                DataTypes.createStructField("channel", DataTypes.StringType, true),
                DataTypes.createStructField("account_number", DataTypes.StringType, true),
                DataTypes.createStructField("hub_card_key", DataTypes.StringType, true),
        });

    }


}
