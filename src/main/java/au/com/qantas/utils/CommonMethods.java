package au.com.future-airlines.utils;

import au.com.future-airlines.nonfunctional.Offer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by boses on 7/07/2018.
 */
public class CommonMethods {

    private Map<String, Object> properties = PropsUtil.loadProperties();
    private DateFunctions dtFunctions = new DateFunctions();
    private List<String> finalRecord = null;

    public String getRandomValueFromLIstOfString(List<String> list) {
        String random = list.get(new Random().nextInt(list.size()));
        return random;
    }

    public Offer getAnOfferFromTheOffersCollection(List<Offer> offerCollection) {
        return offerCollection.get(new Random().nextInt(offerCollection.size()));
    }

    public List<Offer> getOffersSubset(List<Offer> offerCollection , int count) {
        List<Offer> finalList = new ArrayList<Offer>();
        Offer randomOffer = offerCollection.get(new Random().nextInt(offerCollection.size()));
        do {
            if (!finalList.contains(randomOffer)) {
                finalList.add(randomOffer);
            } else {
                randomOffer = offerCollection.get(new Random().nextInt(offerCollection.size()));
            }
        } while (finalList.size() != count);
        return finalList;
    }

    public List<String> getListOfRandomValuesFromLIstOfString(List<String> list, Integer count) {
        List<String> finalList = new ArrayList<String>();
        String random = list.get(new Random().nextInt(list.size()));
        do {
            if (!finalList.contains(random)) {
                finalList.add(random);
            } else {
                random = list.get(new Random().nextInt(list.size()));
            }
        } while (finalList.size() != count);
        return finalList;
    }

    public List<String> getRandomKeyValuePairFromMap(Map<String, String> stringMap) {
        finalRecord = new ArrayList<String>();
        // Get a random entry from the HashMap.
        Object[] crunchifyKeys = stringMap.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
//        System.out.println("************ Random Value ************ \n" + key + " :: " + stringMap.get(key));
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(stringMap.entrySet());
        // Shuffle the List so that Each time you get a different order...
        Collections.shuffle(list);
        finalRecord.add(key.toString());
        finalRecord.add(stringMap.get(key));
        return finalRecord;
    }


    public String getPipeSeparatedList(List<String> listOfStrings) {
        String output = "";
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (i != 0) {
                output = output + "|" + listOfStrings.get(i);
            } else {
                output = listOfStrings.get(i);
            }
        }
        return output;
    }

    public List<String> getListOfStrings(String possibilities) {
        return Arrays.asList(possibilities.split(","));
    }

    public FileWriter createFile(String fileName, FileWriter writer) {
        String systemPath = System.getProperty("user.dir");
        String filePath = systemPath + "/fileContainer/";
        System.out.println("  Generating the file in the following directory :  " + filePath + fileName);
//        FileWriter bw = null;
        try {
            writer = new FileWriter(filePath + fileName);
//            bw = new FileWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

    public FileWriter createActionHistoryFile(String fileName, FileWriter writer) {
        String systemPath = System.getProperty("user.dir");
        String filePath = systemPath + "/fileContainer/Action_History/";
        System.out.println("  Generating the file in the following directory :  " + filePath + fileName);
//        FileWriter bw = null;
        try {
            writer = new FileWriter(filePath + fileName);
//            bw = new FileWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }


    public FileWriter createFile(String fileName) {
        String systemPath = System.getProperty("user.dir");
        String filePath = systemPath + "/fileContainer/";
        System.out.println("  Generating the file in the following directory :  " + filePath + fileName);
//        BufferedWriter bw = null;
        try {
            return new FileWriter(filePath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void writeToFile(BufferedWriter bufferedWriter, String content) {
        try {
            bufferedWriter.write(content + "\n");
//            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void writeToFile(FileWriter fileWriter, String content) {
        try {
            fileWriter.write(content + "\n");
//            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeFileWriter(FileWriter writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flushMultipleFileWriters(Collection<FileWriter> allFileWriters){
        for (FileWriter writer : allFileWriters) {
            try {
                if (writer != null) {
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeMultipleFileWriters(Collection<FileWriter> allFileWriters){
        for (FileWriter writer : allFileWriters) {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeBufferedFile(BufferedWriter bufferedWriter) {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flushFileWriter(FileWriter writer) {
        try {
            if (writer != null) {
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileCreationDate() {
        String date = (String) properties.get("file.Run.Date");
        if (date.equalsIgnoreCase("")) {
            date = dtFunctions.getCurrentDateInyyyyMMddFormat();
        }
        return date.replace("-", "");
    }


    public FileWriter createOffersJsonFile(String fileName) {
        String systemPath = System.getProperty("user.dir");
        String filePath = systemPath + "/fileContainer/offers/";
        System.out.println("  Generating the file in the following directory :  " + filePath + fileName);
        try {
            return new FileWriter(filePath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
