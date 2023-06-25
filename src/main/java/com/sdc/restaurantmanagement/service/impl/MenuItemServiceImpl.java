package com.sdc.restaurantmanagement.service.impl;

import com.sdc.restaurantmanagement.constant.Constant;
import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.payload.request.MenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.MenuItemResponse;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import com.sdc.restaurantmanagement.repository.MenuItemRepository;
import com.sdc.restaurantmanagement.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Handle logic for all features of menu items that mean:
 * <p>get all menu items</p>
 * <p>add/update/delete a menu items to menu</p>
 * <p>search items in menu</p>
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * Get all menu item in database
     *
     * @return list menu item
     */
    @Override
    public MenuResponse getAll() {
        List<MenuItemResponse> menuItems = menuItemRepository
                .findAllByIsDeleted(false)
                .stream()
                .map(MenuItemResponse::fromEntity)
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .totalMenuItem(menuItems.size())
                .items(menuItems)
                .build();
    }

    /**
     * Get menu item by id
     *
     * @param id id of menu item
     * @return MenuItemDTO
     */
    @Override
    public MenuItemResponse getById(Long id) {
        MenuItem menuItem = menuItemRepository.findByIdAndIsDeletedFalse(id).orElse(null);
        if (menuItem == null) {
            throw new NoSuchElementException("Menu item with id " + id + " doesn't exist!");
        }
        return MenuItemResponse.fromEntity(menuItem);
    }

    /**
     * Create new menu item
     *
     * @param request data request
     * @return true if create successful, otherwise return false
     */
    public boolean create(MenuItemRequest request) throws MalformedURLException {
        MenuItem menuItem = menuItemRepository.save(MenuItemRequest.toEntity(request));
        return menuItem.getName().equals(request.getName());
    }

    /**
     * Update menu item by id.
     *
     * @param id      id of menu item.
     * @param request new data update to current.
     */
    public void update(Long id, MenuItemRequest request) throws MalformedURLException, NoSuchElementException {
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if (item == null || item.isDeleted()) {
            throw new NoSuchElementException(Constant.NOT_FOUND_THE_MENU_ITEM_WITH_ID + id);
        }

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        item.setType(request.getType());
        menuItemRepository.save(item);
    }

    /**
     * Delete menu item by id
     *
     * @param id id of menu item
     */
    public void delete(Long id) {
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new NoSuchElementException(Constant.NOT_FOUND_THE_MENU_ITEM_WITH_ID + id);
        }
        item.setDeleted(true);
        menuItemRepository.save(item);
    }

    @Override
    public MenuResponse search(String name, String description, String type) {
        List<MenuItemResponse> result = menuItemRepository.search(name, description, type)
                .stream().map(MenuItemResponse::fromEntity).collect(Collectors.toList());

        return MenuResponse.builder().items(result).totalMenuItem(result.size()).build();
    }
}