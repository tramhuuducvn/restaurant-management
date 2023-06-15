package com.sdc.restaurantmanagement.service.impl;

import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreateRequest;
import com.sdc.restaurantmanagement.payload.request.MenuItemUpdateRequest;
import com.sdc.restaurantmanagement.payload.response.MenuItemResponse;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import com.sdc.restaurantmanagement.repository.MenuItemRepository;
import com.sdc.restaurantmanagement.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * Get menu data, list of dishes in menu
     *
     * @return menu response data
     */
    @Override
    public MenuResponse getAll() {
        List<MenuItemResponse> results = menuItemRepository
                .findAllByDeleted(false)
                .stream()
                .map(MenuItemResponse::fromEntity)
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .totalMenuItem(results.size())
                .items(results)
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
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        MenuItemResponse itemDTO = MenuItemResponse.fromEntity(menuItem.orElse(null));
        if (itemDTO == null) {
            throw new NoSuchElementException("Menu item doesn't exist!");
        }
        return itemDTO;
    }

    /**
     * Create new menu item
     *
     * @param request data request
     * @return true if create successful, otherwise return false
     */
    public boolean create(MenuItemCreateRequest request) throws MalformedURLException {
        MenuItem menuItem = menuItemRepository.save(MenuItemCreateRequest.toEntity(request));
        return menuItem.getName().equals(request.getName());
    }

    /**
     * Update menu item by id.
     *
     * @param id      id of menu item.
     * @param request new data update to current.
     * @return true if update success.
     */
    public void update(Long id, MenuItemUpdateRequest request) throws MalformedURLException, NoSuchElementException {
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if (item == null || item.getDeleted()) {
            throw new NoSuchElementException("Can't find the menu item with id " + id);
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
     * @return true
     */
    public boolean delete(Long id) {
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setDeleted(true);
            menuItemRepository.save(item);
            return true;
        }
        return false;
    }

    @Override
    public MenuResponse search(String name, String description, String type) {
        List<MenuItemResponse> result = menuItemRepository.search(name, description, type)
                .stream().map(MenuItemResponse::fromEntity).collect(Collectors.toList());

        return MenuResponse.builder().items(result).totalMenuItem(result.size()).build();
    }
}