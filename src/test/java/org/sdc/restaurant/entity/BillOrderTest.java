package org.sdc.restaurant.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BillOrderTest {
    BillOrder billOrder;
    Date date;

    @Before
    public void setUp(){
        MenuItem item = new MenuItem("Hawaiian Pizza", "All-time favourite toppings. Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple");
        date = new Date();
        billOrder = new BillOrder(1, item, 3, date);
    }
    @Test
    public void getTotalPrice_ItemPriceIs300AndQuantitiesIs3_15() {
        Assert.assertEquals(900.0, billOrder.getTotalPrice(), 0.001);
    }

    @Test
    public void toCSV() {
        String target = "1, Hawaiian Pizza, 3, " + date.toString() + ", Italian Ham Pineapple";
        Assert.assertEquals(target, billOrder.toCSV());
    }
}