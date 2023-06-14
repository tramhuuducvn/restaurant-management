package com.sdc.restaurantmanagement.payload.dto;

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
public class MenuItemDTO  {
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
    public static MenuItemDTO fromEntity(MenuItem entity){
        if(entity == null){
            return null;
        }
        return MenuItemDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .type(entity.getType())
                .build();
    }
}