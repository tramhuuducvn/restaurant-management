package com.sdc.restaurantmanagement.payload.request;

import com.sdc.restaurantmanagement.entity.MenuItem;
import lombok.Getter;
import lombok.Data;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemUpdateRequest {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String type;

    public static MenuItem toEntity(MenuItemUpdateRequest request) {
        return  MenuItem.builder()
                .name(request.name)
                .description(request.description)
                .price(request.price)
                .imageUrl(request.imageUrl)
                .type(request.type)
                .deleted(false)
                .build();
    }
}
