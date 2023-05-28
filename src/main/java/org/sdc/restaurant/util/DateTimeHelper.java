package org.sdc.restaurant.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateTimeHelper have all method needed for format and convert datetime
 */
public class DateTimeHelper {
    /**
     * Convert text to date object by special format (EEE MMM dd HH:mm:ss zzz yyyy).
     * Return a date if the given text is valid, and return date now if the text is invalid.
     * @param dateString a text illustrating for a date
     * @return a Date object from dateString
     */
    public static Date getDateFromText(String dateString){
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
