package org.example;

import org.example.controller.BillOrderController;
import org.example.entity.BillOrder;
import org.example.entity.MenuItem;
import org.example.service.MenuItemService;

import java.util.Date;

public class BillOrderManagement {
    private static BillOrderController controller = new BillOrderController();
    private static MenuItemService menuItemService = MenuItemService.getInstance();

    private static void createBillOrders(){
        controller.createBillOrder(new BillOrder(1, menuItemService.findMenuItemByName("Hawaiian Pizza"), 1, new Date()));
        controller.createBillOrder(new BillOrder(1, menuItemService.findMenuItemByName("Chinese Pizza"), 1, new Date()));
        controller.createBillOrder(new BillOrder(1, menuItemService.findMenuItemByName("Oolong tea"), 2, new Date()));
        controller.createBillOrder(new BillOrder(2, menuItemService.findMenuItemByName("Chicken Tom Yum Pizza"), 1, new Date()));
        controller.createBillOrder(new BillOrder(2, menuItemService.findMenuItemByName("Beer"), 3, new Date()));
        controller.createBillOrder(new BillOrder(2, menuItemService.findMenuItemByName("Kimchi"), 1, new Date()));
        controller.createBillOrder(new BillOrder(3, menuItemService.findMenuItemByName("Oolong tea"), 4, new Date()));
        controller.createBillOrder(new BillOrder(3, menuItemService.findMenuItemByName("Beer"), 4, new Date()));

    }

    private static void retrieveBillOrders() {
        controller.getAllBillOrder().forEach(System.out::println);
    }

    private static void updateBillOrders(){
        System.out.println("\nUpdate quantities of 'Chinese Pizza' in bill #1 = 3");
        controller.updateQuantitiesItem(1, "Chinese Pizza", 3);
        System.out.println("\nRemove beer from bill #3");
        controller.removeBillItem(1, "Beer");
        System.out.println("\n\nBill list after that: ");
        retrieveBillOrders();
    }

    private static void printBillNo1(){
        controller.printBillOrder(1);
    }

    public static void runDemo(){
        createBillOrders();
        retrieveBillOrders();
        updateBillOrders();
        printBillNo1();
    }
}