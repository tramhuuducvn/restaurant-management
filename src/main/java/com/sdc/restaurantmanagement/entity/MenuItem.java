package com.sdc.restaurantmanagement.entity;

import com.sdc.restaurantmanagement.util.Helper;
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
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String type;
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;

    public void setImageUrl(String imageUrl) throws MalformedURLException {
        if(!Helper.isValidURL(imageUrl)){
            throw new MalformedURLException("Image URL is not valid");
        }
        this.imageUrl = imageUrl;
    }
}