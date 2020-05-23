package be.hers.info.persea.util.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerseaDate {
    public static final String STANDARD_FORMAT = "dd/MM/yyyy";

    public static final String DATE_HOUR_FORMAT = "dd/MM/yyyy HH:mm";

    public static final String SHORT_FORMAT = "ddMMyyyy";

    /**/
    public static String getStandardFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_FORMAT);
        return formatter.format(new Date());
    }

    public static String getStandardFormattedDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_FORMAT);
        return formatter.format(date);
    }

    public static String getShortFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(SHORT_FORMAT);
        return formatter.format(new Date());
    }

    public static String getShortFormattedDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(SHORT_FORMAT);
        return formatter.format(date);
    }

    public static Date parseDate(String date) {
        return PerseaDate.parseDate(date, PerseaDate.STANDARD_FORMAT);
    }

    public static Date parseDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
           return null;
        }
    }
}
