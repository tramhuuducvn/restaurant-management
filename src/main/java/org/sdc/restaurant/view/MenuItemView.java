package org.sdc.restaurant.view;

import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.controller.MenuItemController;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.util.Helper;

import lombok.NoArgsConstructor;

import java.util.EnumSet;
import java.util.List;

@NoArgsConstructor
public class MenuItemView {
    private static final MenuItemController menuItemController = new MenuItemController();

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
        List<MenuItem> list = this.getMenu();
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

    public void listingMenu() {
        menuItemController.listingMenu();
    }

    public List<MenuItem> getMenu() {
        return menuItemController.getMenu();
    }

    /**
     * search item
     */
    public void search() {
        System.out.println("Which type you want to search?");

        int i = 1;
        for (SearchMenuType info : EnumSet.allOf(SearchMenuType.class)) {
            System.out.println(i + ". " + info);
            i++;
        }

        int type = Helper.getInputInteger("Enter type you want to search:", "Invalid integer value, please try again!");
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
