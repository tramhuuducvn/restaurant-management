package org.sdc.restaurant.service;

import org.sdc.restaurant.entity.MenuItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sdc.restaurant.service.impl.MenuItemServiceImpl;


public class MenuItemServiceTest {
    MenuItemServiceImpl menuItemService;

    @Before
    public void setUp(){
        menuItemService = MenuItemServiceImpl.getInstance();
        menuItemService.create(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        menuItemService.create(new MenuItem("Chicken Tom Yum Pizza", "Best marinated chicken with pineapple and mushroom on Spicy Lemon sauce. Enjoy our tasty Thai style pizza.", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu2.jpg", 350, "Italian Thai Chicken Mushroom Hot"));
        menuItemService.create(new MenuItem("Xiaolongbao", "Chinese steamed bun", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu3.jpg", 200, "Chinese Pork Recommended"));
        menuItemService.create(new MenuItem("Kimchi", "Traditional side dish made from salted and fermented vegetables", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu4.jpg", 50, "Korean Radish Cabbage"));
        menuItemService.create(new MenuItem("Oolong tea", "Partially fermented tea grown in the Alishan area", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu5.jpg", 30, "Hot Non-alcohol"));
        menuItemService.create(new MenuItem("Beer", "Fantastic flavors and authentic regional appeal beer", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu6.jpg", 60, "Alcohol"));
    }

    @Test
    public void getInstance() {
        MenuItemServiceImpl obj1 = MenuItemServiceImpl.getInstance();
        MenuItemServiceImpl obj2 = MenuItemServiceImpl.getInstance();
        Assert.assertEquals(obj1, obj2);
    }

    @Test
    public void createUnExistedMenuItem_True() {
        MenuItem result = menuItemService.create(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        Assert.assertNotNull(result);
    }

    @Test
    public void createExistedMenuItem_True() {
        MenuItem result = menuItemService.create(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        Assert.assertNull(result);
    }

    @Test
    public void findExistedIndex_1_True() {
       int index = menuItemService.findIndexById(3);
       Assert.assertTrue(index > -1);
    }

    @Test
    public void findUnExistedIndex_1000000_False() {
        int index = menuItemService.findIndexById(1000000);
        Assert.assertFalse(index > -1);
    }

    @Test
    public void findMenuItemByName_HawaiianPizza_True() {
        String name = "Hawaiian Pizza";
        MenuItem menuItem = menuItemService.findByName(name);
        Assert.assertTrue(menuItem != null && menuItem.getName().equals(name));
    }

    @Test
    public void findMenuItemByName_abcdef_False() {
        String name = "abcdef";
        MenuItem menuItem = menuItemService.findByName(name);
        Assert.assertNull(menuItem);
    }
}