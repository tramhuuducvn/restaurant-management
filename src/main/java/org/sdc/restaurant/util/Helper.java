package org.sdc.restaurant.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.sdc.restaurant.constant.Constant;
import org.sdc.restaurant.constant.SpecialCharacters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Helper have common functions usually use in this project
 */
public class Helper {
    /**
     * Search each word in the given keywords param
     * and return true if the given src contains that word, otherwise return false
     * 
     * @param src      the given source
     * @param keywords the keyword need to search in src
     * @return TRUE if src matches with at least one word in the given keywords, the opposite is not.
     */
    public static boolean containKeyword(String src, String keywords) {
        String trimmed = keywords.replaceAll("[,.]+", SpecialCharacters.SPACE).trim().toLowerCase();
        String[] values = trimmed.split(SpecialCharacters.SPACE);
        String lowerCaseSRC = src.toLowerCase();

        for (String value : values) {
            if (lowerCaseSRC.contains(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get input double from console
     * @param message      enter message
     * @param errorMessage error message occur when caught input mismatch exception
     * @return the value of double type from input of user
     */
    public static double getInputPositiveDouble(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                double value = scanner.nextDouble();
                if(value <= 0){
                    System.out.println(errorMessage);
                    continue;
                }
                return value;
            } catch (InputMismatchException exception) {
                exception.printStackTrace();
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }

    /**
     * Get input url from console
     * @param message      enter message
     * @param errorMessage error message occur when caught input mismatch exception
     * @return the value of url from input of user
     */
    public static String getInputURLValue(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);

        String line;

        while (true) {
            line = scanner.nextLine();
            UrlValidator validator = new UrlValidator();
            if(validator.isValid(line)){
                return line;
            }
            System.out.println(errorMessage);
        }
    }

    /**
     * Get input string from console
     * @param message      enter message
     * @param errorMessage error message occur when caught input mismatch exception
     * @return the value of String type from input of user
     */
    public static String getInputString(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }

    /**
     * Get input integer from console
     * @param message      enter message
     * @param errorMessage error message occur when caught input mismatch exception
     * @return the value of int type from input of user
     */
    public static int getInputInteger(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);

        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }

    /**
     * remove extra spaces and commas
     * 
     * @param value the given string src
     * @return a formatted string from src
     */
    public static String reformatText(String value) {
        return value.replaceAll(SpecialCharacters.COMMA, SpecialCharacters.SPACE).trim().replaceAll(" +",
                SpecialCharacters.SPACE);
    }

    /**
     * Check nearly similar name
     * @param str1 first value
     * @param str2 second value
     * @return true if src is similar to target
     */
    public static boolean checkNearlySimilarName(String str1, String str2) {
        String a = reformatText(str1);
        String b = reformatText(str2);
        return a.toLowerCase().equals(b.toLowerCase());
    }

    /**
     * Convert text to date object by special format (EEE MMM dd HH:mm:ss zzz yyyy).
     * Return a date if the given text is valid, and return date now if the text is invalid.
     * @param dateString a text illustrating for a date
     * @return a Date object from dateString
     */
    public static Date getDateFromText(String dateString){
        DateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE_TIME);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}