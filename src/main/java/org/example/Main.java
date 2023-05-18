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
        MenuManagement.runDemo();
    }
}