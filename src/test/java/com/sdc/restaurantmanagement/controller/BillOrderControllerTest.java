package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.RestaurantManagementApplication;
import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.repository.BillMenuItemRepository;
import com.sdc.restaurantmanagement.repository.BillOrderRepository;
import com.sdc.restaurantmanagement.service.BillOrderService;
import com.sdc.restaurantmanagement.util.Helper;
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

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RestaurantManagementApplication.class)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@AutoConfigureMockMvc
class BillOrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private BillOrderService billOrderService;

    @Autowired
    private BillOrderRepository billOrderRepository;

    @Autowired
    BillMenuItemRepository billMenuItemRepository;

    @Test
    void testGetAllBillOrder_OK_IfGetBillOrderSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill-orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBillOrderById_OK_GetBillOrderByIdSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill-orders/7").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBillOrderById_NotFound_GetBillOrderByIdSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill-orders/7000").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateBillOrder_CREATED_IfCreateSuccess() throws Exception {
        List<BillMenuItemRequest> items = new ArrayList<>();
        items.add(new BillMenuItemRequest(1L, 10));
        items.add(new BillMenuItemRequest(5L, 10));
        mockMvc.perform(MockMvcRequestBuilders.post("/bill-orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.toJSON(items)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateBillOrder_404_IfMenuItemNotFound() throws Exception {
        List<BillMenuItemRequest> items = new ArrayList<>();
        items.add(new BillMenuItemRequest(1L, 10));
        items.add(new BillMenuItemRequest(2L, 10));
        mockMvc.perform(MockMvcRequestBuilders.post("/bill-orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.toJSON(items)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddMenuItem_ACCEPTED_IfAddSuccess() throws Exception {
        billOrderService.removeBillMenuItem(33L, 15L);
        BillMenuItemRequest item = new BillMenuItemRequest(15L, 10);

        mockMvc.perform(MockMvcRequestBuilders.put("/bill-orders/33/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.toJSON(item)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddMenuItem_CONFLICT_IfMenuItemIsAlreadyExist() throws Exception {
        billMenuItemRepository.unSoftDeleteByBillOrderIdAndMenuItemId(7L, 1L);
        BillMenuItemRequest items = new BillMenuItemRequest(1L, 10);


        mockMvc.perform(MockMvcRequestBuilders.put("/bill-orders/7/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.toJSON(items)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateMenuItemQuantity_ACCEPTED_IfUpdateSuccess() throws Exception {
        BillMenuItemRequest item = new BillMenuItemRequest(15L, 10);

        mockMvc.perform(MockMvcRequestBuilders.put("/bill-orders/7/update-quantity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.toJSON(item)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateMenuItemQuantity_NotFound_IfUpdateSuccess() throws Exception {
        BillMenuItemRequest item = new BillMenuItemRequest(1050L, 10);

        mockMvc.perform(MockMvcRequestBuilders.put("/bill-orders/44/update-quantity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.toJSON(item)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteBillMenuItem_NoContent_IfNothing() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/bill-orders/7/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testPaidBillOrder_NotFound_IfBillOrderNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/bill-orders/7009/pay-bill-order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testPaidBillOrder_Accepted_IfBillOrderFound() throws Exception {
        billOrderRepository.unPayBillOrder(37L);
        mockMvc.perform(MockMvcRequestBuilders.put("/bill-orders/37/pay-bill-order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}