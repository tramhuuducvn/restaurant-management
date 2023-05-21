package org.sdc.restaurant.util;

import org.sdc.restaurant.constant.SpecialCharacters;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
    /**
     * Search each word in the given keywords param
     * and return true if the given src contains that word, otherwise return false
     * @param src the given source
     * @param keywords the keyword need to search in src
     * @return TRUE if src matches with at least one word in the given keywords, the opposite is not.
     */
    public static boolean containKeyword(String src, String keywords){
        String trimmed = keywords.replaceAll("[,.]+", SpecialCharacters.SPACE).trim().toLowerCase();
        String[] values = trimmed.split(SpecialCharacters.SPACE);
        String lowerCaseSRC = src.toLowerCase();

        for (String value: values) {
            if(lowerCaseSRC.contains(value)){
                return true;
            }
        }

        return false;
    }

    public static double getInputDouble(String message, String errorMessage){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true){
            try {
                return scanner.nextDouble();
            }
            catch (InputMismatchException exception){
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }

    public static String getInputString(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true){
            try {
                return scanner.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }

    public static int getInputInteger(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);

        while (true){
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }
}