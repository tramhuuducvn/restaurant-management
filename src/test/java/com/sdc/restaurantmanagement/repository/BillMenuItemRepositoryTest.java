package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BillMenuItemRepositoryTest {

    @Autowired
    BillMenuItemRepository repository;

    @Test
    public void testFindByBillOrderIdAndMenuItemId_NotNull_IfItemFound(){
        BillMenuItem item = repository.findByBillOrderIdAndMenuItemId(7L, 1L).orElse(null);
        Assertions.assertNotNull(item);
    }

    @Test
    public void testFindByBillOrderIdAndMenuItemId_Null_IfItemNotFound(){
        BillMenuItem item = repository.findByBillOrderIdAndMenuItemId(7L, 991L).orElse(null);
        Assertions.assertNull(item);
    }

    @Test
    public void testSoftDeleteByBillOrderIdAndMenuItemId_Success_IfNoExceptionOccur(){
        repository.softDeleteByBillOrderIdAndMenuItemId(7L, 1L);
        BillMenuItem item = repository.findByBillOrderIdAndMenuItemId(7L, 1L).orElse(null);
        Assertions.assertTrue(item.isDeleted());
    }
}