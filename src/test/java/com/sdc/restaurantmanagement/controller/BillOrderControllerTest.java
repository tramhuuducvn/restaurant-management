package com.sdc.restaurantmanagement.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class BillOrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAllBillOrder_OK_IfGetBillOrderSuccess() {

    }

    @Test
    void testGetBillOrderById_OK_GetBillOrderByIdSuccess() {
    }

    @Test
    void testCreateBillOrder_CREATED_IfCreateSuccess() {
    }

    @Test
    void testAddMenuItem_ACCEPTED_IfAddSuccess() {
    }

    @Test
    void testUpdateMenuItemQuantity_ACCEPTED_IfUpdateSuccess() {
    }

    @Test
    void testDeleteBillMenuItem_NO_CONTENT_IfNothing() {
    }
}