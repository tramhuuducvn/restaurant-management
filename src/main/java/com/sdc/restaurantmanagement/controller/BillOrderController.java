package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/bill-orders")
public class BillOrderController {
    @Autowired
    private BillOrderService billOrderService;


}