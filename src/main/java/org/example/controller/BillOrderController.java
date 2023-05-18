package org.example.controller;

import org.example.entity.BillOrder;
import org.example.entity.MenuItem;
import org.example.service.BillOrderService;

import java.util.List;
import java.util.stream.Collectors;

public class BillOrderController {
    private BillOrderService billOrderService;

    public BillOrderController(){
        billOrderService = BillOrderService.getInstance();
    }

    // Create bill order
    public boolean createBillOrder(BillOrder billOrder) {
        return billOrderService.createBillOrder(billOrder);
    }

    // Retrieve bill order

    public List<BillOrder> getAllBillOrder(){
        return billOrderService.getAllBillOrder();
    }

    public List<BillOrder> getBillOrderByNumber(int billNumber){
        return billOrderService.getBillOrderByNumber(billNumber);
    }

    public List<MenuItem> getMenuItemByBillNumber(int billNumber){
        return billOrderService.getMenuItemByBillNumber(billNumber);
    }

    public boolean updateQuantitiesItem(int billNumber, String name, int quantity){
        return billOrderService.updateQuantitiesItem(billNumber, name, quantity);
    }

    public boolean removeBillItem(int billNumber, String name){
        return billOrderService.removeBillItem(billNumber, name);
    }

    public double calculateBillOrder(int billNumber) {
        return billOrderService.calculateBillOrder(billNumber);
    }

    public void printBillOrder(int billNumber) {
        billOrderService.printBillOrder(billNumber);
    }
}