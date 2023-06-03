package org.sdc.restaurant.util;

import org.junit.Assert;
import org.junit.Test;
import java.util.Date;

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
    public void removeSpacesAndCommas(){
        String target = "Hello World!";
        String actual = Helper.reformatText("Hello   ,   World!");
        Assert.assertEquals(target, actual);
    }

    @Test
    public void checkNearlySimilarName_True(){
        boolean result = Helper.checkNearlySimilarName("Banh Xeo", "banh xeo");
        Assert.assertTrue(result);
    }

    @Test
    public void getDateFromText_DateNowEqualDateCloneFromText_True() {
        Date date = new Date();
        Date clone = Helper.getDateFromText(date.toString());
        int diff = (int) Math.abs(date.getTime() - clone.getTime());
        Assert.assertTrue(diff < 1000);
    }
}