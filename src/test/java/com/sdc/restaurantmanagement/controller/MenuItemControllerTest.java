package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.RestaurantManagementApplication;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreationRequest;
import com.sdc.restaurantmanagement.payload.request.MenuItemUpdateRequest;
import com.sdc.restaurantmanagement.util.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RestaurantManagementApplication.class)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@AutoConfigureMockMvc
class MenuItemControllerTest {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){

    }

    @Test
    @DisplayName("Test get menu items will pass when return list menu items success")
    public void testGetMenuItems_Pass_whenGetMenuItemsSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu-items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("Test get menu item by id will pass when menu item exist in database")
    public void testGetMenuItemById_Pass_IfMenuItemExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu-items/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test create menu item by id will pass when menu item exist in database")
    public void testCreateMenuItem_Pass_IfMenuItemExists() throws Exception {
        MenuItemCreationRequest item = MenuItemCreationRequest.builder()
                .name("Test name 1")
                .description("Test description 2")
                .price(10.0)
                .imageUrl("https://abc.com.vn")
                .type("")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/menu-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.toJSON(item)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test update menu item by id will pass when menu item exist in database")
    public void testUpdateMenuItem_Pass_IfMenuItemExists() throws Exception {
        MenuItemUpdateRequest item = MenuItemUpdateRequest.builder()
                .name("Test name 1")
                .description("Test description 2")
                .price(10.0)
                .imageUrl("https://abc.com.vn")
                .type("")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.put("/menu-items/2")
                        .content(Helper.toJSON(item))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test delete menu item with un exist id will pass when menu item not exist in database")
    public void testDeleteMenuItem_Pass_IfMenuItemExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/menu-items/1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test search menu items will pass when return list menu items success")
    public void testSearchMenuItem_Pass_whenGetMenuItemsSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu-items/search?name=a").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}