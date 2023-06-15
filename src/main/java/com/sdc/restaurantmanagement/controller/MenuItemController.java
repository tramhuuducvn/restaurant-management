package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.dto.MenuItemDTO;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreationRequest;
import com.sdc.restaurantmanagement.payload.request.MenuItemUpdateRequest;
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

import java.net.MalformedURLException;


/**
 * Receive requests from client related to menu item.
 * And response the relevant data according to client's request.
 */
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
    public ResponseEntity<APIResponse> getAll(){
        MenuResponse results = menuItemService.getAll();
        return new ResponseEntity<>(APIResponse.builder().message("Get list of dishes in menu successful").data(results).build(), HttpStatus.OK) ;
    }

    /**
     * Get menu item by id
     * @param id id of menu item
     * @return MenuItemDTO
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable Long id){
        MenuItemDTO menuItemDTO = menuItemService.getById(id);
        return new ResponseEntity<>(APIResponse.builder().message("Menu item has been found").data(menuItemDTO).build(), HttpStatus.OK);
    }

    /**
     * Create new menu item
     * @param request data need to create a menu item, under json data type.
     * @return APIResponse represent created successful
     */
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<APIResponse> create(@RequestBody MenuItemCreationRequest request) throws MalformedURLException {
        menuItemService.create(request);
        return new ResponseEntity<>(APIResponse.builder().message("Create menu item successful").build(), HttpStatus.CREATED);
    }

    /**
     * Update menu item by id
     * @param id id of menu item
     * @return true if update success, this method will return no content status.
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<APIResponse> update(@PathVariable Long id, @RequestBody MenuItemUpdateRequest request) throws MalformedURLException {
        menuItemService.update(id, request);
        return new ResponseEntity<>(APIResponse.builder().message("Menu item is updated").build(), HttpStatus.NO_CONTENT);
    }

    /**
     * Delete menu item by id
     * @param id id of menu item
     * @return true if delete success, this method will return no content status.
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<APIResponse> delete(@PathVariable Long id) {
        menuItemService.delete(id);
        return new ResponseEntity<>(APIResponse.builder().message("Menu item is deleted").build(), HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/search")
    public ResponseEntity<APIResponse> search(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String description,
            @RequestParam(required = false, defaultValue = "") String type
    ){
        MenuResponse response = menuItemService.search(name, description, type);
        String message = response.getTotalMenuItem() == 0 ? "No item found" : response.getTotalMenuItem() + " item found";
         return ResponseEntity.status(HttpStatus.OK).body(APIResponse.builder().message(message).data(response).build());
    }
}