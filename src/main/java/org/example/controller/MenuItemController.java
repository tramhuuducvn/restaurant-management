package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.entity.MenuItem;
import org.example.service.MenuItemService;

import java.util.List;

@AllArgsConstructor
public class MenuItemController {
    private static MenuItemService menuItemService;

    static {
        System.out.println("Hello MenuItemService");
        menuItemService = MenuItemService.getInstance();
    }
    //    Create Menu Item
    public boolean createMenuItem(MenuItem menuItem){
        return menuItemService.createMenuItem(menuItem);
    }
    //    Retrieve Menu Item
    public MenuItem findMenuItemById(int id){
        return menuItemService.findMenuItemById(id);
    }
    // Update Menu Item
    public void updateMenuItemById(int id, MenuItem menuItem) {
        menuItemService.updateMenuItemById(id, menuItem);
    }
    // Delete Menu Item
    public void deleteMenuItemById(int id){
        menuItemService.deleteMenuItemById(id);
    }

    // Listing Menu
    public List<MenuItem> getMenu(){
        return menuItemService.getMenuItems();
    }

    // Searching Item
    public List<MenuItem> search(String keywords){
        return menuItemService.search(keywords);
    }
}