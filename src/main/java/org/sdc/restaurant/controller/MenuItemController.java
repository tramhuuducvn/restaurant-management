package org.sdc.restaurant.controller;

import lombok.AllArgsConstructor;
import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.service.MenuItemService;

import java.util.List;

@AllArgsConstructor
public class MenuItemController {
    private static final MenuItemService menuItemService;

    static {
        menuItemService = MenuItemService.getInstance();
    }

    public boolean create(MenuItem menuItem) {
        return menuItemService.create(menuItem);
    }

    public MenuItem findMenuItemById(int id) {
        return menuItemService.findById(id);
    }

    public void updateMenuItemById(int id, MenuItem menuItem) {
        menuItemService.updateById(id, menuItem);
    }

    public void updateMenuItemByName(String name, MenuItem menuItem) {
        menuItemService.updateByName(name, menuItem);
    }

    public void deleteMenuItemById(int id) {
        menuItemService.deleteById(id);
    }

    public List<MenuItem> getMenu() {
        return menuItemService.getAll();
    }

    public List<MenuItem> search(String keywords, SearchMenuType searchMenuType) {
        return menuItemService.search(keywords, searchMenuType);
    }
}