package com.sdc.restaurantmanagement.payload.response;

import com.sdc.restaurantmanagement.payload.APIResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BodyResponseTest {
    @Test
    public void testBuilderClass(){
        APIResponse body = APIResponse.builder(HttpStatus.OK).build();
        System.out.println(body.toString());
        assertEquals(200, body.getStatus());
    }
}