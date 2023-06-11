package com.sdc.restaurantmanagement.payload.response;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BodyResponseTest {
    @Test
    public void testBuilderClass(){
        BodyResponse body = new BodyResponse.BodyResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).buildMessage("Success").build();
        System.out.println(body.toString());
        assertEquals(200, body.getStatus());
    }
}