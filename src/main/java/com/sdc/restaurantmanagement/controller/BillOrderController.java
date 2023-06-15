package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/bill-orders")
public class BillOrderController {
    @Autowired
    private BillOrderService billOrderService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok().body(null);
    }

    /**
     * create new bill with given menu items
     * @return bill order
     */
    @PostMapping(value = "")
    public ResponseEntity<Object> create(@RequestBody(required = false) List<MenuItem> items){

        return null;
    }
}