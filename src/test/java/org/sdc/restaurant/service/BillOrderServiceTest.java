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
    public void createBillOrder_True() {
        Mockito.when(menuItemService.findByName("Hawaiian Pizza")).thenReturn(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
        BillOrder bill = new BillOrder(1, menuItemService.findByName("Hawaiian Pizza"), 1, new Date());
        billOrderService.create(bill);
        Assert.assertTrue(billOrderService.getById(1).contains(bill));
    }

    @Test
    public void updateQuantitiesItem_Bill_1_Dish_1_Quantity_17() {
        billOrderService.updateQuantitiesItem(1, 1, 17);
        BillOrder billOrder = billOrderService.getBillByDishIndex(1,1);
        Assert.assertEquals(17, billOrder.getQuantities());
    }

    @Test
    public void findBillIndexByDishIndex_Bill2_Dish3_5(){
        int result = billOrderService.findBillIndexByDishIndex(2, 3);
        Assert.assertEquals(4, result);
    }

    @Test
    public void removeBill_1_Dish_1_Result_True() {
        boolean result = billOrderService.remove(1, 1);
        Assert.assertTrue(result);
    }

    @Test
    public void calculateTotalPrice_Bill_1_Result_2474_8(){
        double result = billOrderService.calculateTotalPrice(1);
        Assert.assertEquals(2474.8, result, 0);
    }

    @Test
    public void getMaxBillNumber_Result3() {
        int result = billOrderService.getMaxBillNumber();
        Assert.assertEquals(3, result);
    }

    @Test
    public void getBillByDishIndex_Bill2_DishIndex2_HawaiianPizza(){
        BillOrder result = billOrderService.getBillByDishIndex(2, 2);
        Assert.assertEquals("Hawaiian Pizza", result.getItem().getName());
    }

    @Test
    public void getOrderedTimeOfBill3(){
        Date result = billOrderService.getOrderedDate(3);
        int diff = (int) Math.abs(Helper.getDateFromText("Tue May 30 15:06:35 ICT 2023").getTime() - result.getTime());
        Assert.assertTrue(diff < 1000);
    }
}