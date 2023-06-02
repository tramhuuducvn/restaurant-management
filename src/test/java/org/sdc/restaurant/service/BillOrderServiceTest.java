package org.sdc.restaurant.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.impl.BillOrderServiceImpl;
import org.sdc.restaurant.util.Helper;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class BillOrderServiceTest {
    private BillOrderServiceImpl billOrderService;

    @Mock
    private MenuItemService menuItemService;

    @Before
    public void setUp(){
        billOrderService = BillOrderServiceImpl.getInstance();
    }

    @Test
    public void getInstance() {
        BillOrderServiceImpl obj1 = BillOrderServiceImpl.getInstance();
        Assert.assertEquals(billOrderService, obj1);
    }



    @Test
    public void createBillOrder() {
        Mockito.when(menuItemService.findByName("Hawaiian Pizza")).thenReturn(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        BillOrder bill = new BillOrder(1, menuItemService.findByName("Hawaiian Pizza"), 1, new Date());
        billOrderService.create(bill);
        Assert.assertTrue(billOrderService.getById(1).contains(bill));
    }

    @Test
    public void updateQuantitiesItem() {
        billOrderService.updateQuantitiesItem(1, 1, 17);
        BillOrder billOrder = billOrderService.getBillByDishIndex(1,1);
        Assert.assertEquals(17, billOrder.getQuantities());
    }

    @Test
    public void remove() {
        boolean result = billOrderService.remove(1, 1);
        Assert.assertTrue(result);
    }

    @Test
    public void calculateTotalPrice(){
        double result = billOrderService.calculateTotalPrice(1);
        Assert.assertEquals(2174.8, result, 0);
    }


    @Test
    public void getMaxBillNumber(){
        int result = billOrderService.getMaxBillNumber();
        Assert.assertEquals(3, result);
    }

    @Test
    public void findIndexItemByDishId(){
        int result = billOrderService.findBillIndexByDishIndex(2, 3);
        Assert.assertEquals(5, result);
    }

    @Test
    public void getBillByDishIndex(){
        BillOrder result = billOrderService.getBillByDishIndex(2, 3);
        Assert.assertEquals("Banh Xeo", result.getItem().getName());
    }
    @Test
    public void getOrderedTime(){
        Date result = billOrderService.getOrderedDate(3);
        int diff = (int) Math.abs(Helper.getDateFromText("Tue May 30 15:06:35 ICT 2023").getTime() - result.getTime());
        Assert.assertTrue(diff < 1000);
    }
}