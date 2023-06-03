package org.sdc.restaurant.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.MenuItemService;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MenuItemControllerTest {
    @Mock
    private MenuItemService service;
    @InjectMocks
    private MenuItemController controller;

    @Test
    public void create() {
        MenuItem item = new MenuItem("Chinese Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple");
        Mockito.when(service.create(item)).thenReturn(item);
        Assert.assertTrue(controller.create(item));
    }

    @Test
    public void findById() {
        MenuItem item = new MenuItem("Chinese Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple");
        Mockito.when(service.findById(2)).thenReturn(item);

        Assert.assertEquals("Chinese Pizza", controller.findById(2).getName());
    }

    @Test
    public void updateById() {
        MenuItem item = new MenuItem("Chinese Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple");
        Mockito.when(service.updateById(2, item)).thenReturn(true);
        Assert.assertTrue(controller.updateById(2, item));
    }

    @Test
    public void deleteById() {
        Mockito.when(service.deleteById(2)).thenReturn(true);
        Assert.assertTrue(controller.deleteById(2));
    }

    @Test
    public void getMenu() {
        Mockito.when(service.getAll()).thenReturn(new ArrayList<>());
        Assert.assertEquals(new ArrayList<>(), controller.getMenu());
    }

    @Test
    public void search() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        Mockito.when(service.search("Hello", SearchMenuType.ALL)).thenReturn(list);
        Assert.assertEquals(list, controller.search("Hello", SearchMenuType.ALL));
    }
}