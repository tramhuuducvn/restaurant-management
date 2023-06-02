package org.sdc.restaurant.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sdc.restaurant.constant.SpecialCharacters;
import org.sdc.restaurant.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryTest {
    private MenuItemRepository repository;

    @Before
    public void setUp(){
        repository = MenuItemRepository.getInstance();
    }

    @Test
    public void getInstance() {
        MenuItemRepository obj1 = MenuItemRepository.getInstance();
        MenuItemRepository obj2 = MenuItemRepository.getInstance();

        Assert.assertEquals(obj1, obj2);
    }

    @Test
    public void createMenuItem(){
        String src = "Kim Chi Gio, Traditional side dish made from salted and fermented vegetables, https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu4.jpg, 50.0, Korea, false";
        String[] values = src.split(SpecialCharacters.COMMA_SPACE);
        MenuItem menuItem = repository.createMenuItem(1, values);

        Assert.assertEquals(src, menuItem.toCSV());
    }

//    @Test
//    public void readAndWrite(){
//        List<MenuItem> list = new ArrayList<>();
//        list.add(repository.createMenuItem(1, "Kim Chi Gio, Traditional side dish made from salted and fermented vegetables, https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu4.jpg, 50.0, Korea, false".split(SpecialCharacters.COMMA_SPACE)));
//        repository.writeAll(list);
//        List<MenuItem> actual = repository.readMenuItems();
//        Assert.assertEquals(list, actual);
//    }
}