package com.sdc.restaurantmanagement.payload.response;

import lombok.Getter;
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
    private long totalMenuItem;
    private int pageSize;
    private boolean hasNextPage;
    private List<MenuItemResponse> items;
}