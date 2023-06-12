package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.dto.MenuItemDTO;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreationRequest;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import com.sdc.restaurantmanagement.service.MenuItemService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Receive requests from client related to menu item.
 * And response the relevant data according to client's request.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;
    /**
     * Get menu items
     * @return body response contain menu items data
     */
    @GetMapping(value = "")
    public APIResponse getMenuItems(){
        MenuResponse results = menuItemService.getMenu();
        return  APIResponse.builder(HttpStatus.OK).message("Get list of dishes in menu successful").data(results).build();
    }

    /**
     * Get menu item by id
     * @param id id of menu item
     * @return MenuItemDTO
     */
    @GetMapping(value = "/{id}")
    public APIResponse getMenuItemById(@PathVariable Long id){
        MenuItemDTO menuItemDTO = menuItemService.getMenuItemById(id);
        return APIResponse.builder(HttpStatus.OK).message("Menu item has been found").data(menuItemDTO).build();
    }

    /**
     * Create new menu item
     * @param request data need to create a menu item, under json data type.
     * @return APIResponse represent created successful
     */
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse createMenuItem(@RequestBody MenuItemCreationRequest request){
        menuItemService.createMenuItem(request);
        return APIResponse.builder(HttpStatus.CREATED).message("Create menu item successful").build();
    }
}