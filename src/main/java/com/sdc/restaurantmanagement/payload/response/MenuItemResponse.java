package com.sdc.restaurantmanagement.payload.response;

import com.sdc.restaurantmanagement.entity.MenuItem;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private String type;

    /**
     * Mapper MenuItem to MenuItemDTO
     * @param entity MenuItem collect from the database
     * @return MenuItemDTO
     */
    public static MenuItemResponse fromEntity(MenuItem entity){
        if(entity == null){
            return null;
        }
        return MenuItemResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .type(entity.getType())
                .build();
    }
}