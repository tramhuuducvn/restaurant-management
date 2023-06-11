package com.sdc.restaurantmanagement.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping(value = "/menu")
public class MenuItemController {
    @GetMapping(value = "")
    public ResponseEntity<String> getMenuItem(){
        int a = 10 / 0;
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hello");
    }
}