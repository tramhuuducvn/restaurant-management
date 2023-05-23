package org.sdc.restaurant.controller;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.impl.BillOrderServiceImpl;
import org.sdc.restaurant.service.BillOrderService;
import org.sdc.restaurant.util.Helper;

import java.util.Date;
import java.util.List;

public class BillOrderController {
    private BillOrderService billOrderService;

    public BillOrderController() {
        billOrderService = BillOrderServiceImpl.getInstance();
    }

    /**
     * Get max bill number
     * 
     * @return max bill number
     */
    public int getMaxBillNumber() {
        return billOrderService.getMaxBillNumber();
    }

    /**
     * Create new bill order
     */
    public void create(BillOrder billOrder) {
        billOrderService.create(billOrder);
    }

    /**
     * Print list of item in menu
     */
    public void listingBill() {
        int max = billOrderService.getMaxBillNumber();
        for (int i = 0; i < max; ++i) {
            this.printBillOrder(i + 1);
        }
    }

    public List<BillOrder> getAll() {
        return billOrderService.getAll();
    }

    /**
     * Update quantities of a dish in a bill
     */
    public boolean updateQuantitiesItem(int billNumber, int dishIndex, int newQuantities) {
        return billOrderService.updateQuantitiesItem(billNumber, dishIndex, newQuantities);
    }

    /**
     * Remove dish from menu in bill
     */
    public boolean removeDish(int billNumber, int dishIndex) {
        return billOrderService.remove(billNumber, dishIndex);
    }

    /**
     * Print bill order
     * 
     * @param billNumber id of bill
     */
    public void printBillOrder(int billNumber) {
        billOrderService.printBillOrder(billNumber);
    }

    /**
     * Save data to file
     */
    public void save() {
        billOrderService.saveToFile();
    }
}