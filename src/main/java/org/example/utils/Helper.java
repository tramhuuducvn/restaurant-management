package org.example.utils;

public class Helper {
    /**
     * Search each word in the given keywords param
     * and return true if the given src contains that word, otherwise return false
     * @param src the given source
     * @param keywords the keyword need to search in src
     * @return TRUE if src matches with at least one word in the given keywords, the opposite is not.
     */
    public static boolean containKeyword(String src, String keywords){
        String trimmed = keywords.replaceAll("[\\p{Punct},]+", " ").trim().toLowerCase();
        String[] values = trimmed.split(" ");
        String lowerCaseSRC = src.toLowerCase();

        for (String value: values) {
            if(lowerCaseSRC.contains(value)){
                return true;
            }
        }

        return false;
    }
}
