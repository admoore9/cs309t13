package edu.iastate.utils;

import java.util.Date;

public class DateUtils {

    /**
     * Returns true if the current date is between the given dates
     * 
     * @param first The first date
     * @param second The second date
     * @return true if the current date is between the given dates, false
     *         otherwise.
     */
    public static boolean currentDateInRange(Date first, Date second) {
        Date now = new Date();
        return now.before(second) && now.after(first);
    }
}
