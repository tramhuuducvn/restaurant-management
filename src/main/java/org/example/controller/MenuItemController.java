package org.example.controller;

import org.example.entity.MenuItem;
import org.example.service.MenuItemService;

import java.util.List;

public class MenuItemController {
    private static MenuItemService menuItemService;

    static {
        System.out.println("Hello MenuItemService");
        menuItemService = MenuItemService.getInstance();
    }
    //    Create Menu Item
    public void createMenuItem(MenuItem menuItem){

    }
    //    Retrieve Menu Item
    public MenuItem findMenuItemById(int id){
        return null;
    }
    // Update Menu Item
    public void updateMenuItemById(int id) {

    }
    // Delete Menu Item
    public void deleteMenuItemById(int id){

    }

    // Listing Menu
    public List<MenuItem> getMenu(){
        return null;
    }

    // Searching Item
    public List<MenuItem> search(String keywords){
        return null;
    }
}