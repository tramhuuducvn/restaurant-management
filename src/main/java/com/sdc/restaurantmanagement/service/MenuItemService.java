package com.sdc.restaurantmanagement.service;

import com.sdc.restaurantmanagement.payload.response.MenuItemResponse;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreateRequest;
import com.sdc.restaurantmanagement.payload.request.MenuItemUpdateRequest;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;


@Service
public interface MenuItemService {
    /**
     * Get menu data, list of dishes in menu
     * @return menu response data
     */
    MenuResponse getAll();

    /**
     * Get menu item by id
     * @param id id of menu item
     * @return MenuItemDTO
     */
    MenuItemResponse getById(Long id);

    /**
     * Create new menu item
     * @param request data request
     * @return true if create successful, otherwise return false
     */
    boolean create(MenuItemCreateRequest request) throws MalformedURLException;

    /**
     * Update menu item by id.
     * @param id id of menu item.
     * @param request new data update to current.
     * @return true if update success.
     */
    void update(Long id, MenuItemUpdateRequest request) throws MalformedURLException;

    /**
     * Delete menu item by id
     * @param id id of menu item
     * @return true
     */
    boolean delete(Long id);

    /**
     * Find menu item by name, by description or by type
     * @param name name of menu item
     * @param description description of menu item
     * @param type additional information
     * @return list menu item
     */
    MenuResponse search(String name, String description, String type);

}