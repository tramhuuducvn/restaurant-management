package org.sdc.restaurant.view;

import lombok.NoArgsConstructor;
import org.sdc.restaurant.controller.BillOrderController;
import org.sdc.restaurant.controller.MenuItemController;
import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.util.Helper;

import java.util.Date;
import java.util.List;

/**
 * Responsible for Render UI of Bill Order
 */
@NoArgsConstructor
public class BillOrderView {
    private static final BillOrderController billOrderController = new BillOrderController();

    /**
     * pick menu item from menu
     *
     * @return Menu Item
     */
    public MenuItem pickMenuItem() {
        MenuItemController menuItemController = new MenuItemController(); // Listing menu item
        System.out.println("Pick one of the given list menu item below: ");
        menuItemController.listingMenu();

        int itemId;
        MenuItem menuItem;
        while (true) {
            itemId = Helper.getInputInteger("Enter your dish id: ",
                    "The entered value must be an integer, please try again:");
            menuItem = menuItemController.findById(itemId);
            if (menuItem == null) {
                System.out.println("Can't find dish id in menu, please try again!");
                continue;
            }
            break;
        }
        return menuItem;
    }

    /**
     * Choose Yes No
     * @return true if yes, false if no
     */
    public boolean enterYesNo(){
        while(true){
            System.out.println(
                    "Do you want to add more dish?\n" +
                            "Choose option:\n" +
                            "1. Yes\n" +
                            "2. No\n");
            int choose = Helper.getInputInteger("Enter your options: ",
                    "Entered value must be an integer, please try again!");

            if (choose == 1) {
                return true;
            }
            if (choose == 2) {
                return false;
            }
            System.out.println("Your input must be 1 or 2");
        }
    }

    /**
     * Create new bill order
     */
    public void create() {
        // Get bill id
        int billNumber = billOrderController.getMaxBillNumber() + 1;
        System.out.println("Add new bill #"+ billNumber + ": ");

        while (true) {
            // Pick menu item to new bill
            MenuItem menuItem = this.pickMenuItem();
            // enter quantities
            int quantities = Helper.getInputInteger("Enter quantities of dish:",
                    "The entered value must be an integer, please try again:");
            billOrderController.create(new BillOrder(billNumber, menuItem, quantities, new Date()));

            boolean choose = this.enterYesNo();
            if (!choose){
                return;
            }
        }
    }

    /**
     * Print list of item in menu
     */
    public void listingBill() {
        int max = billOrderController.getMaxBillNumber();
        if(max == 0){
            System.out.println("No any bills");
            return;
        }
        for (int i = 0; i < max; ++i) {
            this.printBillOrder(i + 1);
        }
    }

    /**
     * Add new dish to menu bill
     */
    public void addDishToMenu() {
        System.out.println("#Here is list of bill:");
        this.listingBill();
        int maxBillNumber = billOrderController.getMaxBillNumber();
        int billNumber;

        while (true) {
            billNumber = Helper.getInputInteger("Please enter bill number you want to add new dish: ",
                    "Invalid value, please try again: ");
            if (billNumber <= maxBillNumber && billNumber > 0) {
                break;
            }
            System.out.println("Can't found bill number, please try again: ");
        }

        System.out.println("Add some dish in the given menu to bill(" + billNumber + "): ");
        MenuItemController menuItemController = new MenuItemController();
        menuItemController.listingMenu();
        // Pick menu item to new bill
        int itemId;
        MenuItem menuItem;
        while (true) {
            itemId = Helper.getInputInteger("Enter your dish id: ",
                    "The entered value must be an integer, please try again:");
            menuItem = menuItemController.findById(itemId);
            if (menuItem == null) {
                System.out.println("Can't find dish id in menu, please try again!");
                continue;
            }
            break;
        }
        // Enter quantities
        int quantities = Helper.getInputInteger("Enter quantities of dish:",
                "The entered value must be an integer, please try again:");

        billOrderController.create(new BillOrder(billNumber, menuItem, quantities, new Date()));
    }

    public List<BillOrder> getAll() {
        return billOrderController.getAll();
    }

    /**
     * Update quantities of a dish in a bill
     */
    public void updateQuantitiesItem() {
        System.out.println("#Here is list of bill:");
        this.listingBill();
        int maxBillNumber = billOrderController.getMaxBillNumber();
        int billNumber;

        while (true) {
            billNumber = Helper.getInputInteger("Please enter bill number you want to add new dish: ",
                    "Invalid value, please try again: ");
            if (billNumber <= maxBillNumber && billNumber > 0) {
                break;
            }
            System.out.println("Can't found bill number, please try again: ");
        }

        int dishIndex = Helper.getInputInteger("Enter the dish index you want to update: ",
                "Invalid value, please try again");
        int newQuantities = Helper.getInputInteger("Please enter quantities you want to update: ",
                "Invalid value, please try again: ");

        if (!billOrderController.updateQuantitiesItem(billNumber, dishIndex, newQuantities)) {
            System.out.println("Update failed, maybe your enter index is out of the bound size, please try again!");
        }
    }

    /**
     * Remove dish from menu in bill
     */
    public void removeDish() {
        System.out.println("#Here is list of bill: ");
        this.listingBill();
        int maxBillNumber = billOrderController.getMaxBillNumber();
        int billNumber;

        while (true) {
            billNumber = Helper.getInputInteger("Please enter bill number you want to add new dish: ",
                    "Invalid value, please try again: ");
            if (billNumber <= maxBillNumber && billNumber > 0) {
                break;
            }
            System.out.println("Can't found bill number, please try again: ");
        }
        int dishIndex = Helper.getInputInteger("Enter the dish index you want to update: ",
                "Invalid value, please try again");
        if (!billOrderController.removeDish(billNumber, dishIndex)) {
            System.out.println(
                    "Update failed, may be your enter data doesn't correct or not match with the data, please try again!");
        }
    }

    /**
     * Print bill order
     * 
     * @param billNumber id of bill
     */
    public void printBillOrder(int billNumber) {
        billOrderController.printBillOrder(billNumber);
    }

    /**
     * Save data to file
     */
    public void save() {
        billOrderController.save();
    }
}
