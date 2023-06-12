package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.dto.MenuItemDTO;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import com.sdc.restaurantmanagement.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
class MenuItemControllerTest {
    @TestConfiguration
    public static class MenuItemControllerTestConfiguration {
        @Bean
        public MenuItemController menuItemController() {
            return new MenuItemController();
        }
    }

    @Autowired
    private MenuItemController menuItemController;

    @MockBean
    MenuItemService menuItemService;

    @BeforeEach
    public void setUp(){
        MenuItemDTO menuItemDTO = new MenuItemDTO(1L, "Pizza", "Pizza so good", 1.1, "", "some type");
        List<MenuItemDTO> list = new ArrayList<>();
        list.add(menuItemDTO);
        MenuResponse menuResponse = MenuResponse.builder().totalMenuItem(list.size()).items(list).build();
        Mockito.when(menuItemService.getMenu()).thenReturn(menuResponse);
    }

    @Test
    @DisplayName("Test getMenuItems pass when return list menu items success")
    public void testGetMenuItems_Pass_whenGetMenuItemsSuccess() {
        this.setUp();
        APIResponse apiResponse = menuItemController.getMenuItems();
        System.out.println(apiResponse);
        Assertions.assertEquals("Pizza", ((MenuResponse)apiResponse.getData()).getItems().get(0).getName());
    }
}