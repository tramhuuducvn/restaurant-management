package org.example.controller;

import org.example.entity.MenuItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuItemControllerTest {
    private static final MenuItemController menuItemController = new MenuItemController();
    @Test
    public void createUnExistedMenuItem() {
        boolean ret = menuItemController.createMenuItem(new MenuItem("Hawaiian Pizza", "All-time favourite toppings. Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        assertTrue(ret);
    }

    @Test
    public void createAnExistedMenuItem() {
        boolean ret = menuItemController.createMenuItem(new MenuItem("Hawaiian Pizza", "All-time favourite toppings. Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        assertFalse(ret);
    }
}