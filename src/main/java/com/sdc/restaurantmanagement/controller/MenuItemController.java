package com.sdc.restaurantmanagement.controller;

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
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;


/**
 * Receive requests from client related to menu item.
 * And response the relevant data according to client's request.
 */
@Tag(name = "Menu Item APIs", description = "REST APIs for Menu Item Collections")
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
    @Operation(summary = "Listing menu items", description = "Show list menu items that have not been deleted")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = APIResponse.class))), responseCode = "200"),
    })
    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        MenuResponse menuItems;
        if(page == null || size == null){
            menuItems = menuItemService.getAll();
        }
        else {
            menuItems = menuItemService.getByPage(page, size);
        }
        return APIResponse.builder().message("Get list of dishes in menu successful").data(menuItems).build();
    }

    /**
     * Get menu item by id
     *
     * @param id id of menu item
     * @return MenuItemDTO
     */
    @Operation(summary = "Get menu item by id", description = "Show information of menu item have the given id")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = APIResponse.class))), responseCode = "200"),
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse getById(@PathVariable Long id) {
        MenuItemResponse menuItemDTO = menuItemService.getById(id);
        return APIResponse.builder().message("Menu item has been found").data(menuItemDTO).build();
    }

    /**
     * Create new menu item
     *
     * @param request data need to create a menu item, under json data type.
     * @return APIResponse represent created successful
     */
    @Operation(summary = "Create menu item", description = "Create a menu item with the given information and add it to the database")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = APIResponse.class))), responseCode = "200"),
    })
    @PostMapping(value = "")
    public ResponseEntity<APIResponse> create(@RequestBody MenuItemRequest request) throws MalformedURLException {
        menuItemService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.builder().message("Create menu item successful").build());
    }

    /**
     * Update menu item by id
     *
     * @param id id of menu item
     * @return true if update success, this method will return no content status.
     */
    @Operation(summary = "Update menu item", description = "Find the menu items with the given id, if found item -> update it orElse return 404 and an message error")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = APIResponse.class))), responseCode = "200"),
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public APIResponse update(@PathVariable Long id, @RequestBody MenuItemRequest request) throws MalformedURLException {
        menuItemService.update(id, request);
        return APIResponse.builder().message("Menu item is updated").build();
    }

    /**
     * Delete menu item by id
     *
     * @param id id of menu item
     * @return true if delete success, this method will return no content status.
     */
    @Operation(summary = "Delete menu item", description = "Find the menu items with the given id, if found item -> soft delete it (just update the isDeleted value to True) orElse return 404 and an message error")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = APIResponse.class))), responseCode = "200"),
    })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public APIResponse delete(@PathVariable Long id) {
        menuItemService.delete(id);
        return APIResponse.builder().message("Menu item is deleted").build();
    }


    /**
     * Search menu items in the database
     *
     * @param name        name of menu item
     * @param description description of menu item
     * @param type        type that is addition information of menu item
     * @return list of menu item matched with the given field
     */
    @GetMapping(value = "/search")
    @Operation(summary = "Search menu items", description = "Listing the menu items matched with the given field")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = APIResponse.class))), responseCode = "200"),
    })
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