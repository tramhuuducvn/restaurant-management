package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.BillOrderResponse;
import com.sdc.restaurantmanagement.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/bill-orders")
public class BillOrderController {
    @Autowired
    private BillOrderService billOrderService;

    /**
     * Get all bill orders
     * @return list bill orders
     */
    @GetMapping(value = "")
    public ResponseEntity<APIResponse> getAll() {
        return ResponseEntity.ok().body(APIResponse.builder().message("List bill order").data(billOrderService.getAll()).build());
    }

    /**
     * Get bill order by id
     * @param id id of bill order
     * @return detail information of bill order
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable Long id) {
        BillOrderResponse res = billOrderService.getById(id);
        return ResponseEntity.ok().body(APIResponse.builder().message("Get bill order success").data(res).build());
    }

    /**
     * create new bill with given menu items
     * @return bill order
     */
    @PostMapping(value = "")
    public ResponseEntity<APIResponse> create(@RequestBody(required = false) List<BillMenuItemRequest> items){
        APIResponse res = APIResponse.builder().message("Create bill success").data(billOrderService.create(items)).build(); ;
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping(value = {"/{id}/add", "/{id}/update-quantity"})
    public ResponseEntity<APIResponse> updateBillMenuItem(@PathVariable Long id, @RequestBody BillMenuItemRequest request){
        billOrderService.updateBillMenuItem(id, request);
        APIResponse res = APIResponse.builder().build();
        return ResponseEntity.ok().body(res);
    }
}