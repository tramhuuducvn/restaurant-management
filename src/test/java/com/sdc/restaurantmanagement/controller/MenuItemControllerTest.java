package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.RestaurantManagementApplication;
import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.request.MenuItemRequest;
import com.sdc.restaurantmanagement.service.MenuItemService;
import com.sdc.restaurantmanagement.util.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.MalformedURLException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RestaurantManagementApplication.class)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@AutoConfigureMockMvc
class MenuItemControllerTest {
        @InjectMocks
        MenuItemController menuItemController;
        @Mock
        MenuItemService menuItemService;

        @Autowired
        MockMvc mockMvc;

        @BeforeEach
        public void setUp() {

        }

        @Test
        @DisplayName("Test get menu items will pass when return list menu items success")
        public void testGetMenuItems_Pass_whenGetMenuItemsSuccess() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/menu-items").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test get menu item by id will return 200 when the menu item exists in database")
        public void testGetMenuItemById_200_IfMenuItemExists() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/menu-items/1").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test get menu item by id will return 404 when the menu item doesn't exist in database")
        public void testGetMenuItemById_404_IfMenuItemNotExists() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/menu-items/1000").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        public void testCreateMenuItem_202_IfMenuItemExists() throws MalformedURLException {
                MockHttpServletRequest request = new MockHttpServletRequest();
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

                MenuItemRequest menuItemRequest = MenuItemRequest.builder().description("Hello").name("Hello Name")
                                .build();
                Mockito.when(menuItemService.create(Mockito.any(MenuItemRequest.class))).thenReturn(true);

                ResponseEntity<APIResponse> response = menuItemController.create(menuItemRequest);
                Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

        @Test
        @DisplayName("Test create menu item will return 400 when the imageUrl is invalid")
        public void testCreateMenuItem_400_IfImgUrlInvalid() throws Exception {
                MenuItemRequest item = MenuItemRequest.builder()
                                .name("Test name 1")
                                .description("Test description 2")
                                .price(10.0)
                                .imageUrl("https://abc .com.vn")
                                .type("")
                                .build();

                mockMvc.perform(MockMvcRequestBuilders.post("/menu-items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Helper.toJSON(item)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test update menu item by id will return 202 when menu item exists in database and all data is valid")
        public void testUpdateMenuItem_202_IfMenuItemExists() throws Exception {
                MenuItemRequest item = MenuItemRequest.builder()
                                .name("Test name 17")
                                .description("Test update menu item id 17")
                                .price(10.0)
                                .imageUrl("https://abc.com.vn")
                                .type("")
                                .build();

                mockMvc.perform(MockMvcRequestBuilders.put("/menu-items/17")
                                .content(Helper.toJSON(item))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isAccepted())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test update menu item by id will return 400 when menu url is invalid")
        public void testUpdateMenuItem_400_IfImgUrlInvalid() throws Exception {
                MenuItemRequest item = MenuItemRequest.builder()
                                .name("Test name 17")
                                .description("Test update menu item id 17")
                                .price(10.0)
                                .imageUrl("https:// abc.com.vn")
                                .type("")
                                .build();

                mockMvc.perform(MockMvcRequestBuilders.put("/menu-items/17")
                                .content(Helper.toJSON(item))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test update menu item by id will return 404 when the menu item is not exist in database")
        public void testUpdateMenuItem_404_IfMenuItemNotExists() throws Exception {
                MenuItemRequest item = MenuItemRequest.builder()
                                .name("Test name 17")
                                .description("Test update menu item id 17")
                                .price(10.0)
                                .imageUrl("https://abc.com.vn")
                                .type("")
                                .build();

                mockMvc.perform(MockMvcRequestBuilders.put("/menu-items/1007")
                                .content(Helper.toJSON(item))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test delete menu item with un exist id will pass when menu item not exist in database")
        public void testDeleteMenuItem_20_IfMenuItemExists() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/menu-items/2")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNoContent())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test delete menu item with un exist id will pass when menu item not exist in database")
        public void testDeleteMenuItem_Pass_IfMenuItemNotExists() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/menu-items/1000")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Test search menu items will pass when return list menu items success")
        public void testSearchMenuItem_Pass_whenGetMenuItemsSuccess() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/menu-items/search?name=a")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }
}