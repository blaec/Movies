package org.blaec.movies.utils;

public class RuntimeUtils {
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;

    public static String format(int min) {
        int days = min / (MINUTES_IN_HOUR * HOURS_IN_DAY);
        int hours = (min / MINUTES_IN_HOUR) % HOURS_IN_DAY;
        int minutes = min % MINUTES_IN_HOUR;
        return days == 0
                ? String.format("%dh %dm", hours, minutes)
                : String.format("%dd %dh %dm", days, hours, minutes);
    }
}
