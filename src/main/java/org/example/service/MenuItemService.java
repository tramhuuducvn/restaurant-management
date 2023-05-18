package org.example.service;

import org.example.constant.SearchMenuType;
import org.example.entity.MenuItem;
import org.example.repository.MenuItemRepository;
import org.example.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Singleton Design
public class MenuItemService {
    private static final MenuItemService instance;
    private static final MenuItemRepository repo;
    private static List<MenuItem> menuItems;

    static {
        try {
            repo = MenuItemRepository.getInstance();
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

    public int findIndexById(int id){
        try {
            return IntStream.range(0, menuItems.size()).filter(item -> id == menuItems.get(item).getItemId()).findFirst().getAsInt();
        }
        catch (NoSuchElementException e){
//            throw new NoSuchElementException(e.getMessage());
            return -1;
        }
    }

    public int findIndexByName(String name){
        try {
            return IntStream.range(0, menuItems.size()).filter(item -> name.equals(menuItems.get(item).getName())).findFirst().getAsInt();
        }
        catch (NoSuchElementException e){
            return -1;
        }
    }

    public boolean createMenuItem(MenuItem menuItem){
        for (MenuItem item: menuItems) {
            if(item.getName().equals(menuItem.getName())){
                return false;
            }
        }
        menuItem.setItemId(menuItems.size());
        menuItems.add(menuItem);
        this.saveToFile();
        return true;
    }

    public MenuItem findMenuItemById(int id){
        return menuItems.stream().filter(item -> item.getItemId() == id).findFirst().orElse(null);
    }

    public MenuItem findMenuItemByName(String name){
        return menuItems.stream().filter(item -> item.getName().equals(name)).findFirst().orElse(null);
    }

    public void updateMenuItemById(int id, MenuItem item) {
        int index = this.findIndexById(id);
        if(index < 0) {
            return;
        }
        item.setItemId(id);
        menuItems.set(index, item);
        this.saveToFile();
    }

    public void updateMenuItemByName(String name, MenuItem item) {
        int existedName = this.findIndexByName(item.getName());
        if(existedName >= 0){
            return;
        }
        int index = this.findIndexByName(name);
        if(index < 0) {
            return;
        }
        item.setItemId(menuItems.get(index).getItemId());
        menuItems.set(index, item);
        this.saveToFile();
    }

    public boolean deleteMenuItemById(int id){
        int index = this.findIndexById(id);
        if(index < 0) return false;
        MenuItem obj = menuItems.remove(index);
        this.saveToFile();
        return obj == null;
    }

    public boolean deleteMenuItemByName(String name){
        int index = this.findIndexByName(name);
        if(index < 0) return false;
        MenuItem obj = menuItems.remove(index);
        this.saveToFile();
        return obj == null;
    }
    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public List<MenuItem> search(String keywords, SearchMenuType searchMenuType){
        switch (searchMenuType){
            case ALL:
                return menuItems.stream().filter(item -> Helper.containKeyword(item.getContent(), keywords)).collect(Collectors.toList());
            default:
                return null;
        }
    }
    public void saveToFile(){
        repo.writeAll(menuItems);
    }

    public void clearAll(){
        menuItems.clear();
    }
}