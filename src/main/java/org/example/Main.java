package org.example;


import org.example.controller.BillOrderController;
import org.example.controller.MenuItemController;
import org.example.entity.MenuItem;
import org.example.repository.MenuItemRepo;
import org.example.service.BillOrderService;
import org.example.service.MenuItemService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        MenuItemController menuItemController = new MenuItemController();
        menuItemController.createMenuItem(new MenuItem(1, "Moodle", "long", "https://pitbullsocute", 5, "Chill"));
        MenuItemRepo repo = MenuItemRepo.getInstance();
        repo.writeAll(menuItemController.getMenu());
    }
}