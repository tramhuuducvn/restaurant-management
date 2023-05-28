package org.sdc.restaurant.controller;

import lombok.AllArgsConstructor;
import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.MenuItemService;
import org.sdc.restaurant.service.impl.MenuItemServiceImpl;

import java.util.List;

/**
 * This class represent for Presentation Layer which is responsible for receiving requests from users
 * and interact with the Service Layer to handle or cook data to respond back to the user.
 * Every method focus on manage menu item.
 */
@AllArgsConstructor
public class MenuItemController {
    private static final MenuItemService menuItemService;
    static {
        menuItemService = MenuItemServiceImpl.getInstance();
    }

    /**
     * Create new dish in menu
     * 
     * @param menuItem data of new item
     */
    public void create(MenuItem menuItem) {
        menuItemService.create(menuItem);
    }

    /**
     * Find menu item by id
     * 
     * @param id find menu item by id
     * @return Menu item
     */
    public MenuItem findById(int id) {
        return menuItemService.findById(id);
    }

    /**
     * Update Menu item by id
     * @param id       id of item
     * @param menuItem data menu item
     */
    public void updateById(int id, MenuItem menuItem) {
        menuItemService.updateById(id, menuItem);
    }

    /**
     * Delete item by Id
     * 
     * @param id id of item
     * @return true if delete successful
     */
    public boolean deleteById(int id) {
        return menuItemService.deleteById(id);
    }

    /**
     * Print menu list
     */
    public void listingMenu() {
        System.out.println("List item in menu: ");
        List<MenuItem> list = menuItemService.listingMenu();
        if (list.size() < 1) {
            System.out.println("Empty!");
        }
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i).toString());
        }
    }

    /**
     * Get list item in menu
     * 
     * @return list item in menu
     */
    public List<MenuItem> getMenu() {
        return menuItemService.getAll();
    }

    /**
     * Search item in menu by special types with the given keywords
     * @param keywords       keywords want to search
     * @param searchMenuType search by type, by all, by name, by description,...
     * @return result of menu item matched with the given keyword
     */
    public List<MenuItem> search(String keywords, SearchMenuType searchMenuType) {
        return menuItemService.search(keywords, searchMenuType);
    }

    /**
     * Save all data to file csv
     */
    public void save() {
        menuItemService.saveToFile();
    }

}