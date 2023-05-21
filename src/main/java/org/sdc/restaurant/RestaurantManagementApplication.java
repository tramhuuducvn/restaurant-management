package org.sdc.restaurant;

import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.controller.BillOrderController;
import org.sdc.restaurant.controller.MenuItemController;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RestaurantManagementApplication {

    private static void runMenuManagement() {
        Scanner scanner = new Scanner(System.in);
        MenuItemController menuItemController = new MenuItemController();

        while (true) {
            System.out.println(
                    "------- \t Menu Management \t ------- \n" +
                    "Choose option:\n" +
                    "1. Create menu item\n" +
                    "2. Update menu item\n" +
                    "3. Delete menu item\n" +
                    "4. Listing menu\n" +
                    "5. Search menu by keywords\n" +
                    "6. Save to CSV\n" +
                    "7. Exit\n"
            );

            try {
                int choose = scanner.nextInt();
                if (choose == 1) {
                    menuItemController.create();
                }
                if (choose == 2) {
                    menuItemController.updateMenuItemById();
                }
                if (choose == 3) {
                    menuItemController.deleteMenuItemById();
                }
                if (choose == 4) {
                    menuItemController.listingMenu();
                }
                if (choose == 5) {
                    menuItemController.search();
                }
                if (choose == 6) {
                    menuItemController.save();
                    System.out.println("Exported");
                }
                if (choose == 7) {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }
        }
    }

    private static void runBillOrderManagement() {
        Scanner scanner = new Scanner(System.in);
        BillOrderController billOrderController = new BillOrderController();

        while (true) {
            System.out.println(
                    "-------\tBill Order Management\t------- \n" +
                    "Choose option:\n" +
                    "1. Create bill\n" +
                    "2. Listing bill\n" +
                    "3. Add dish to bill\n" +
                    "4. Remove dish to bill\n" +
                    "5. Update quantities of dish\n" +
                    "6. Save to CSV\n" +
                    "7. Exit\n"
            );

            try {
                int choose = scanner.nextInt();
                if (choose == 1) {
                    billOrderController.create();
                }
                if (choose == 2) {
                    billOrderController.listingBill();
                }
                if (choose == 3) {
                    billOrderController.addDishToMenu();
                }
                if (choose == 4) {
                    billOrderController.removeDish();
                }
                if (choose == 5) {
                    billOrderController.updateQuantitiesItem();
                }
                if (choose == 6) {
                    billOrderController.save();
                }
                if (choose == 7) {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "-------\tWelcome to Restaurant Management Application\t------- \n" +
                    "Choose option:\n" +
                    "1. Menu Management\n" +
                    "2. Bill Order Management\n" +
                    "3. Exit\n"
            );

            try {
                int choose = scanner.nextInt();

                if (choose == 1) {
                    runMenuManagement();
                }
                if (choose == 2) {
                    runBillOrderManagement();
                }
                if (choose == 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        run();
    }

}