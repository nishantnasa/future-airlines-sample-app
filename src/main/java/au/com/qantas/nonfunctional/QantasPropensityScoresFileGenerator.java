package au.com.future-airlines.nonfunctional;

import au.com.future-airlines.utils.CommonMethods;

import java.io.*;
import java.util.*;

/**
 * Created by boses on 18/07/2018.
 */
public class future-airlinesPropensityScoresFileGenerator {


    private final String examplePropensityScores = "0.000113727529996739,0,0.0259999999999999,0.00279095293090831,0.004,0.0149999999999999,0.0461480735999999,0.00833333333299999,0.0495166739999999,0.000137751368697595,0.013333333333,0.0245929171552248,0.00027550273739519,0.008,0.0359999999999999,0.16,0.002,0.021,5.68637649983699E-05,0.0299999999999999,0.000333333333,0.182,0.0839912555999999,0.000666666666,0.006,0.001,0.0402498178999999,0.07,0.003,0.016,0.00866666666599999,0.626,0.00466666666599999,0.124418,0.0749999999999999,0.000234067125690959,0.000468134251381919,0.007,0.0218603708046443";
    private CommonMethods commonMethods = new CommonMethods();
    private Map<String, String> modelAndVertical = null;

    public void createAndWriteOutputRecords(List<Member> memberList, BufferedWriter sixthWriter, MemberPropensityScores memberPropensityScores) {
        memberPropensityScores = new MemberPropensityScores();
        if (modelAndVertical == null) {
            modelAndVertical = getExistingPropensityModels();
        }
        List<String> propensityScores = commonMethods.getListOfStrings(examplePropensityScores);
        List<String> models = new ArrayList<String>();
        for (Member member : memberList) {
            memberPropensityScores.setPARTY_ID(member.getPrimPartyId());
            memberPropensityScores.setPropensityScore(commonMethods.getRandomValueFromLIstOfString(propensityScores));
            models = commonMethods.getRandomKeyValuePairFromMap(modelAndVertical);
            memberPropensityScores.setPropensityModel(models.get(0));
            memberPropensityScores.setVertical(models.get(1));
            commonMethods.writeToFile(sixthWriter, memberPropensityScores.toString());
        }
    }

    private Map<String, String> getExistingPropensityModels() {
        String path = System.getProperty("user.dir");
        File QPropensityModels = new File(path + "/src/main/resources/future-airlinesPropensityModels.csv");
        BufferedReader bufrd = null;
        try {
            bufrd = new BufferedReader(new FileReader(QPropensityModels));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, String> existingModels = new HashMap<String, String>();
        List<String> details = new ArrayList<String>();
        try {
            for (String line = bufrd.readLine(); line != null; line = bufrd.readLine()) {
                details = Arrays.asList(line.split(","));
                existingModels.put(details.get(0), details.get(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return existingModels;
    }

}
