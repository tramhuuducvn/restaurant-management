package org.sdc.restaurant.controller;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.BillOrderService;
import org.sdc.restaurant.util.Helper;

import java.util.Date;
import java.util.List;

public class BillOrderController {
    private BillOrderService billOrderService;

    public BillOrderController() {
        billOrderService = BillOrderService.getInstance();
    }

    private int getMaxBillNumber() {
        List<BillOrder> list = billOrderService.getAll();
        if (list.size() < 1) {
            return 1;
        }
        BillOrder billOrder = list.stream().max((a, b) -> a.getBillNumber() - b.getBillNumber()).get();
        return billOrder.getBillNumber();
    }

    public void create() {
        // Get bill number
        System.out.println("Add new bill: ");
        int billNumber = this.getMaxBillNumber() + 1;
        // Listing menu item
        System.out.println("Add some dish in the given menu to bill(" + billNumber + "): ");
        MenuItemController menuItemController = new MenuItemController();
        menuItemController.listingMenu();
        // pick menu item to new bill
        int itemId;
        MenuItem menuItem;
        while (true) {
            itemId = Helper.getInputInteger("Enter your dish id: ", "The entered value must be an integer, please try again:") - 1;
            menuItem = menuItemController.findMenuItemById(itemId);
            if (menuItem == null) {
                System.out.println("Can't find dish id in menu, please try again!");
                continue;
            }
            break;
        }
        // enter quantities
        int quantities = Helper.getInputInteger("Enter quantities of dish", "The entered value must be an integer, please try again:");

        billOrderService.create(new BillOrder(billNumber, menuItem, quantities, new Date()));
    }

    public void listingBill(){
        int max = this.getMaxBillNumber();
        for(int i = 0; i < max; ++i){
            this.printBillOrder(i + 1);
        }
    }

    public void addDishToMenu() {
        System.out.println("#Here is list of bill:");
        this.listingBill();
        int billNumber = Helper.getInputInteger ("Please enter bill number you want to add new dish: ", "Invalid value, please try again: ");
        System.out.println("Add some dish in the given menu to bill(" + billNumber + "): ");
        MenuItemController menuItemController = new MenuItemController();
        menuItemController.listingMenu();
        // pick menu item to new bill
        int itemId;
        MenuItem menuItem;
        while (true) {
            itemId = Helper.getInputInteger("Enter your dish id: ", "The entered value must be an integer, please try again:") - 1;
            menuItem = menuItemController.findMenuItemById(itemId);
            if (menuItem == null) {
                System.out.println("Can't find dish id in menu, please try again!");
                continue;
            }
            break;
        }
        // enter quantities
        int quantities = Helper.getInputInteger("Enter quantities of dish", "The entered value must be an integer, please try again:");
        billOrderService.create(new BillOrder(billNumber, menuItem, quantities, new Date()));
    }

    public List<BillOrder> getAll() {
        return billOrderService.getAll();
    }

    public List<BillOrder> getByBillNumber(int billNumber) {
        return billOrderService.getByNumber(billNumber);
    }

    public List<MenuItem> getMenuItemByBillNumber(int billNumber) {
        return billOrderService.getMenuItemByBillNumber(billNumber);
    }

    public void updateQuantitiesItem() {
        System.out.println("#Here is list of bill:");
        this.listingBill();
        int billNumber = Helper.getInputInteger ("Please enter bill number you want to update: ", "Invalid value, please try again: ") - 1;
        String name = Helper.getInputString("Enter the name of dish you want to update: ", "Invalid value, please try again");
        int newQuantities = Helper.getInputInteger ("Please enter quantities you want to update: ", "Invalid value, please try again: ");
        billOrderService.updateQuantitiesItem(billNumber, name, newQuantities);
    }

    public void removeDish() {
        System.out.println("#Here is list of bill: ");
        this.listingBill();
        int billNumber = Helper.getInputInteger ("Please enter bill number you want to update: ", "Invalid value, please try again: ") - 1;
        String name = Helper.getInputString("Enter the name of dish you want to remove: ", "Invalid value, please try again");
        billOrderService.remove(billNumber, name);
    }

    public double calculateTotalPrice(int billNumber) {
        return billOrderService.calculateTotalPrice(billNumber);
    }

    public void printBillOrder(int billNumber) {
        billOrderService.printBillOrder(billNumber);
    }

    public void save(){
        billOrderService.saveToFile();
    }
}