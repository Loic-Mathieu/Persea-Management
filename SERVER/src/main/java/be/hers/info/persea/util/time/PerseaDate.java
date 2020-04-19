package be.hers.info.persea.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PerseaDate {
    public static final String STANDARD_FORMAT = "dd/MM/yyyy";

    public static final String SHORT_FORMAT = "ddMMyyyy";

    /**/
    public static String getStandardFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_FORMAT);
        return formatter.format(new Date());
    }

    public static String getShortFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(SHORT_FORMAT);
        return formatter.format(new Date());
    }
}
