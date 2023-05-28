package org.sdc.restaurant.controller;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.service.impl.BillOrderServiceImpl;
import org.sdc.restaurant.service.BillOrderService;

import java.util.List;

/**
 * This class represent for Presentation Layer which is responsible for receiving requests from users
 * and interact with the Service Layer to handle or cook data to respond back to the user.
 * Every method focus on manage bill order
 */
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
     * Get list all bill order
     * @return  list bill order
     */
    public List<BillOrder> getAll() {
        return billOrderService.getAll();
    }

    /**
     * Update quantities of a dish in a bill
     * @param billId id of bill order
     * @param dishIndex index of menu item in that bill
     * @param newQuantities quantities need to update
     * @return if update successful return true, else return false
     */
    public boolean updateQuantitiesItem(int billId, int dishIndex, int newQuantities) {
        return billOrderService.updateQuantitiesItem(billId, dishIndex, newQuantities);
    }

    /**
     * Remove dish from menu in bill
     * @param billId id of bill order
     * @param dishIndex index of menu item in that bill
     * @return if delete successful return true, else return false
     */
    public boolean removeDish(int billId, int dishIndex) {
        return billOrderService.remove(billId, dishIndex);
    }

    /**
     * Print bill order
     * @param billId id of bill
     */
    public void printBillOrder(int billId) {
        billOrderService.printBillOrder(billId);
    }

    /**
     * Save data to file
     */
    public void save() {
        billOrderService.saveToFile();
    }
}