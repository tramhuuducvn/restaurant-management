package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
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
    public void testFindByBillOrderIdAndMenuItemId_Success_IfNoExceptionOccur(){
        BillMenuItem item = repository.findByBillOrderIdAndMenuItemId(1L, 36L).orElse(null);
        System.out.println(item);
    }

    @Test
    public void testSoftDeleteByBillOrderIdAndMenuItemId_Success_IfNoExceptionOccur(){
        repository.softDeleteByBillOrderIdAndMenuItemId(36L, 12L);
    }
}