package org.sdc.restaurant.service;

import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.repository.MenuItemRepository;
import org.sdc.restaurant.util.Helper;

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
        } catch (RuntimeException e) {
            throw new RuntimeException("Exception occurred in creating singleton object: " + e.getMessage());
        }
    }

    private MenuItemService() {
        menuItems = repo.readMenuItems();
    }

    public static MenuItemService getInstance() {
        return instance;
    }

    public int findIndexById(int id) {
        try {
            return IntStream.range(0, menuItems.size()).filter(item -> id == menuItems.get(item).getItemId()).findFirst().getAsInt();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    public int findIndexByName(String name) {
        try {
            return IntStream.range(0, menuItems.size()).filter(item -> name.equals(menuItems.get(item).getName())).findFirst().getAsInt();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    public MenuItem create(MenuItem menuItem) {
        for (MenuItem item : menuItems) {
            if (item.getName().equals(menuItem.getName())) {
                System.out.println("Can't create new dish because the name has existed!");
                return null;
            }
        }
        menuItem.setItemId(menuItems.size());
        menuItems.add(menuItem);
        return menuItem;
    }

    public MenuItem findById(int id) {
        return menuItems.stream().filter(item -> item.getItemId() == id).findFirst().orElse(null);
    }

    public MenuItem findByName(String name) {
        return menuItems.stream().filter(item -> item.getName().equals(name)).findFirst().orElse(null);
    }

    public void updateById(int id, MenuItem item) {
        int index = this.findIndexById(id);
        if (index < 0) {
            return;
        }
        item.setItemId(id);
        menuItems.set(index, item);
    }

    public void updateByName(String name, MenuItem item) {
        int existedName = this.findIndexByName(item.getName());
        if (existedName >= 0) {
            return;
        }
        int index = this.findIndexByName(name);
        if (index < 0) {
            return;
        }
        item.setItemId(menuItems.get(index).getItemId());
        menuItems.set(index, item);
    }

    public boolean deleteById(int id) {
        int index = this.findIndexById(id);
        if (index < 0) return false;
        MenuItem obj = menuItems.remove(index);
        return obj != null;
    }

    /**
     * @param name
     * @return
     */
    public boolean deleteByName(String name) {
        int index = this.findIndexByName(name);
        if (index < 0) return false;
        return menuItems.remove(index) == null;
    }

    public List<MenuItem> getAll() {
        return menuItems;
    }

    /**
     * Search menu item with given keyword by Name or by description,....
     *
     * @param keywords       the keywords that users want to search
     * @param searchMenuType user can search by Name, by Description,...
     * @return result of all item in menu that matched to the given keyword.
     */
    public List<MenuItem> search(String keywords, SearchMenuType searchMenuType) {
        switch (searchMenuType) {
            case ALL:
                return menuItems.stream().filter(item -> Helper.containKeyword(item.getContent(), keywords)).collect(Collectors.toList());
            case NAME:
                return menuItems.stream().filter(item -> Helper.containKeyword(item.getName(), keywords)).collect(Collectors.toList());
            case DESCRIPTION:
                return menuItems.stream().filter(item -> Helper.containKeyword(item.getDescription(), keywords)).collect(Collectors.toList());
            case TYPES:
                return menuItems.stream().filter(item -> Helper.containKeyword(item.getTypes(), keywords)).collect(Collectors.toList());
            default:
                return null;
        }
    }

    /**
     * export all item in menu to file csv
     */

    public void saveToFile() {
        repo.writeAll(menuItems);
    }

    /**
     * clear all item in menu list
     */
    public void clearAll() {
        menuItems.clear();
    }
}