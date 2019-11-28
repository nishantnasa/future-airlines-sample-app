package au.com.future-airlines.nonfunctional;

import au.com.future-airlines.utils.CommonMethods;

import java.io.*;
import java.util.*;

/**
 * Created by boses on 21/11/2018.
 */
public class PropensityScoresFileGeneratorForCustomerDna {

    public static CommonMethods commonMethods = new CommonMethods();

    public static void generateFile(){
        String path = System.getProperty("user.dir");
        File sampleData = new File(path + "/src/main/resources/testMembers/Pers_mvp_full_list.txt");
        BufferedReader bufrd = null;
        try {
            bufrd = new BufferedReader(new FileReader(sampleData));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> listOfPrimPartyIds = new ArrayList<>();
        try {
            for (String line = bufrd.readLine(); line != null; line = bufrd.readLine()) {
                listOfPrimPartyIds.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> sampleScoresList = generatePropScoresFileForCustomerDNA();
        String propScoresFileHeader = "PRIM_PARTY_IDµ\"score_USA\"µ\"score_UK\"µ\"score_SIN\"µ\"score_THAI\"µ\"score_HKG\"µ\"score_INDO\"µ\"score_PHILI\"µ\"score_JAPAN\"µ\"score_CHINA\"µ\"score_RSA\"µ\"score_CHILE\"µ\"score_CANADA\"µ\"score_NEW_CAL\"µ\"score_PNG\"µ\"score_NZ\"µ\"score_SYD\"µ\"score_BNE\"µ\"score_MEL\"µ\"score_PER\"µ\"score_CBR\"µ\"score_DRW\"µ\"score_HBA\"µ\"score_OOL\"µ\"score_CNS\"µ\"score_HTI\"µ\"score_ADL\"µ\"score_Qcash\"µ\"score_Qstore\"µ\"score_Aquire\"µ\"score_Car\"µ\"score_Epiqure\"µ\"score_Hooroo\"µ\"score_Golf\"\n";
        FileWriter writer = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = commonMethods.createFile("Prop_Scores_Full_List.csv", writer);
            commonMethods.writeToFile(fileWriter, propScoresFileHeader);

            for (String primPartyId : listOfPrimPartyIds) {
                commonMethods.writeToFile(fileWriter, primPartyId + commonMethods.getRandomValueFromLIstOfString(sampleScoresList));
            }

        } finally {
            commonMethods.closeFileWriter(writer);
            commonMethods.closeFileWriter(fileWriter);
        }
    }


    public static List<String> generatePropScoresFileForCustomerDNA(){

        String path = System.getProperty("user.dir");
        File sampleData = new File(path + "/src/main/resources/sample_prop_scores.csv");
        BufferedReader bufrd = null;
        try {
            bufrd = new BufferedReader(new FileReader(sampleData));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> details = new ArrayList<String>();
        try {
            for (String line = bufrd.readLine(); line != null; line = bufrd.readLine()) {
                details.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return details;
    }


    public static void main(String[] args) {
        generateFile();
    }


}
