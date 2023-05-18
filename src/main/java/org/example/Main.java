package org.example;


import org.example.controller.MenuItemController;
import org.example.entity.MenuItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static MenuItemController menuItemController = new MenuItemController();


    public static void main(String[] args) {
        System.out.println("\n MenuManagement Run Demo");
        MenuManagement.runDemo();
        System.out.println("\n BillOrderManagement Run Demo");
        BillOrderManagement.runDemo();
    }
}