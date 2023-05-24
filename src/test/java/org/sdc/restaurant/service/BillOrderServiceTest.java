package org.sdc.restaurant.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.impl.BillOrderServiceImpl;
import org.sdc.restaurant.service.impl.MenuItemServiceImpl;

import java.util.Date;

public class BillOrderServiceTest {
    private BillOrderServiceImpl billOrderService;
    private MenuItemServiceImpl menuItemService;

    @Before
    public void setUp(){
        billOrderService = BillOrderServiceImpl.getInstance();
        menuItemService = MenuItemServiceImpl.getInstance();
        menuItemService.create(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        menuItemService.create(new MenuItem("Chicken Tom Yum Pizza", "Best marinated chicken with pineapple and mushroom on Spicy Lemon sauce. Enjoy our tasty Thai style pizza.", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu2.jpg", 350, "Italian Thai Chicken Mushroom Hot"));
        menuItemService.create(new MenuItem("Xiaolongbao", "Chinese steamed bun", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu3.jpg", 200, "Chinese Pork Recommended"));
        menuItemService.create(new MenuItem("Kimchi", "Traditional   side dish made from salted and fermented vegetables", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu4.jpg", 50, "Korean Radish Cabbage"));
        menuItemService.create(new MenuItem("Oolong tea", "Partially fermented tea grown in the Alishan area", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu5.jpg", 30, "Hot Non-alcohol"));
        menuItemService.create(new MenuItem("Beer", "Fantastic flavors and authentic regional appeal beer", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu6.jpg", 60, "Alcohol"));
    }

    @Test
    public void getInstance() {
        BillOrderServiceImpl obj1 = BillOrderServiceImpl.getInstance();
        Assert.assertEquals(billOrderService, obj1);
    }

    @Test
    public void createBillOrder() {
        BillOrder bill = new BillOrder(1, menuItemService.findByName("Hawaiian Pizza"), 1, new Date());
        billOrderService.create(bill);
        Assert.assertTrue(billOrderService.getById(1).contains(bill));
    }
}