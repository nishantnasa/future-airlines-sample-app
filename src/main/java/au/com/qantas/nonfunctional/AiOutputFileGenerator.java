package au.com.future-airlines.nonfunctional;

import au.com.future-airlines.utils.CommonMethods;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by boses on 18/07/2018.
 */
public class AiOutputFileGenerator {


    CommonMethods commonMethods = new CommonMethods();

    public void createAndWriteOutputRecords(Integer partyId, String offerCode, AiOutput aiOutput,
                                            Map<Integer, FileWriter> fileWriters, FileWriter aiModelFileWriter, boolean splitSwitch) {
        aiOutput = new AiOutput();
        aiOutput.setPARTY_ID(partyId);
        aiOutput.setACTION_ID(offerCode);
        aiOutput = getOfferValuesAssigned(aiOutput);
        if (splitSwitch) {
//        FileWriter aiModelFileWriter = null;
            if (partyId < 11000000) {
                aiModelFileWriter = fileWriters.get(0);
            } else {
                aiModelFileWriter = fileWriters.get((partyId - 10000000) / 1000000);
            }
        }

        commonMethods.writeToFile(aiModelFileWriter, aiOutput.toString());
    }


    public List<String> getRandomisedOfferValueList() {
        List<String> values = new ArrayList<String>();
        values.add("0.01787963663921467,0.0185576883566489,0.0013182660516534949");
        values.add("0.010616945018963068,0.010163764561219478,0.0012192073137965477");
        values.add("0.025336452201939037,0.02513661096511281,0.0013570262611765807");
        values.add("0.015465677021313417,0.01617860936501086,0.0011083909783990696");
        values.add("0.002713102115056048,0.003491230436072149,6.254433900844308E-4");
        values.add("0.018755222765599582,0.015449394694269838,0.0011619567905239885");
        values.add("0.018457783272482164,0.01970015904475199,0.0014067640236177263");
        values.add("0.033822941841212026,0.03526846185948359,0.001968280439766178");
        values.add("0.010224909463864782,0.008100589343834631,0.0013738512671741705");
        values.add("0.01948240131073596,0.02068030585809224,0.0012460239878823102");
        values.add("0.002416426415417487,0.00385205410664752,6.966838788183594E-4");
        values.add("0.003047034945551009,0.003458034407809437,4.784655304377507E-4");
        values.add("0.015549308526813663,0.015299675080246709,0.0012537077410903592");
        values.add("0.00362060119635499,0.004478761000915759,5.38557569103465E-4");
        values.add("0.014930468595300152,0.016253854016143324,0.001530979964991028");
        return values;
    }


    public AiOutput getOfferValuesAssigned(AiOutput aiOutput) {
        String valueDetails = commonMethods.getRandomValueFromLIstOfString(getRandomisedOfferValueList());
        List<String> details = Arrays.asList(valueDetails.split(","));
        aiOutput.setProblem_Sample(details.get(0));
        aiOutput.setProblem_Mean(details.get(1));
        aiOutput.setProblem_StandardDeviation(details.get(2));
        return aiOutput;
    }

}
