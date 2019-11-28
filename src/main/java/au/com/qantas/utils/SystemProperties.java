package au.com.future-airlines.utils;

public class SystemProperties {

    public static String getOfferCount() {
        return System.getProperty("dummy.Offer.Count");
    }

    public static String getOfferPerMemberCount() {
        return System.getProperty("Offer.per.Member");
    }

    public static String getFFUserCount() {
        return System.getProperty("dummy.FFUser.Count");
    }

    public static String getProfile() {
        return System.getProperty("spring.profiles.active");
    }

    public static String getFileRunDate() {
        return System.getProperty("file.Run.Date");
    }

}
