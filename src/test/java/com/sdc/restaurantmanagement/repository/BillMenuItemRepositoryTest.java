package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BillMenuItemRepositoryTest {

    @Autowired
    BillMenuItemRepository repository;

    @Test
    public void test(){
        BillMenuItem item = repository.findByMenuItemIdAndBillOrderId(1L, 36L).orElse(null);
        System.out.println(item);
    }
}