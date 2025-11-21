import java.io.*;
import java.util.*;


public class DurationDate implements Serializable {
    private static final List<DurationDate> extent = new ArrayList<>();
    private static final String EXT_FILE = "duration_extent.ser";


    private Date dateOfEstablishment;
    private Date dateOfExpiration;


    public DurationDate(Date dateOfEstablishment, Date dateOfExpiration) {
        this.dateOfEstablishment = dateOfEstablishment;
        this.dateOfExpiration = dateOfExpiration;
        if (!(TimeLeft() > 0)) {
            throw new InvalidValueException("The duration exceeds the maximum number of days");
        }
        extent.add(this);
    }


    public int TimeLeft() {
        return dateOfExpiration.getDays() - dateOfEstablishment.getDays();
    }


    public static List<DurationDate> getExtent() {
        return extent;
    }


    public static void saveExtent() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXT_FILE));
        oos.writeObject(extent);
        oos.close();
    }


    public static void loadExtent() throws Exception {
        File f = new File(EXT_FILE);
        if (!f.exists()) return;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXT_FILE));
        List<DurationDate> list = (List<DurationDate>) ois.readObject();
        extent.clear();
        extent.addAll(list);
        ois.close();
    }


    public static class Date implements Serializable {
        private int year;
        private int month;
        private int day;
        private int hour;


        Date(int year, int month, int day, int hour) {
            if (month < 1 || month > 12 || day < 1 || day > 31 || hour < 0 || hour > 23) {
                throw new InvalidFormatException("Invalid date format");
            }
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
        }


        private static final int[] daysInMonth = {
                31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };


        private boolean isLeapYear() {
            return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
        }


        public int getDays() {
            int days = day;
            for (int i = 0; i < month - 1; i++) {
                days += daysInMonth[i];
            }
            if (isLeapYear() && month > 2) {
                days += 1;
            }
            return days;
        }
    }
}