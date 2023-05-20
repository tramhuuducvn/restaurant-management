package org.sdc.restaurant.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {
    private MenuItem item;

    @Before
    public void setUp() {
        item = new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple");
    }

    @Test
    public void getContent() {
        String target = "Hawaiian Pizza All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style Italian Ham Pineapple";
        Assert.assertEquals(target, item.getContent());
    }

    @Test
    public void toCSV() {
        String target = "Hawaiian Pizza, All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style, https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg, 300.0, Italian Ham Pineapple";
        Assert.assertEquals(target, item.toCSV());
    }
}