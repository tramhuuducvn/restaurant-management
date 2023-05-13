package org.example.service;

import org.example.repository.BillOrderRepo;
import org.example.repository.MenuItemRepo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MenuItemService {
    private static final MenuItemService instance;
    private static final MenuItemRepo repo;
    static {
        try {
            instance = new MenuItemService();
            repo = MenuItemRepo.getInstance();
        }
        catch (RuntimeException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating singleton object");
        }
    }

    private MenuItemService(){

    }

    public static MenuItemService getInstance(){
        return instance;
    }
}