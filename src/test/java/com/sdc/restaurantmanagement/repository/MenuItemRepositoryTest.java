package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class MenuItemRepositoryTest {
    @Autowired
    private MenuItemRepository repository;

    @Test
    void testFindByIdAndIsDeletedFalse_NotNull_IfItemFound() {
        MenuItem item = repository.findByIdAndIsDeletedFalse(1L).orElse(null);
        Assertions.assertNotNull(item);
    }

    @Test
    void testFindByIdAndIsDeletedFalse_Null_IfItemNotFound() {
        MenuItem item = repository.findByIdAndIsDeletedFalse(11L).orElse(null);
        Assertions.assertNull(item);
    }

    @Test
    void testSearchByName_One_ResultSizeIsOne() {
        List<MenuItem> items = repository.search("Pizza", "", "");
        Assertions.assertEquals(1, items.size());
    }

    @Test
    void testSearchByDescription_Two_ResultSizeIsOne() {
        List<MenuItem> items = repository.search("", "ea", "");
        Assertions.assertEquals(3, items.size());
    }

    @Test
    void testFindAllByIsDeletedFalse() {
        List<MenuItem> items = repository.findAllByIsDeletedFalse();
        Assertions.assertEquals(19, items.size());
    }
}