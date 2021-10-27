package info.seleniumcucumber.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ClockUtil {
  /* ---------------------------------------------------------------------------
    Specific date and time format
    --------------------------------------------------------------------------- */

    private static String getClock(String format) {
        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    // Japanese calendar format because it is the best date format
    public static String getDate() {
        return getClock("yyyyMMdd");
    }

    public static String getTime() {
        return getClock("HHmmss");
    }

    public static String getDate(String format) {
        return getClock(format);
    }

    public static String getTime(String format) {
        return getClock(format);
    }

	/* ---------------------------------------------------------------------------
    Used to get an unique token
	--------------------------------------------------------------------------- */

    public static String getMicroSeconds() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long howMany = (c.getTimeInMillis() - System.currentTimeMillis());
        return Long.toString(howMany);
    }
}
