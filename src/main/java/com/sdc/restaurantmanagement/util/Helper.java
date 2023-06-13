package com.sdc.restaurantmanagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.validator.routines.UrlValidator;

public class Helper {
    /**
     * Validate URL
     * @param URL string url
     * @return true if the given url is valid, otherwise return false.
     */
    public static boolean isValidURL(String URL){
        UrlValidator validator = new UrlValidator();
        return validator.isValid(URL);
    }

    /**
     * Convert object to json format
     * @param obj any object
     * @return json data under string type
     */
    public static String toJSON(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
