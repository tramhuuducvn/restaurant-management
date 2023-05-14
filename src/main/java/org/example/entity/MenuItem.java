package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem  {
    private int itemId;
    private String name;
    private String description;
    private String image;
    private double price;
    private String types;

    public MenuItem(String name, String description, String image, double price, String additionalDetail) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.types = additionalDetail;
    }

    public String toCSV() {
        //Name, Description, Image, Price, Types
        return  this.name + ", " + this.description + ", " + this.image + ", " + this.price + ", " + this.types;
    }
}