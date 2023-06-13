package com.sdc.restaurantmanagement.payload.request;

import com.sdc.restaurantmanagement.entity.MenuItem;
import com.sdc.restaurantmanagement.util.Helper;
import lombok.Getter;
import lombok.Data;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.net.MalformedURLException;

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

    public static MenuItem toEntity(MenuItemCreationRequest request) throws MalformedURLException {
        if(!Helper.isValidURL(request.imageUrl)) {
            throw new MalformedURLException("Image URL is invalid");
        }
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