package au.com.future-airlines.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PropertyFileUtils {

    public static Map<String,Object> loadProperties(String fileName){
        Map<String,Object> res = new HashMap();

        try {
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(fileName);
            Iterator<String> keys = propertiesConfiguration.getKeys();
            while (keys.hasNext()) {
                String key =  keys.next();
                List<Object> values = propertiesConfiguration.getList(key);
                String theValue = "";
                for (Object value : values) {
                    theValue += (String)value;
                    theValue += ",";
                }
                theValue = theValue.substring(0, theValue.length() -1);
                res.put(key,theValue);
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return res;
    }
}
