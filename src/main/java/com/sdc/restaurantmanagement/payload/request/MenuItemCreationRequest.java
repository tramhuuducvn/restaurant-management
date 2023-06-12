package com.sdc.restaurantmanagement.payload.request;

import com.sdc.restaurantmanagement.entity.MenuItem;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemCreationRequest {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String type;
    private Boolean deleted = false;

    public static MenuItem toEntity(MenuItemCreationRequest request) {
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