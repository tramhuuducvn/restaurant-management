package com.sdc.restaurantmanagement.payload.response;

import com.sdc.restaurantmanagement.payload.dto.MenuItemDTO;
import lombok.Getter;
import lombok.Data;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
    private int totalMenuItem;
    private List<MenuItemDTO> items;
}