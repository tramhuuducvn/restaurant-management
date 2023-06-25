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
import com.sdc.restaurantmanagement.service.BillOrderService;
import com.sdc.restaurantmanagement.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Handle logic for all features of bill order that mean:
 * <p>get all bill orders</p>
 * <p>create a bill order</p>
 * <p>add a menu item to bill order</p>
 * <p>remove a menu item from bill order</p>
 * <p>update quantity for menu item in bill order</p>
 */
@Service
public class BillOrderServiceImpl implements BillOrderService {

    @Autowired
    BillOrderRepository billOrderRepository;

    @Autowired
    MenuItemService menuItemService;

    @Autowired
    BillMenuItemRepository billMenuItemRepository;

    private void checkIsPaid(Long id) throws Exception {
        BillOrder billOrder = billOrderRepository.findById(id).orElse(null);
        if(billOrder == null) {
            throw new NoSuchElementException(Constant.NO_BILL_ORDER_WITH_ID + id);
        }

        if(billOrder.isPaid()) {
            throw new AlreadyExistException("This bill has been paid and exported, you can't update or export more one time");
        }
    }

    /**
     * Get all bill orders
     *
     * @return list bill orders
     */
    @Override
    public List<BillOrderResponse> getAll() {
        List<BillOrder> billOrders = billOrderRepository.findAll();
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
            throw new NoSuchElementException(Constant.NOT_FOUND_THE_BILL_ORDER_WITH_ID + id);
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
                MenuItem menuItem = menuItemService.getById(item.getMenuItemId()).toEntity();
                if (menuItem == null || menuItem.isDeleted()) {
                    throw new NoSuchElementException("Can't add the menu item with id " + item.getMenuItemId() + " to the bill because It maybe not exist or be deleted");
                }
                list.add(BillMenuItem.builder().billOrder(billOrder).menuItem(menuItem).number(item.getNumber()).build());
            }
        }

        billOrder.setItems(list);
        billOrderRepository.saveAndFlush(billOrder);
        return BillOrderResponse.fromEntity(billOrder);
    }

    /**
     * Update item to bill id
     *
     * @param id      id of bill order
     * @param request request param contain menu_item_id and number want to add
     * @return
     */
    @Override
    public void addBillMenuItem(Long id, BillMenuItemRequest request) throws Exception {
        this.checkIsPaid(id);
        BillOrder billOrder = billOrderRepository.findById(id).orElse(null);

        MenuItem menuItem = menuItemService.getById(request.getMenuItemId()).toEntity();
        if (menuItem == null || menuItem.isDeleted()) {
            throw new NoSuchElementException("The menu item with " + id + " not found or be deleted");
        }

        BillMenuItem billMenuItem = billMenuItemRepository.findByBillOrderIdAndMenuItemId(id, request.getMenuItemId()).orElse(null);


        if (billMenuItem != null) {
            if(billMenuItem.isDeleted()){
                billMenuItem.setDeleted(false);
                billMenuItem.setNumber(request.getNumber());
                billMenuItemRepository.save(billMenuItem);
                return;
            }
            else {
                throw new AlreadyExistException("This menu item already exists in your bill, you can update it's quantities");
            }
        }

        billMenuItemRepository.save(BillMenuItem.builder().number(request.getNumber()).menuItem(menuItem).billOrder(billOrder).build());
    }


    /**
     * Update quantity of item to bill id
     *
     * @param id      id of bill order
     * @param request request param contain menu_item_id and number want to update
     */
    @Override
    public void updateMenuItemQuantity(Long id, BillMenuItemRequest request) throws Exception {
        this.checkIsPaid(id);
        BillOrder billOrder = billOrderRepository.findById(id).orElse(null);
        if (billOrder == null) {
            throw new NoSuchElementException(Constant.NO_BILL_ORDER_WITH_ID + id);
        }

        MenuItem menuItem = menuItemService.getById(request.getMenuItemId()).toEntity();
        if (menuItem == null || menuItem.isDeleted()) {
            throw new NoSuchElementException("The menu item with " + id + " not found or be deleted");
        }

        BillMenuItem billMenuItem = billMenuItemRepository.findByBillOrderIdAndMenuItemId(id, request.getMenuItemId()).orElse(null);

        if (billMenuItem == null || billMenuItem.isDeleted()) {
            throw new NoSuchElementException(Constant.NOT_FOUND_THE_MENU_ITEM_WITH_ID + request.getMenuItemId() + " in this bill order");
        }
        billMenuItem.setNumber(request.getNumber());
        billMenuItemRepository.save(billMenuItem);
    }

    /**
     * Remove Menu Item from bill order
     * @param billId id of bill order
     * @param menuItemId id of menu item want to remove
     */
    @Override
    public void removeBillMenuItem(Long billId, Long menuItemId) throws Exception {
        billMenuItemRepository.softDeleteByBillOrderIdAndMenuItemId(billId, menuItemId);
    }

    /**
     * paid and export bill order
     * @param id id of bill order
     */
    @Override
    public void payBillOrder(Long id) throws Exception {
        this.checkIsPaid(id);
        billOrderRepository.payBillOrder(id);
    }
}