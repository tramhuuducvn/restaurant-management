package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class MenuItemRepositoryTest {
    @Autowired
    private MenuItemRepository repository;

    @Test
    void testFindAllByIsDeleted() {

    }

    @Test
    void testFindByIdAndIsDeletedFalse_NotNull_IfItemFound() {
        MenuItem item = repository.findByIdAndIsDeletedFalse(12L).orElse(null);
        Assertions.assertNotNull(item);
    }

    @Test
    void testFindByIdAndIsDeletedFalse_Null_IfItemNotFound() {
        MenuItem item = repository.findByIdAndIsDeletedFalse(11L).orElse(null);
        Assertions.assertNull(item);
    }

    @Test
    void testSearch() {

    }
}