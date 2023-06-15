package com.sdc.restaurantmanagement.service.impl;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import com.sdc.restaurantmanagement.entity.BillOrder;
import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.BillOrderResponse;
import com.sdc.restaurantmanagement.repository.BillMenuItemRepository;
import com.sdc.restaurantmanagement.repository.BillOrderRepository;
import com.sdc.restaurantmanagement.repository.MenuItemRepository;
import com.sdc.restaurantmanagement.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BillOrderServiceImpl implements BillOrderService {

    @Autowired
    BillOrderRepository billOrderRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    BillMenuItemRepository billMenuItemRepository;

    /**
     * Get all bill orders
     *
     * @return list bill orders
     */
    @Override
    public List<BillOrderResponse> getAll() {
        List<BillOrder> billOrders = billOrderRepository.findAll();
        if (billOrders == null) {
            billOrders = new ArrayList<>();
        }
        return billOrders.stream().map(BillOrderResponse::fromEntity).collect(Collectors.toList());
    }

    /**
     * Get bill order by id
     *
     * @param id id of bill order
     * @return bill order
     */
    @Override
    public BillOrderResponse getById(Long id) {
        BillOrder billOrder = billOrderRepository.findById(id).orElse(null);
        if (billOrder == null) {
            throw new NoSuchElementException("Can't find the bill order with " + id);
        }

        return BillOrderResponse.fromEntity(billOrder);
    }

    /**
     * Create bill order with the given menu items
     *
     * @param items list menu items
     * @return created bill order data
     */
    @Override
    public BillOrderResponse create(List<BillMenuItemRequest> items) {
        BillOrder billOrder = billOrderRepository.save(BillOrder.builder().createTime(new Date()).build());

        List<BillMenuItem> list = new ArrayList<>();
        if (!items.isEmpty()) {
            for (BillMenuItemRequest item : items) {
                MenuItem menuItem = menuItemRepository.findById(item.getMenuItemId()).orElse(null);
                if (menuItem == null || menuItem.isDeleted()) {
                    throw new NoSuchElementException("Can't add the menu item with id " + item.getMenuItemId() + " to the bill because It maybe not exist or be deleted");
                }
                list.add(BillMenuItem.builder().billOrder(billOrder).menuItem(menuItem).number(item.getNumber()).build());
            }
        }

        billOrder.setItems(list);
        billOrderRepository.save(billOrder);

        return BillOrderResponse.fromEntity(billOrder);
    }

    /**
     * Update item to bill id
     *
     * @param id      id of bill order
     * @param request request param contain menu_item_id and number want to add
     */
    @Override
    public void updateBillMenuItem(Long id, BillMenuItemRequest request) {
        BillOrder billOrder = billOrderRepository.findById(id).orElse(null);
        if(billOrder == null) {
            throw new NoSuchElementException("There are no bill with id " + id);
        }

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId()).orElse(null);
        if(menuItem == null || menuItem.isDeleted()) {
            throw new NoSuchElementException("The menu item with " + id + " not found or be deleted");
        }


        BillMenuItem billMenuItem = billMenuItemRepository.findByBillOrderIdAndMenuItemId(id, request.getMenuItemId()).orElse(null);

        if(billMenuItem != null) {
            request.setNumber((request.getNumber() + billMenuItem.getNumber()));
            billMenuItem.setNumber(request.getNumber() + billMenuItem.getNumber());
            billMenuItemRepository.save(billMenuItem);
            return;
        }

        billMenuItemRepository.save(BillMenuItem.builder().menuItem(menuItem).billOrder(billOrder).build());
    }

//    /**
//     * Update quantity of item to bill id
//     *
//     * @param id      id of bill order
//     * @param request request param contain menu_item_id and number want to update
//     */
////    @Override
//    public void updateMenuItemQuantity(Long id, BillMenuItemRequest request) {
//        BillOrder billOrder = billOrderRepository.findById(id).orElse(null);
//        if(billOrder == null) {
//            throw new NoSuchElementException("There are no bill with id " + id);
//        }
//
//        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId()).orElse(null);
//        if(menuItem == null || menuItem.isDeleted()) {
//            throw new NoSuchElementException("The menu item with " + id + " not found or be deleted");
//        }
//
//
//        BillMenuItem billMenuItem = billMenuItemRepository.findByBillOrderIdAndMenuItemId(id, request.getMenuItemId()).orElse(null);
//
//        if(billMenuItem != null) {
//            request.setNumber((request.getNumber() + billMenuItem.getNumber()));
//            updateMenuItemQuantity(id, request);
//        }
//
//        billMenuItemRepository.save(BillMenuItem.builder().menuItem(menuItem).billOrder(billOrder).build());
//    }
}