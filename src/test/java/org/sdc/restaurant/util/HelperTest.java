package org.sdc.restaurant.util;

import org.junit.Assert;
import org.junit.Test;

public class HelperTest {
    @Test
    public void containKeyword_SourceContainKeyWord_True() {
        String src = "Hello World, My name is Pitbull";
        String keywords = "world name";
        boolean result = Helper.containKeyword(src, keywords);

        Assert.assertTrue(result);
    }

    @Test
    public void containKeyword_SourceNotContainKeyWord_False() {
        String src = "Hello World, My name is Pitbull";
        String keywords = "bully";
        boolean result = Helper.containKeyword(src, keywords);

        Assert.assertFalse(result);
    }

    @Test
    public void getInputDouble() {
        double result = Helper.getInputDouble("Enter your price", "Invalid value, please try again");
        Assert.assertEquals(9.9, result);
    }
}