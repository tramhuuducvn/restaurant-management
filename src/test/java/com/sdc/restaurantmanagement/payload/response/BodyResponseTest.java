package com.sdc.restaurantmanagement.payload.response;

import com.sdc.restaurantmanagement.payload.APIResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BodyResponseTest {
    @Test
    public void testBuilderClass(){
        APIResponse body = APIResponse.builder().build();
        System.out.println(body.toString());
        Assertions.assertNull(body.getData());
    }
}