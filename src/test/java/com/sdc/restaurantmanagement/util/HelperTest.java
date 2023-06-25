package com.sdc.restaurantmanagement.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelperTest {

    @Test
    @DisplayName("Test ValidURL: Return TRUE if the given url is valid")
    public void testIsValidURL_True_IfValidURL() {
        Assertions.assertTrue(Helper.isValidURL("https://google.com.vn"));
    }

    @Test
    @DisplayName("Test InvalidURL: Return FALSE if the given url is valid")
    public void testIsValidURL_False_IfInvalidURL() {
        Assertions.assertFalse(Helper.isValidURL("https:-//google.com.vn"));
    }
}