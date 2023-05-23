package org.sdc.restaurant.view;

import org.sdc.restaurant.util.Helper;

import java.util.Scanner;

public class ApplicationConsoleView {
    /**
     * Render menu management.
     */
    private static void renderMenuManagement() {
        MenuItemView menuItemView = new MenuItemView();

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
                            "7. Exit\n");

            int choose = Helper.getInputInteger("Enter your options: ", "Invalid value, try again!");

            if (choose == 1) {
                menuItemView.create();
                continue;
            }
            if (choose == 2) {
                menuItemView.updateMenuItemById();
                continue;
            }
            if (choose == 3) {
                menuItemView.deleteMenuItemById();
                continue;
            }
            if (choose == 4) {
                menuItemView.listingMenu();
                continue;
            }
            if (choose == 5) {
                menuItemView.search();
                continue;
            }
            if (choose == 6) {
                menuItemView.save();
                System.out.println("Exported");
                continue;
            }
            if (choose == 7) {
                break;
            }
            System.out.println("Your number doesn't not match any options");
        }
    }

    /**
     * Render bill order management.
     */
    private static void renderBillOrderManagement() {
        BillOrderView billOrderView = new BillOrderView();

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
                            "7. Exit\n");

            int choose = Helper.getInputInteger("Enter your options: ", "Invalid value, try again!");

            if (choose == 1) {
                billOrderView.create();
                continue;
            }
            if (choose == 2) {
                billOrderView.listingBill();
                continue;
            }
            if (choose == 3) {
                billOrderView.addDishToMenu();
                continue;
            }
            if (choose == 4) {
                billOrderView.removeDish();
                continue;
            }
            if (choose == 5) {
                billOrderView.updateQuantitiesItem();
                continue;
            }
            if (choose == 6) {
                billOrderView.save();
                continue;
            }
            if (choose == 7) {
                break;
            }
            System.out.println("Your number doesn't not match any options");
        }
    }

    /**
     * Render restaurant application.
     */
    public static void render() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "------- \t Welcome to Restaurant Management Application \t ------- \n" +
                            "Choose option:\n" +
                            "1. Menu Management\n" +
                            "2. Bill Order Management\n" +
                            "3. Exit\n");

            int choose = Helper.getInputInteger("Enter your options: ", "Invalid value, try again!");

            if (choose == 1) {
                renderMenuManagement();
                continue;
            }
            if (choose == 2) {
                renderBillOrderManagement();
                continue;
            }
            if (choose == 3) {
                break;
            }
            System.out.println("Your number doesn't not match any options!");
        }
        scanner.close();
    }
}
