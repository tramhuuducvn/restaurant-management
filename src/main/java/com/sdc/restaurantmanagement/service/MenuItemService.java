package com.sdc.restaurantmanagement.service;

import com.sdc.restaurantmanagement.payload.dto.MenuItemDTO;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import org.springframework.stereotype.Service;


@Service
public interface MenuItemService {
    /**
     * Get menu data, list of dishes in menu
     * @return menu response data
     */
    MenuResponse getMenu();

    /**
     * Get menu item by id
     * @param id id of menu item
     * @return MenuItemDTO
     */
    MenuItemDTO getMenuItemById(Long id);
}