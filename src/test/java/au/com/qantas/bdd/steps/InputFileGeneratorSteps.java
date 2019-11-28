package au.com.future-airlines.bdd.steps;

import au.com.future-airlines.nonfunctional.CreateOffers;
import au.com.future-airlines.nonfunctional.EngineInputFileGenerator;
import au.com.future-airlines.nonfunctional.Offer;
import au.com.future-airlines.utils.DateFunctions;
import au.com.future-airlines.utils.CsvToParquet;
import au.com.future-airlines.utils.PropsUtil;
import org.jbehave.core.annotations.Given;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class InputFileGeneratorSteps {


    DateFunctions dtFunctions = new DateFunctions();
    Map<String, Object> properties = PropsUtil.loadProperties();


    @Given("I generate the input and output files for engine run with auto-generated offers and frequent flyer members for $isEngineCodeNew Code Repo")
    public void generateInputFiles(String isEngineCodeNew) {
        EngineInputFileGenerator engineInputFileGenerator = new EngineInputFileGenerator();
        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        Boolean flag = true;
        if (isEngineCodeNew.equalsIgnoreCase("NEW")) {
            flag = true;
        } else {
            flag = false;
        }
        String numberOfOffers = (String) properties.get("dummy.Offer.Count");
        String numberOfMembers = (String) properties.get("dummy.FFUser.Count");
        CreateOffers createOffers = new CreateOffers();
        List<Offer> offersList = createOffers.createOffer(Integer.valueOf(numberOfOffers));
        engineInputFileGenerator.createEngineInputFiles(offersList, Integer.valueOf(numberOfMembers), flag);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        System.out.println("Engine Input File generation has been completed successfully in ... " + totalTime + " millisecinds / " + dtFunctions.getHHmmSSfromMillis(totalTime) + "  ... !!");

        System.out.println("Generating Eligibility and AI parquet files");
        new CsvToParquet().loadSparkSession().generateParquetFiles();


    }


    @Given("I generate parquet files")
    public void generateInputFiles() {
        System.out.println("Generating Eligibility and AI parquet files");
        new CsvToParquet().loadSparkSession().generateParquetFiles();
    }

    @Given("I generate the input and output files for engine run with auto-generated offers and frequent flyer members for $isEngineCodeNew Code Repo and delta extract with date $deltaExtractDate")
    public void generateInputFilesWIthDeltaExtracts(String isEngineCodeNew, String deltaExtractDate) {
        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        Boolean flag = true;
        if (isEngineCodeNew.equalsIgnoreCase("NEW")) {
            flag = true;
        } else {
            flag = false;
        }
        String numberOfOffers = (String) properties.get("dummy.Offer.Count");
        String numberOfMembers = (String) properties.get("dummy.FFUser.Count");
        CreateOffers createOffers = new CreateOffers();
        List<Offer> offersList = createOffers.createOffer(Integer.valueOf(numberOfOffers));
//        engineInputFileGenerator.createEngineInputFilesAlongWithDeltaEligibility(offersList, Integer.valueOf(numberOfMembers), flag, deltaExtractDate);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        System.out.println("Engine Input File generation has been completed successfully in ... " + totalTime + " millisecinds / " + dtFunctions.getHHmmSSfromMillis(totalTime) + "  ... !!");


    }

    @Given("I generate the input and output files for engine run against existing offers for existing frequent flyer members for $isEngineCodeNew Code Repo")
    public void generateInputFilesForExistingOffers(String isEngineCodeNew) {
        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        Boolean flag = true;
        if (isEngineCodeNew.equalsIgnoreCase("NEW")) {
            flag = true;
        } else {
            flag = false;
        }
        String listOfActions = (String) properties.get("actionId.List");
        List<String> actionIdList = Arrays.asList(listOfActions.split(","));
        // For manual testers to generate data against existing Actions
        CreateOffers createOffers = new CreateOffers();
        List<Offer> offersList = createOffers.createOfferForExistingActionIds(actionIdList);
        EngineInputFileGenerator engineInputFileGenerator = new EngineInputFileGenerator();
        engineInputFileGenerator.createEngineInputFiles(offersList, 0, flag);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        System.out.println("Engine Input File generation has been completed successfully in ... " + totalTime + "/" + dtFunctions.getHHmmSSfromMillis(totalTime) + "  ... !!");


    }


    @Given("I generate the input and output files for engine run against existing offers for $numberOfFFMembers frequent flyer members")
    public void generateInputFilesForExistingOffersAndNewMembers(Integer numberOfFFMembers) {
        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        Boolean flag = true;
        EngineInputFileGenerator engineInputFileGenerator = new EngineInputFileGenerator();
        List<String> actionIdList = engineInputFileGenerator.readExistingOfferIds();
        CreateOffers createOffers = new CreateOffers();
        List<Offer> offersList = createOffers.createOfferForExistingActionIds(actionIdList);
        engineInputFileGenerator.createEngineInputFiles(offersList, numberOfFFMembers, flag);
        long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
        System.out.println("Engine Input File generation has been completed successfully in ... " + totalTime + "/" + dtFunctions.getHHmmSSfromMillis(totalTime) + "  ... !!");

        System.out.println("Generating Eligibility and AI parquet files");
        new CsvToParquet().loadSparkSession().generateParquetFiles();

    }


}
