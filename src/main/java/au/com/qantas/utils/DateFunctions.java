package au.com.future-airlines.utils;

import junit.framework.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by boses on 21/06/2018.
 */
public class DateFunctions {

    public DateFunctions() {
    }

    public String getFormattedDate(String inputDate) {
        String depDate = "";
        Date date;
        SimpleDateFormat originalFormat = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = originalFormat.parse(inputDate);
            depDate = targetFormat.format(date);
        } catch (Exception ex) {
            // Handle Exception.
        }
        return depDate;
    }

    public String getFormattedDateInyyyyMMdd(String inputDate) {
        String depDate = "";
        Date date;
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = originalFormat.parse(inputDate);
            depDate = targetFormat.format(date);
        } catch (Exception ex) {
            // Handle Exception.
        }
        return depDate;
    }

    public String getCurrentDateInyyyyMMddWithTimestampFormat(String inputDate){
        String outputDate = "";
        Date date = new Date();
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = originalFormat.parse(inputDate);
            outputDate = targetFormat.format(date);
        } catch (Exception ex) {
            // Handle Exception.
        }
        return outputDate;

    }



    public String getCurrentDateInddMONyyUTCFormat(){
        String outputDate = "";
        Date date = new Date();
        SimpleDateFormat targetFormatUTC = new SimpleDateFormat("ddMMMyy");
        targetFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            outputDate = targetFormatUTC.format(date).toString().toUpperCase();
        } catch (Exception ex) {
            // Handle Exception.
        }
        return outputDate;

    }

    public String getFormattedDateInddMMyy(String inputDate) {
        String depDate = "";
        Date date;
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("ddMMyy");
        try {
            date = originalFormat.parse(inputDate);
            depDate = targetFormat.format(date);
        } catch (Exception ex) {
            // Handle Exception.
        }
        return depDate;
    }

    public String getFormattedDateInddMONFormat(String inputDate) {
        String depDate = "";
        Date date;
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("ddMMM");
        try {
            date = originalFormat.parse(inputDate);
            depDate = targetFormat.format(date);
        } catch (Exception ex) {
            // Handle Exception.
        }
        return depDate;
    }


    public String getFormattedTimeInHHMMFormat(String inputTime) {
        String depTime = "";
        Date date;
        SimpleDateFormat originalFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat targetFormat = new SimpleDateFormat("HH:mm");
        try {
            date = originalFormat.parse(inputTime);
            depTime = targetFormat.format(date);
        } catch (Exception ex) {
            // Handle Exception.
        }
        return depTime;
    }

    public String getCurrentDateInddMMHHmmFormat(){
        String outputDate = "";
        Date date = new Date();
        SimpleDateFormat targetFormat = new SimpleDateFormat("MMddHHmm");
        try {
            outputDate = targetFormat.format(date);
        } catch (Exception ex) {
            System.out.println("Error in Parsing the date !! \n" + ex.getMessage());
        }
        return outputDate;

    }

    public String getCurrentDateInyyyyMMddFormat(){
        String outputDate = "";
        Date date = new Date();
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            outputDate = targetFormat.format(date);

        } catch (Exception ex) {
            System.out.println("Error in Parsing the date !! \n" + ex.getMessage());
        }
        return outputDate;

    }

    public String getHHmmSSfromMillis(Long millis){

        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        System.out.println(hms);
        return hms;
    }


    public String getCurrentDateInyyyyMMddFormatWithHyphenAndTime(String inputDate){
        String outputDate = "";
        Date date = new Date();
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            date = originalFormat.parse(inputDate);
            outputDate = targetFormat.format(date);
        } catch (Exception ex) {
            Assert.fail("Failed to parse !!");
            ex.printStackTrace();
        }
        return outputDate;

    }

    public Date getDateInyyyymmddFormat(String inputDate){
        Date date = new Date();
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = originalFormat.parse(inputDate);
        } catch (Exception ex) {
            Assert.fail("Failed to parse !!");
            ex.printStackTrace();
        }
        return date;
    }


    public boolean compareDate(Date date1 ,  Date  date2){

        return date1.after(date2);
    }


    public List<String> getMondayToThursdayDatesInCurrentWeek(String inputDate){
        List<String> dates = new ArrayList<String>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        LocalDate formatDate = LocalDate.parse(inputDate, formatter);

        LocalDate monday = formatDate.with(DayOfWeek.MONDAY);
        LocalDate tuesday = formatDate.with(DayOfWeek.TUESDAY);
        LocalDate wednesday = formatDate.with(DayOfWeek.WEDNESDAY);
        LocalDate thursday = formatDate.with(DayOfWeek.THURSDAY);

        dates.add(getCurrentDateInyyyyMMddFormatWithHyphenAndTime(monday.toString()));
        dates.add(getCurrentDateInyyyyMMddFormatWithHyphenAndTime(tuesday.toString()));
        dates.add(getCurrentDateInyyyyMMddFormatWithHyphenAndTime(wednesday.toString()));
        dates.add(getCurrentDateInyyyyMMddFormatWithHyphenAndTime(thursday.toString()));

        return dates;
    }


    public List<String> getLast7DaysInCurrentWeek(String inputDate){
        List<String> dates = new ArrayList<String>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        // get starting date
        cal.add(Calendar.DAY_OF_YEAR, -8);

        // loop adding one day in each iteration
        for(int i = 0; i<= 6; i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dates.add(sdf.format(cal.getTime()));
        }

        return dates;
    }

    public Map<Integer, List<String>> get7DaysForEachWeekInLastNWeeks(String inputDate, Integer numberOfWeeks){
        Map<Integer, List<String>> mapOfWeekAndDays = new HashMap<Integer, List<String>>();
        List<String> dates = new ArrayList<String>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int weekNumber = 1; weekNumber<= numberOfWeeks; weekNumber++){
            dates = new ArrayList<String>();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            // get starting date
            cal.add(Calendar.DAY_OF_YEAR, (-8*weekNumber));

            // loop adding one day in each iteration
            for(int i = 0; i<= 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                dates.add(sdf.format(cal.getTime()));
            }
            mapOfWeekAndDays.put(weekNumber, dates);

        }
        return mapOfWeekAndDays;
    }

    public String getFormattedDateInyyMMdd(String inputDate) {
        String depDate = "";
        Date date;
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyMMdd");
        try {
            date = originalFormat.parse(inputDate);
            depDate = targetFormat.format(date);
        } catch (Exception ex) {
            // Handle Exception.
        }
        return depDate;
    }


    public LocalDate getNextSaturdayInCurrentWeek(){
        Date input = new Date();
        LocalDate formatDate = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate saturday = formatDate.with(DayOfWeek.SATURDAY);
        return saturday;
    }

    public LocalDate getNextThursdayInNextWeek(){
        Date input = new Date();
        LocalDate formatDate = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate thursday = formatDate.with(DayOfWeek.THURSDAY);
        return thursday.plusDays(7l);
    }

    public String getNextSaturdayInCurrentWeek(String inputDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate formatDate = LocalDate.parse(inputDate, formatter);

        LocalDate saturday = formatDate.with(DayOfWeek.SATURDAY);

        return saturday.toString();
    }


    public String getDateMinusNDaysFromGivenDate(String inputDate, Integer numberOfDays){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
        cal.add(Calendar.DATE, -numberOfDays);
        Date dateBeforeNDays = cal.getTime();
        return formatter.format(dateBeforeNDays);

    }


    public String getDatePlusNDaysFromGivenDate(String inputDate, Integer numberOfDays){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -numberOfDays);
        Date dateBeforeNDays = cal.getTime();
        return formatter.format(dateBeforeNDays);

    }


    /*public static void main(String args[]){
        DateFunctions dt = new DateFunctions();

        LocalDate today = LocalDate.now();

        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate thursday = today.with(DayOfWeek.THURSDAY);

        System.out.println("Today: " + today.toString());
        System.out.println(dt.getCurrentDateInyyyyMMddFormatWithHyphenAndTime(today.toString()));
        System.out.println("Monday of the Week: " + monday.toString());
        System.out.println("Thursday of the Week: " + thursday.toString());

        System.out.println(dt.getNextSaturdayInCurrentWeek());

    }*/


}
