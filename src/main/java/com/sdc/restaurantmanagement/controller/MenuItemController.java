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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.net.MalformedURLException;


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
    public APIResponse getAll(){
        MenuResponse results = menuItemService.getAll();
        return APIResponse.builder(HttpStatus.OK).message("Get list of dishes in menu successful").data(results).build();
    }

    /**
     * Get menu item by id
     * @param id id of menu item
     * @return MenuItemDTO
     */
    @GetMapping(value = "/{id}")
    public APIResponse getById(@PathVariable Long id){
        MenuItemDTO menuItemDTO = menuItemService.getById(id);
        return APIResponse.builder(HttpStatus.OK).message("Menu item has been found").data(menuItemDTO).build();
    }

    /**
     * Create new menu item
     * @param request data need to create a menu item, under json data type.
     * @return APIResponse represent created successful
     */
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse create(@RequestBody MenuItemCreationRequest request) throws MalformedURLException {
        menuItemService.create(request);
        return APIResponse.builder(HttpStatus.CREATED).message("Create menu item successful").build();
    }

    /**
     * Update menu item by id
     * @param id id of menu item
     * @return true if update success, this method will return no content status.
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public APIResponse update(@PathVariable Long id, @RequestBody MenuItemUpdateRequest request) throws MalformedURLException {
        menuItemService.update(id, request);
        return APIResponse.builder(HttpStatus.NO_CONTENT).message("Menu item is updated").build();
    }

    /**
     * Delete menu item by id
     * @param id id of menu item
     * @return true if delete success, this method will return no content status.
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public APIResponse delete(@PathVariable Long id) {
        menuItemService.delete(id);
        return APIResponse.builder(HttpStatus.NO_CONTENT).message("Menu item is deleted").build();
    }


    @GetMapping(value = "/search/{number}")
    public ResponseEntity<String> search(@PathVariable int number){
        if(number == 1){
            return new ResponseEntity<>(
                    "Year of birth cannot be in the future",
                    HttpStatus.BAD_REQUEST);
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "foo");

            return new ResponseEntity<>(
                    "Custom header set", headers, HttpStatus.OK);
        }
//        return APIResponse.builder(HttpStatus.OK).build();
    }
}