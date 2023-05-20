package org.sdc.restaurant.controller;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.BillOrderService;

import java.util.List;

public class BillOrderController {
    private BillOrderService billOrderService;

    public BillOrderController(){
        billOrderService = BillOrderService.getInstance();
    }

    public void create(BillOrder billOrder) {
        billOrderService.create(billOrder);
    }


    public List<BillOrder> getAll(){
        return billOrderService.getAll();
    }

    public List<BillOrder> getByBillNumber(int billNumber){
        return billOrderService.getByNumber(billNumber);
    }

    public List<MenuItem> getMenuItemByBillNumber(int billNumber){
        return billOrderService.getMenuItemByBillNumber(billNumber);
    }

    public boolean updateQuantitiesItem(int billNumber, String name, int quantity){
        return billOrderService.updateQuantitiesItem(billNumber, name, quantity);
    }

    public boolean remove(int billNumber, String name){
        return billOrderService.remove(billNumber, name);
    }

    public double calculateTotalPrice(int billNumber) {
        return billOrderService.calculateTotalPrice(billNumber);
    }

    public void printBillOrder(int billNumber) {
        billOrderService.printBillOrder(billNumber);
    }
}