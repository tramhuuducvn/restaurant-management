package com.sdc.restaurantmanagement.service.impl;

import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.payload.dto.MenuItemDTO;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreationRequest;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import com.sdc.restaurantmanagement.repository.MenuItemRepository;
import com.sdc.restaurantmanagement.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public MenuResponse getMenu(){
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
    public MenuItemDTO getMenuItemById(Long id) {
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
    public boolean createMenuItem(MenuItemCreationRequest request) {
        MenuItem menuItem = menuItemRepository.save(MenuItemCreationRequest.toEntity(request));
        return menuItem.getName().equals(request.getName());
    }
}