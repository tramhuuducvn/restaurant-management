package com.sdc.restaurantmanagement.service;

import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.BillOrderResponse;

import java.util.List;

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
     */
    void addBillMenuItem(Long id, BillMenuItemRequest request);

    /**
     * Update quantity of item to bill id
     *
     * @param id      id of bill order
     * @param request request param contain menu_item_id and number want to update
     */
    void updateMenuItemQuantity(Long id, BillMenuItemRequest request);

    /**
     * Remove Menu Item from bill order
     * @param billId id of bill order
     * @param menuItemId id of menu item want to remove
     */
    void removeBillMenuItem(Long billId, Long menuItemId);
}