package org.sdc.restaurant.view;

import lombok.NoArgsConstructor;
import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.controller.MenuItemController;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.util.Helper;

import java.util.EnumSet;
import java.util.List;

/**
 * Responsible for Render UI of Menu Item
 */
@NoArgsConstructor
public class MenuItemView {
    private static final MenuItemController menuItemController = new MenuItemController();

    /**
     * create menu item
     */
    public void create() {
        String name = Helper.getInputString("Enter name:", "Invalid value, please try again:");
        String description = Helper.getInputString("Enter description:", "Invalid value, please try again:");
        String image = Helper.getInputString("Enter image url: ", "Invalid value, please try again:");
        double price = Helper.getInputDouble("Enter price: ", "Invalid value, please try again:");
        String type = Helper.getInputString("Enter type: ", "Invalid value, please try again:");

        menuItemController.create(new MenuItem(name, description, image, price, type));
    }

    /**
     * Render update menu item view
     */
    public void updateMenuItemById() {
        this.listingMenu();
        System.out.println("Which item you want to update?");
        int id;
        while (true) {
            id = Helper.getInputInteger("Enter the id number in the given list above: ",
                    "The entered value must be an integer, please try again!");
            if (menuItemController.findById(id) == null) {
                System.out.println("Item not found, please try again: ");
                continue;
            }
            break;
        }

        String name = Helper.getInputString("Enter name:", "Invalid value, please try again:");
        String description = Helper.getInputString("Enter description:", "Invalid value, please try again:");
        String image = Helper.getInputString("Enter image url: ", "Invalid value, please try again:");
        double price = Helper.getInputDouble("Enter price: ", "Invalid value, please try again:");
        String type = Helper.getInputString("Enter type: ", "Invalid value, please try again:");
        menuItemController.updateById(id, new MenuItem(name, description, image, price, type));
    }

    /**
     * Render delete menu item view
     */
    public void deleteMenuItemById() {
        List<MenuItem> list = menuItemController.getMenu();
        if (list.size() < 1) {
            System.out.println("You can't delete anything because the menu is empty!");
            return;
        }
        this.listingMenu();
        System.out.println("Which item you want to delete?");

        int id;
        while (true) {
            id = Helper.getInputInteger("Enter the id number in the given list above: ",
                    "The entered value must be an integer, please try again!");
            if (menuItemController.findById(id) == null) {
                System.out.println("Item not found, please try again: ");
                continue;
            }
            break;
        }

        if (menuItemController.deleteById(id)) {
            System.out.println("Delete successful");
        }
    }

    /**
     * print menu item
     */
    public void listingMenu() {
        menuItemController.listingMenu();
    }

    /**
     * Render ui request user enter option from 1 to 4
     * @return valid option 1,2,3 or 4
     */
    public int enterSearchType() {
        while (true) {
            System.out.println("Which type you want to search?");

            int i = 1;
            for (SearchMenuType info : EnumSet.allOf(SearchMenuType.class)) {
                System.out.println(i + ". " + info);
                i++;
            }
            int type = Helper.getInputInteger("Enter type you want to search:", "Invalid integer value, please try again!");

            if (type < 1 || type > 4) {
                System.out.println("The value must be 1, 2, 3 or 4, please try again!");
                continue;
            }
            return type;
        }

    }

    /**
     * search item
     */
    public void search() {
        int type = this.enterSearchType();
        String keywords = Helper.getInputString("Enter your keywords: ", "Invalid value, please try again: ");
        List<MenuItem> result;
        switch (type) {
            case 1:
                result = menuItemController.search(keywords, SearchMenuType.ALL);
                break;
            case 2:
                result = menuItemController.search(keywords, SearchMenuType.NAME);
                break;
            case 3:
                result = menuItemController.search(keywords, SearchMenuType.DESCRIPTION);
                break;
            case 4:
                result = menuItemController.search(keywords, SearchMenuType.TYPES);
                break;
            default:
                System.out.println("Your entered type doesn't not match with the given types above!");
                return;
        }
        if (result == null || result.size() < 1) {
            System.out.println("No item found!");
        } else {
            System.out.println("Result search: ");
            result.forEach(System.out::println);
        }
    }

    /**
     * Save all data to file csv
     */
    public void save() {
        menuItemController.save();
    }
}
