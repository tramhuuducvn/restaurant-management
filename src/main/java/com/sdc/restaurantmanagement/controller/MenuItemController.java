package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.response.MenuItemResponse;
import com.sdc.restaurantmanagement.payload.request.MenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import com.sdc.restaurantmanagement.service.MenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.net.MalformedURLException;


/**
 * Receive requests from client related to menu item.
 * And response the relevant data according to client's request.
 */
@Tag(name = "Menu Item")
@RestController
@RequestMapping(value = "/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    /**
     * Get menu items
     *
     * @return body response contain menu items data
     */
    @Operation(description = "Listing menu items", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = MenuItem.class))), responseCode = "200")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping(value = "")
    public ResponseEntity<APIResponse> getAll() {
        MenuResponse menuItems = menuItemService.getAll();
        return new ResponseEntity<>(APIResponse.builder().message("Get list of dishes in menu successful").data(menuItems).build(), HttpStatus.OK);
    }

    /**
     * Get menu item by id
     *
     * @param id id of menu item
     * @return MenuItemDTO
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable Long id) {
        MenuItemResponse menuItemDTO = menuItemService.getById(id);
        return new ResponseEntity<>(APIResponse.builder().message("Menu item has been found").data(menuItemDTO).build(), HttpStatus.OK);
    }

    /**
     * Create new menu item
     *
     * @param request data need to create a menu item, under json data type.
     * @return APIResponse represent created successful
     */
    @PostMapping(value = "")
    public ResponseEntity<APIResponse> create(@RequestBody MenuItemRequest request) throws MalformedURLException {
        menuItemService.create(request);
        return new ResponseEntity<>(APIResponse.builder().message("Create menu item successful").build(), HttpStatus.CREATED);
    }

    /**
     * Update menu item by id
     *
     * @param id id of menu item
     * @return true if update success, this method will return no content status.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable Long id, @RequestBody MenuItemRequest request) throws MalformedURLException {
        menuItemService.update(id, request);
        return new ResponseEntity<>(APIResponse.builder().message("Menu item is updated").build(), HttpStatus.NO_CONTENT);
    }

    /**
     * Delete menu item by id
     *
     * @param id id of menu item
     * @return true if delete success, this method will return no content status.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Long id) {
        menuItemService.delete(id);
        return new ResponseEntity<>(APIResponse.builder().message("Menu item is deleted").build(), HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/search")
    public ResponseEntity<APIResponse> search(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String description,
            @RequestParam(required = false, defaultValue = "") String type
    ) {
        MenuResponse response = menuItemService.search(name, description, type);
        String message = response.getTotalMenuItem() == 0 ? "No item found" : response.getTotalMenuItem() + " item found";
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.builder().message(message).data(response).build());
    }
}