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

    public static MenuItem toEntity(MenuItemResponse item) {
        return  MenuItem.builder()
                .id(item.id)
                .name(item.name)
                .description(item.description)
                .price(item.price)
                .imageUrl(item.imageUrl)
                .type(item.type)
                .build();
    }

    public MenuItem toEntity() {
        return  MenuItem.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .imageUrl(this.imageUrl)
                .type(this.type)
                .build();
    }
}