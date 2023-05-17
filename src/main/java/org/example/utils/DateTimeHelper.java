package org.example.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
    public static Date getDateFromText(String dateString){
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            System.out.println(date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
