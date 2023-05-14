package org.example.service;

import org.example.entity.MenuItem;
import org.example.repository.BillOrderRepo;
import org.example.repository.MenuItemRepo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MenuItemService {
    private static final MenuItemService instance;
    private static final MenuItemRepo repo;
    private List<MenuItem> menuItems;

    static {
        try {
            repo = MenuItemRepo.getInstance();
            instance = new MenuItemService();
        }
        catch (RuntimeException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating singleton object: " + e.getMessage());
        }
    }

    private MenuItemService(){
        menuItems = repo.readMenuItems();
    }

    public static MenuItemService getInstance(){
        return instance;
    }

    //Tools
    public int findIndex(int id){
        return IntStream.range(0, menuItems.size()).filter(item -> id == menuItems.get(item).getItemId()).findFirst().getAsInt();
    }

    //CRUD
    public boolean createMenuItem(MenuItem menuItem){
       return menuItems.add(menuItem);
    }

    public MenuItem findMenuItemById(int id){
        return menuItems.stream().filter(item -> item.getItemId() == id).findFirst().orElse(null);
    }

    public void updateMenuItemById(int id, MenuItem item) {
        int index = this.findIndex(id);
        item.setItemId(id);
        menuItems.set(index, item);
    }

    public boolean deleteMenuItemById(int id){
        MenuItem obj = menuItems.remove(this.findIndex(id));
        return obj == null;
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public List<MenuItem> search(String keywords){
        return null;
    }

}