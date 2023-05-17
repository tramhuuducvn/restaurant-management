package org.example.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;


public class DateTimeHelperTest {
    @Test
    public void getDateFromText_DateNowEqualDateCloneFromText_True() {
        Date date = new Date();
        Date clone = DateTimeHelper.getDateFromText(date.toString());
        int diff = (int) Math.abs(date.getTime() - clone.getTime());

        Assert.assertTrue(diff < 1000);
    }
}