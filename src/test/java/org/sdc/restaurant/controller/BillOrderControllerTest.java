package org.sdc.restaurant.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.service.BillOrderService;


@RunWith(MockitoJUnitRunner.class)
public class BillOrderControllerTest {

    @Mock
    private BillOrderService service;
    @InjectMocks
    private BillOrderController controller;

    @Test
    public void getMaxBillNumber() {
        Mockito.when(service.getMaxBillNumber()).thenReturn(1340);
        Assert.assertEquals(1340, controller.getMaxBillNumber());
    }

    @Test
    public void createSuccess() {
        BillOrder billOrder = new BillOrder();
        Mockito.when(service.create(billOrder)).thenReturn(true);
        Assert.assertTrue(controller.create(billOrder));
    }

    @Test
    public void createFailed() {
        BillOrder billOrder = new BillOrder();
        Mockito.when(service.create(billOrder)).thenReturn(false);
        Assert.assertFalse(controller.create(billOrder));
    }

    @Test
    public void updateQuantitiesItem() {
        Mockito.when(service.updateQuantitiesItem(1,1,10)).thenReturn(true);
        Assert.assertTrue(controller.updateQuantitiesItem(1,1,10));
    }

    @Test
    public void removeDish() {
        Mockito.when(service.remove(1,1)).thenReturn(true);
        Assert.assertTrue(controller.removeDish(1,1));
    }
}