package org.sdc.restaurant.service.impl;

import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.repository.MenuItemRepository;
import org.sdc.restaurant.service.MenuItemService;
import org.sdc.restaurant.util.Helper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Singleton Design Pattern
public class MenuItemServiceImpl implements MenuItemService {
    private static final MenuItemServiceImpl instance;
    private static final MenuItemRepository repo;
    private static List<MenuItem> menuItems;

    static {
        try {
            repo = MenuItemRepository.getInstance();
            instance = new MenuItemServiceImpl();
        } catch (RuntimeException e) {
            throw new RuntimeException("Exception occurred in creating singleton object: " + e.getMessage());
        }
    }

    private MenuItemServiceImpl() {
        menuItems = repo.readMenuItems();
    }

    /**
     * @return singleton instance
     */
    public static MenuItemServiceImpl getInstance() {
        return instance;
    }

    /**
     * Find item index by id
     * 
     * @param id id of dish in menu
     * @return index of dish in menu
     */
    public int findIndexById(int id) {
        try {
            return IntStream.range(0, menuItems.size()).filter(item -> id == menuItems.get(item).getItemId())
                    .findFirst().getAsInt();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    /**
     * Find index by name
     * 
     * @param name name of dish in menu
     * @return index of dish in menu
     */
    public int findIndexByName(String name) {
        try {
            return IntStream.range(0, menuItems.size()).filter(item -> name.equals(menuItems.get(item).getName()))
                    .findFirst().getAsInt();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    /**
     * Create new menu item
     * 
     * @param menuItem data of new dish
     * @return MenuItem
     */
    public MenuItem create(MenuItem menuItem) {
        for (MenuItem item : menuItems) {
            if (item.getName().equals(menuItem.getName()) && !item.isDeleted()) {
                System.out.println("Can't create new dish because the name has existed!");
                return null;
            }
        }
        menuItem.setItemId(menuItems.size());
        menuItems.add(menuItem);
        return menuItem;
    }

    /**
     * Find item by id
     * 
     * @param id id of dish in menu
     * @return if item was found return it, else return null
     */
    public MenuItem findById(int id) {
        return menuItems.stream().filter(item -> item.getItemId() == id && item.isDeleted() == false).findFirst()
                .orElse(null);
    }

    /**
     * Find by name
     * 
     * @param name name of dish in menu.
     * @return if item was found return it, else return null.
     */
    public MenuItem findByName(String name) {
        return menuItems.stream().filter(item -> item.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Update by id.
     * 
     * @param id   id of dish in menu need to update.
     * @param item new data of dish will be updated.
     */
    public void updateById(int id, MenuItem item) {
        int index = this.findIndexById(id);
        if (index < 0) {
            return;
        }
        item.setItemId(id);
        menuItems.set(index, item);
    }

    /**
     * Update by name.
     * 
     * @param name name of dish in menu need to update.
     * @param item new data of dish will be updated.
     */
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

    /**
     * Delete by id.
     * 
     * @param id index each item in menu, start from 1.
     * @return if delete success return true, else return false.
     */
    public boolean deleteById(int id) {
        int index = this.findIndexById(id);
        if (index < 0)
            return false;
        MenuItem obj = menuItems.get(index);
        obj.setDeleted(true);
        menuItems.set(index, obj);
        return obj != null;
    }

    /**
     * Delete by name.
     * 
     * @param name name of dish in menu.
     * @return true if delete successful else return false.
     */
    public boolean deleteByName(String name) {
        int index = this.findIndexByName(name);
        if (index < 0)
            return false;
        return menuItems.remove(index) == null;
    }

    /**
     * Get all item
     * 
     * @return list of menu item
     */
    public List<MenuItem> getAll() {
        return menuItems;
    }

    public List<MenuItem> listingMenu() {
        return menuItems.stream().filter(menuItem -> !menuItem.isDeleted()).collect(Collectors.toList());
    }

    /**
     * Search menu item with given keyword by all, by name, by description, by type.
     * 
     * @param keywords       the keywords that users want to search
     * @param searchMenuType user can search by Name, by Description,...
     * @return result of all item in menu that matched to the given keyword.
     */
    public List<MenuItem> search(String keywords, SearchMenuType searchMenuType) {
        switch (searchMenuType) {
            case ALL:
                return menuItems.stream()
                        .filter(item -> !item.isDeleted() && Helper.containKeyword(item.getContent(), keywords))
                        .collect(Collectors.toList());
            case NAME:
                return menuItems.stream()
                        .filter(item -> !item.isDeleted() && Helper.containKeyword(item.getName(), keywords))
                        .collect(Collectors.toList());
            case DESCRIPTION:
                return menuItems.stream()
                        .filter(item -> !item.isDeleted() && Helper.containKeyword(item.getDescription(), keywords))
                        .collect(Collectors.toList());
            case TYPES:
                return menuItems.stream()
                        .filter(item -> !item.isDeleted() && Helper.containKeyword(item.getTypes(), keywords))
                        .collect(Collectors.toList());
            default:
                return null;
        }
    }

    /**
     * Export all item in menu to file csv.
     */
    @Override
    public void saveToFile() {
        repo.writeAll(menuItems);
    }

    /**
     * Clear all item in menu list.
     */
    @Override
    public void clearAll() {

    }
}