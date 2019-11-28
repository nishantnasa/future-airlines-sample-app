package au.com.future-airlines.utils;

import java.util.HashMap;
import java.util.Map;


public class PropsUtil {

    public static Map<String, Object> loadProperties() {

        String resultingProfileToUse = SystemProperties.getProfile();
        System.out.println("The Activated Profile is : " + resultingProfileToUse);
        Map<String, Object> properties = new HashMap();
        properties.putAll(PropertyFileUtils.loadProperties("config/application-" + resultingProfileToUse + ".properties"));
        properties.put("spring.profiles.active", SystemProperties.getProfile());
        properties.put("dummy.Offer.Count", SystemProperties.getOfferCount());
        properties.put("Offer.per.Member", SystemProperties.getOfferPerMemberCount());
        properties.put("dummy.FFUser.Count", SystemProperties.getFFUserCount());
        properties.put("file.Run.Date", SystemProperties.getFileRunDate());

        return properties;
    }

}
