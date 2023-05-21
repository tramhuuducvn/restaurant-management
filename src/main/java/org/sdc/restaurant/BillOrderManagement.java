package org.sdc.restaurant;

import org.sdc.restaurant.controller.BillOrderController;
import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.service.MenuItemService;

import java.util.Date;

public class BillOrderManagement {
//    private static final BillOrderController controller = new BillOrderController();
//    private static final MenuItemService menuItemService = MenuItemService.getInstance();
//
//    private static void createBillOrders(){
//        controller.create(new BillOrder(1, menuItemService.findByName("Hawaiian Pizza"), 1, new Date()));
//        controller.create(new BillOrder(1, menuItemService.findByName("Chinese Pizza"), 1, new Date()));
//        controller.create(new BillOrder(1, menuItemService.findByName("Oolong tea"), 2, new Date()));
//        controller.create(new BillOrder(2, menuItemService.findByName("Chicken Tom Yum Pizza"), 1, new Date()));
//        controller.create(new BillOrder(2, menuItemService.findByName("Beer"), 3, new Date()));
//        controller.create(new BillOrder(2, menuItemService.findByName("Kimchi"), 1, new Date()));
//        controller.create(new BillOrder(3, menuItemService.findByName("Oolong tea"), 4, new Date()));
//        controller.create(new BillOrder(3, menuItemService.findByName("Beer"), 4, new Date()));
//    }
//
//    private static void retrieveBillOrders() {
//        controller.getAll().forEach(System.out::println);
//    }
//
//    private static void updateBillOrders(){
//        System.out.println("\nUpdate quantities of 'Chinese Pizza' in bill #1 = 3");
//        controller.updateQuantitiesItem(1, "Chinese Pizza", 3);
//        System.out.println("\nRemove beer from bill #3");
//        controller.remove(1, "Beer");
//        System.out.println("\n\nBill list after that: ");
//        retrieveBillOrders();
//    }
//
//    private static void printBillNo1(){
//        controller.printBillOrder(1);
//    }
//
//    public static void runDemo(){
//        createBillOrders();
//        retrieveBillOrders();
//        updateBillOrders();
//        printBillNo1();
//    }
}