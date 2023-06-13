package com.sdc.restaurantmanagement.service.impl;

import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.payload.dto.MenuItemDTO;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreationRequest;
import com.sdc.restaurantmanagement.payload.request.MenuItemUpdateRequest;
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
     * @return menu response data
     */
    @Override
    public MenuResponse getAll(){
        List<MenuItemDTO> results = menuItemRepository
                .findAllByDeleted(false)
                .stream()
                .map(MenuItemDTO::fromEntity)
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .totalMenuItem(results.size())
                .items(results)
                .build();
    }

    /**
     * Get menu item by id
     * @param id id of menu item
     * @return MenuItemDTO
     */
    @Override
    public MenuItemDTO getById(Long id) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        MenuItemDTO itemDTO = MenuItemDTO.fromEntity(menuItem.orElse(null));
        if(itemDTO == null){
            throw new NoSuchElementException("Menu item doesn't exist!");
        }
        return itemDTO;
    }

    /**
     * Create new menu item
     * @param request data request
     * @return true if create successful, otherwise return false
     */
    public boolean create(MenuItemCreationRequest request) throws MalformedURLException {
        MenuItem menuItem = menuItemRepository.save(MenuItemCreationRequest.toEntity(request));
        return menuItem.getName().equals(request.getName());
    }

    /**
     * Update menu item by id.
     * @param id id of menu item.
     * @param request new data update to current.
     * @return true if update success.
     */
    public boolean update(Long id, MenuItemUpdateRequest request) throws MalformedURLException {
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if(item != null){
            if(item.getDeleted()){
                throw new NoSuchElementException("Can't find the menu item with id " + id);
            }
            item.setName(request.getName());
            item.setDescription(request.getDescription());
            item.setPrice(request.getPrice());
            item.setImageUrl(request.getImageUrl());
            item.setType(request.getType());
            menuItemRepository.save(item);
            return true;
        }
        return false;
    }

    /**
     * Delete menu item by id
     * @param id id of menu item
     * @return true
     */
    public boolean delete(Long id){
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if(item != null){
            item.setDeleted(true);
            menuItemRepository.save(item);
            return true;
        }
        return false;
    }
}