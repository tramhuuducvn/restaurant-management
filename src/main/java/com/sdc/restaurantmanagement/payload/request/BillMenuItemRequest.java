package com.sdc.restaurantmanagement.payload.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillMenuItemRequest {
    private Long menuItemId;
    private Integer number;
}