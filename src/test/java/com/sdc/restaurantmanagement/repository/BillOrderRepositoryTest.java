package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import com.sdc.restaurantmanagement.entity.BillOrder;
import com.sdc.restaurantmanagement.entity.MenuItem;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BillOrderRepositoryTest {
    @Autowired
    private BillOrderRepository repository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testSave(){

        BillOrder bill = BillOrder.builder()
                .orderedTime(new Date())
                .build();

        BillOrder createdBill = repository.save(bill);
        System.out.println(createdBill.toString());

        List<MenuItem> list = menuItemRepository.findAll();

        List<BillMenuItem> items = new ArrayList<>();
        for(MenuItem  menuItem : list){
            items.add(BillMenuItem.builder().billOrder(createdBill).menuItem(menuItem).build());
        }
        items.add(BillMenuItem.builder().billOrder(createdBill).menuItem(list.get(0)).build());

        createdBill.setItems(items);

        repository.save(createdBill);
    }



}