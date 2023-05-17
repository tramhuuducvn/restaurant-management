package org.example.repository;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuItemRepositoryTest {

    @Test
    public void getInstance() {
        MenuItemRepository obj1 = MenuItemRepository.getInstance();
        MenuItemRepository obj2 = MenuItemRepository.getInstance();

        Assert.assertEquals(obj1, obj2);
    }
}