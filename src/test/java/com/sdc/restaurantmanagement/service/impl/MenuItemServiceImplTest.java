package com.sdc.restaurantmanagement.service.impl;

import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.payload.request.MenuItemCreationRequest;
import com.sdc.restaurantmanagement.payload.request.MenuItemUpdateRequest;
import com.sdc.restaurantmanagement.payload.response.MenuResponse;
import com.sdc.restaurantmanagement.repository.MenuItemRepository;
import com.sdc.restaurantmanagement.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class MenuItemServiceImplTest {
    @TestConfiguration
    public static class MenuServiceImplTestConfiguration {
        @Bean
        public MenuItemService menuItemService(){
            return new MenuItemServiceImpl();
        }
    }

    @Autowired
    private MenuItemService service;

    @MockBean
    private MenuItemRepository repository;

    @BeforeEach
    public void setUp() throws Exception {
        // Mock for testGetMenu_ListMenu_IfGetSuccess
        MenuItem menuItem = new MenuItem(1L, "Pizza", "Pizza so good", 1.1, "", "some type", false);
        List<MenuItem> list = new ArrayList<>();
        list.add(menuItem);
        Mockito.when(repository.findAllByDeleted(false)).thenReturn(list);

        // Mock for testGetMenuItemById_Pass_IfNotFoundItem
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
    }

    @Test
    public void testMenuItemService_NULL_BeanLoader(){
        Assertions.assertNotNull(service);
    }
    
    @Test
    public void testGetMenu_ListMenu_IfGetSuccess(){
        MenuResponse result = service.getAll();
        Assertions.assertEquals(1, result.getTotalMenuItem());
    }

    @Test
    public void testGetMenuItemById_Pass_IfNotFoundItem(){
        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, ()->{
                service.getById(2L);
        }, "NoSuchElementException was expected");

        Assertions.assertEquals("Menu item doesn't exist!", exception.getMessage());
    }

    @Test
    public void testCreateItemById_Pass_IfCreateSuccess() throws MalformedURLException {
        MenuItemCreationRequest item = MenuItemCreationRequest.builder().name("Hello").imageUrl("https://abc.com").build();
        MenuItem menuItem = MenuItemCreationRequest.toEntity(item);
        Mockito.when(repository.save(menuItem)).thenReturn(menuItem);
        Assertions.assertTrue(service.create(item));
    }

    @Test
    public void testUpdateItemById_Pass_IfCreateSuccess() throws MalformedURLException {
        MenuItemUpdateRequest item = MenuItemUpdateRequest.builder().name("Hello").imageUrl("https://abc.com").build();
        MenuItem menuItem = MenuItemUpdateRequest.toEntity(item);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(menuItem));
        Mockito.when(repository.save(menuItem)).thenReturn(menuItem);
        Assertions.assertTrue(service.update(1L, item));
    }

    @Test
    public void testDeleteItemById_Pass_IfCreateSuccess() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new MenuItem()));
        Mockito.when(repository.save(null)).thenReturn(null);
        Assertions.assertTrue(service.delete(1L));
    }

    @Test
    public void testSearchMenuItem_Pass_FindItemSuccess() {
        Mockito.when(repository.search("a", "b", "c")).thenReturn(new ArrayList<>());
        MenuResponse res = service.search("a", "b", "c");
        Assertions.assertEquals(0, res.getTotalMenuItem());
    }
}