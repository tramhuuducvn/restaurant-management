package org.sdc.restaurant.util;

import org.sdc.restaurant.constant.SpecialCharacters;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
    /**
     * Search each word in the given keywords param
     * and return true if the given src contains that word, otherwise return false
     * 
     * @param src      the given source
     * @param keywords the keyword need to search in src
     * @return TRUE if src matches with at least one word in the given keywords, the
     *         opposite is not.
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
     *
     * @param message      enter message
     * @param errorMessage error message occur when caught input mismatch exception
     * @return the value of double type from input of user
     */
    public static double getInputDouble(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException exception) {
                exception.printStackTrace();
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }

    /**
     *
     * @param message      enter message
     * @param errorMessage error message occur when caught input mismatch
     *                     exception
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
     *
     * @param message      enter message
     * @param errorMessage error message occur when caught input mismatch
     *                     exception
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
}