package org.example.service;

import org.example.entity.MenuItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MenuItemServiceTest {
    MenuItemService menuItemService;

    @Before
    public void setUp(){
        menuItemService = MenuItemService.getInstance();
        menuItemService.createMenuItem(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        menuItemService.createMenuItem(new MenuItem("Chicken Tom Yum Pizza", "Best marinated chicken with pineapple and mushroom on Spicy Lemon sauce. Enjoy our tasty Thai style pizza.", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu2.jpg", 350, "Italian Thai Chicken Mushroom Hot"));
        menuItemService.createMenuItem(new MenuItem("Xiaolongbao", "Chinese steamed bun", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu3.jpg", 200, "Chinese Pork Recommended"));
        menuItemService.createMenuItem(new MenuItem("Kimchi", "Traditional side dish made from salted and fermented vegetables", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu4.jpg", 50, "Korean Radish Cabbage"));
        menuItemService.createMenuItem(new MenuItem("Oolong tea", "Partially fermented tea grown in the Alishan area", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu5.jpg", 30, "Hot Non-alcohol"));
        menuItemService.createMenuItem(new MenuItem("Beer", "Fantastic flavors and authentic regional appeal beer", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu6.jpg", 60, "Alcohol"));
    }

    @Test
    public void getInstance() {
        MenuItemService obj1 = MenuItemService.getInstance();
        MenuItemService obj2 = MenuItemService.getInstance();
        Assert.assertEquals(obj1, obj2);
    }

    @Test
    public void createUnExistedMenuItem_True() {
        boolean result = menuItemService.createMenuItem(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        Assert.assertTrue(result);
    }

    @Test
    public void createExistedMenuItem_True() {
        boolean result = menuItemService.createMenuItem(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        Assert.assertFalse(result);
    }

    @Test
    public void findExistedIndex_1_True() {
       int index = menuItemService.findIndex(3);
       Assert.assertTrue(index > -1);
    }

    @Test
    public void findUnExistedIndex_1000000_False() {
        int index = menuItemService.findIndex(1000000);
        Assert.assertFalse(index > -1);
    }

    @Test
    public void findMenuItemByName_HawaiianPizza_True() {
        String name = "Hawaiian Pizza";
        MenuItem menuItem = menuItemService.findMenuItemByName(name);
        Assert.assertTrue(menuItem != null && menuItem.getName().equals(name));
    }

    @Test
    public void findMenuItemByName_abcdef_False() {
        String name = "abcdef";
        MenuItem menuItem = menuItemService.findMenuItemByName(name);
        Assert.assertFalse(menuItem != null);
    }

    @Test
    public void updateMenuItemById() {

    }

    @Test
    public void deleteMenuItemById() {

    }

    @Test
    public void getMenuItems() {

    }

    @Test
    public void search() {

    }

    @Test
    public void saveToFile() {

    }
}