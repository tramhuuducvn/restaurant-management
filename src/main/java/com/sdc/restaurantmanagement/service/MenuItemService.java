package com.sdc.restaurantmanagement.service;

import com.sdc.restaurantmanagement.payload.request.MenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.MenuItemResponse;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

/**
 * Handle logic for all features of menu items that mean:
 * + How to get all menu items
 * + How to add/update/delete a menu items to menu
 * + How to search items in menu
 */
@Service
public interface MenuItemService {
    /**
     * Get all menu item in database
     *
     * @return list menu item
     */
    MenuResponse getAll();

    /**
     * Get menu item by id
     *
     * @param id id of menu item
     * @return MenuItemDTO
     */
    MenuItemResponse getById(Long id);

    /**
     * Create new menu item
     *
     * @param request data request
     * @return true if create successful, otherwise return false
     */
    boolean create(MenuItemRequest request) throws MalformedURLException;

    /**
     * Update menu item by id.
     *
     * @param id      id of menu item.
     * @param request new data update to current.
     */
    void update(Long id, MenuItemRequest request) throws MalformedURLException;

    /**
     * Delete menu item by id
     *
     * @param id id of menu item
     */
    void delete(Long id);

    /**
     * Find menu item by name, by description or by type
     *
     * @param name        name of menu item
     * @param description description of menu item
     * @param type        additional information
     * @return list menu item
     */
    MenuResponse search(String name, String description, String type);

}