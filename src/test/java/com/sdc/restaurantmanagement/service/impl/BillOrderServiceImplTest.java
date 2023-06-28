package com.sdc.restaurantmanagement.service.impl;

import com.sdc.restaurantmanagement.constant.Constant;
import com.sdc.restaurantmanagement.entity.BillMenuItem;
import com.sdc.restaurantmanagement.entity.BillOrder;
import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.exception.AlreadyExistException;
import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.BillOrderResponse;
import com.sdc.restaurantmanagement.repository.BillMenuItemRepository;
import com.sdc.restaurantmanagement.repository.BillOrderRepository;
import com.sdc.restaurantmanagement.repository.MenuItemRepository;
import com.sdc.restaurantmanagement.service.BillOrderService;
import com.sdc.restaurantmanagement.service.MenuItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)
class BillOrderServiceImplTest {
    @TestConfiguration
    public static class BillOrderServiceConfiguration {
        @Bean
        public BillOrderService billOrderService() {
            return new BillOrderServiceImpl();
        }

        @Bean
        public MenuItemService menuItemService() {
            return new MenuItemServiceImpl();
        }
    }

    @Autowired
    private BillOrderService billOrderService;

    @Autowired
    private MenuItemService menuItemService;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @MockBean
    private BillOrderRepository billOrderRepository;

    @MockBean
    private BillMenuItemRepository billMenuItemRepository;

    @Test
    void testGetAll_ListBillOrder_GetListBillOrderSuccess() {
        List<BillOrder> billOrders = new ArrayList<>();
        billOrders.add(BillOrder.builder().items(new ArrayList<>()).build());
        Mockito.when(billOrderRepository.findAll()).thenReturn(billOrders);
        List<BillOrderResponse> list = billOrderService.getAll();

        Assertions.assertEquals(1, list.size());
    }

    @Test
    void testGetById_NotNull_IfItemFound() {
        Mockito.when(billOrderRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(BillOrder.builder().items(new ArrayList<>()).build()));
        BillOrderResponse bill = billOrderService.getById(Mockito.anyLong());

        Assertions.assertNotNull(bill);
    }

    @Test
    void testGetById_Exception_IfItemNotFound() {
        Mockito.when(billOrderRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            BillOrderResponse bill = billOrderService.getById(1L);
        });
        Assertions.assertEquals(Constant.NOT_FOUND_THE_BILL_ORDER_WITH_ID + 1L, exception.getMessage());
    }

    @Test
    void testCreateBillOrder_NotNull_CreateSuccess() {
        Date date = new Date();
        List<BillMenuItemRequest> items = new ArrayList<>();
        items.add(new BillMenuItemRequest(1L, 2));
        items.add(new BillMenuItemRequest(2L, 3));
        items.add(new BillMenuItemRequest(3L, 4));

        BillOrder billOrder = BillOrder.builder().createTime(date).build();

        Mockito.when(billOrderRepository.save(Mockito.any())).thenReturn(billOrder);

        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(1L))
                .thenReturn(Optional.of(MenuItem.builder().price(1.0).isDeleted(false).build()));
        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(2L))
                .thenReturn(Optional.of(MenuItem.builder().price(1.0).isDeleted(false).build()));
        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(3L))
                .thenReturn(Optional.of(MenuItem.builder().price(1.0).isDeleted(false).build()));

        BillOrderResponse billOrderResponse = billOrderService.create(items);

        Assertions.assertNotNull(billOrderResponse);
    }

    @Test
    void testCreateBillOrder_Exception_IfMenuItemNotFound() {
        Date date = new Date();
        List<BillMenuItemRequest> items = new ArrayList<>();
        items.add(new BillMenuItemRequest(1L, 2));
        items.add(new BillMenuItemRequest(2L, 3));
        items.add(new BillMenuItemRequest(3L, 4));

        BillOrder billOrder = BillOrder.builder().createTime(date).build();

        Mockito.when(billOrderRepository.save(Mockito.any())).thenReturn(billOrder);

        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(1L))
                .thenReturn(Optional.of(MenuItem.builder().price(1.0).isDeleted(false).build()));
        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(2L)).thenReturn(Optional.empty());
        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(3L))
                .thenReturn(Optional.of(MenuItem.builder().price(1.0).isDeleted(false).build()));

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            BillOrderResponse billOrderResponse = billOrderService.create(items);
        });

        Assertions.assertEquals("Menu item with id 2 doesn't exist!", exception.getMessage());
    }

    @Test
    void testAddBillMenuItem_NoSuchElementException_IfBillAndMenuItemNotFound() {
        // BillOrder billOrder = BillOrder.builder().id(10L).createTime(new
        // Date()).build();

        Mockito.when(billOrderRepository.findById(10L)).thenReturn(Optional.empty());

        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(4L))
                .thenReturn(Optional.of(MenuItem.builder().build()));

        Mockito.when(billMenuItemRepository.findByBillOrderIdAndMenuItemId(10L, 4L))
                .thenReturn(Optional.of(BillMenuItem.builder().build()));

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            billOrderService.addBillMenuItem(10L, new BillMenuItemRequest(1L, 5));
        });

        Assertions.assertEquals("There is no bill order with id 10", exception.getMessage());
    }

    @Test
    void testAddBillMenuItem_AlreadyExistException_IfBillAndMenuItemFound() {
        BillOrder billOrder = BillOrder.builder().id(10L).isPaid(false).createTime(new Date()).build();

        Mockito.when(billOrderRepository.findById(10L)).thenReturn(Optional.of(billOrder));
        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(4L))
                .thenReturn(Optional.of(MenuItem.builder().build()));
        Mockito.when(billMenuItemRepository.findByBillOrderIdAndMenuItemId(10L, 4L))
                .thenReturn(Optional.of(BillMenuItem.builder().build()));

        Exception exception = Assertions.assertThrows(AlreadyExistException.class, () -> {
            billOrderService.addBillMenuItem(10L, new BillMenuItemRequest(4L, 5));
        });

        Assertions.assertEquals(Constant.ITEM_ALREADY_EXIST_IN_BILL, exception.getMessage());
    }

    @Test
    void testUpdateMenuItemQuantity_NoSuchElementException_IfBillOrderNotFound() {
        Mockito.when(billOrderRepository.findById(10L)).thenReturn(Optional.empty());
        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(4L))
                .thenReturn(Optional.of(MenuItem.builder().build()));
        Mockito.when(billMenuItemRepository.findByBillOrderIdAndMenuItemId(10L, 4L))
                .thenReturn(Optional.of(BillMenuItem.builder().build()));

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            billOrderService.updateMenuItemQuantity(10L, new BillMenuItemRequest(1L, 5));
        });

        Assertions.assertEquals("There is no bill order with id 10", exception.getMessage());
    }

    @Test
    void testUpdateMenuItemQuantity_NoSuchElementException_IfBillAndMenuItemNotFound() {
        BillOrder billOrder = BillOrder.builder().id(10L).createTime(new Date()).build();

        Mockito.when(billOrderRepository.findById(10L)).thenReturn(Optional.of(billOrder));
        Mockito.when(menuItemRepository.findByIdAndIsDeletedFalse(4L))
                .thenReturn(Optional.of(MenuItem.builder().build()));
        Mockito.when(billMenuItemRepository.findByBillOrderIdAndMenuItemId(10L, 4L)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            billOrderService.updateMenuItemQuantity(10L, new BillMenuItemRequest(4L, 5));
        });

        Assertions.assertEquals("Can't find the menu item with id 4 in this bill order", exception.getMessage());
    }

    @Test
    void testPayBillOrder_NoSuchElementException_IfBillOrderNotFound() {
        Mockito.when(billOrderRepository.findById(10L)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            billOrderService.payBillOrder(10L);
        });

        Assertions.assertEquals("There is no bill order with id 10", exception.getMessage());
    }

    @Test
    void testPayBillOrder_AlreadyExistException_IfBillOrderNotFound() {
        BillOrder billOrder = BillOrder.builder().id(10L).isPaid(true).createTime(new Date()).build();
        Mockito.when(billOrderRepository.findById(10L)).thenReturn(Optional.of(billOrder));

        Exception exception = Assertions.assertThrows(AlreadyExistException.class, () -> {
            billOrderService.payBillOrder(10L);
        });

        Assertions.assertEquals("This bill has been paid and exported, you can't update or export more one time",
                exception.getMessage());
    }
}