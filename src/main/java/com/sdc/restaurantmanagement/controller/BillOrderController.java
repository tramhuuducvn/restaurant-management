package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.BillOrderResponse;
import com.sdc.restaurantmanagement.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping(value="/bill-orders")
public class BillOrderController {
    @Autowired
    private BillOrderService billOrderService;

    /**
     * Get all bill orders
     *
     * @return list bill orders
     */
    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public APIResponse getAll() {
        return APIResponse.builder().message("List bill order").data(billOrderService.getAll()).build();
    }

    /**
     * Get bill order by id
     *
     * @param id id of bill order
     * @return detail information of bill order
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public APIResponse getById(@PathVariable Long id) {
        BillOrderResponse res = billOrderService.getById(id);
        return APIResponse.builder().message("Get bill order success").data(res).build();
    }

    /**
     * create new bill with given menu items
     *
     * @return bill order
     */
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public APIResponse create(@RequestBody(required = false) List<BillMenuItemRequest> items) {
        return APIResponse.builder().message("Create bill success").data(billOrderService.create(items)).build();
    }

    /**
     * Add Menu Item to bill
     *
     * @param id      id of bill order
     * @param request an object include id of menu item and number of that item want to add
     * @return success response message
     */
    @PutMapping(value = "/{id}/add")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public APIResponse addMenuItem(@PathVariable Long id, @RequestBody BillMenuItemRequest request) {
        billOrderService.addBillMenuItem(id, request);
        return APIResponse.builder().message("Add item to bill success").build();
    }

    /**
     * Update quantity of Menu Item to bill
     *
     * @param id      id of bill order
     * @param request an object include id of menu item and number of that item want to update
     * @return success response message
     */
    @PutMapping(value = "/{id}/update-quantity")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public APIResponse updateMenuItemQuantity(@PathVariable Long id, @RequestBody BillMenuItemRequest request) {
        billOrderService.updateMenuItemQuantity(id, request);
        return APIResponse.builder().message("Update successful").build();
    }

    @DeleteMapping(value = "/{billId}/{menuItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public APIResponse deleteBillMenuItem(@PathVariable Long billId, @PathVariable Long menuItemId) {
        billOrderService.removeBillMenuItem(billId, menuItemId);
        return APIResponse.builder().message("Delete successful").build();
    }

}