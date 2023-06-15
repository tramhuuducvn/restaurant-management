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
    void updateBillMenuItem(Long id, BillMenuItemRequest request);


}