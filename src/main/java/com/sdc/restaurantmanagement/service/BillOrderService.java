package com.sdc.restaurantmanagement.service;

import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.BillOrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handle logic for all features of bill order that mean:
 * + How to get all bill orders
 * + How to create a bill order
 * + How to add a menu item to bill order
 * + How to remove a menu item from bill order
 * + How to update quantity for menu item in bill order
 */
@Service
public interface BillOrderService {
    /**
     * Get all bill orders
     * @return list bill orders
     */
    List<BillOrderResponse> getAll();

    /**
     * Get bill order by id
     * @param id id of bill order
     * @return bill order
     */
    BillOrderResponse getById(Long id);

    /**
     * Create bill order with the given menu items
     * @param items list menu items
     * @return created bill order data
     */
    BillOrderResponse create(List<BillMenuItemRequest> items);

    /**
     * Update item to bill id
     *
     * @param id      id of bill order
     * @param request request param contain menu_item_id and number want to add
     * @return
     */
    void addBillMenuItem(Long id, BillMenuItemRequest request) throws Exception;

    /**
     * Update quantity of item to bill id
     *
     * @param id      id of bill order
     * @param request request param contain menu_item_id and number want to update
     */
    void updateMenuItemQuantity(Long id, BillMenuItemRequest request) throws Exception;

    /**
     * Remove Menu Item from bill order
     * @param billId id of bill order
     * @param menuItemId id of menu item want to remove
     */
    void removeBillMenuItem(Long billId, Long menuItemId) throws Exception;

    /**
     * paid and export bill order
     * @param id id of bill order
     */
    void payBillOrder(Long id) throws Exception;
}