package com.sdc.restaurantmanagement.entity;

import com.sdc.restaurantmanagement.util.Helper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Data;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.net.MalformedURLException;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue
    @Schema(description = "Menu Item UUID in the database")
    private Long id;
    private String name;
    private String description;
    @Column(nullable = false)
    private Double price;
    private String imageUrl;
    private String type;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void setImageUrl(String imageUrl) throws MalformedURLException {
        if (!Helper.isValidURL(imageUrl)) {
            throw new MalformedURLException("Image URL is not valid");
        }
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        if (this.price == null) {
            return 0.0;
        }
        return this.price;
    }
}