package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    public MenuItem(String name, String description, String image, double price, String types) {
        this.name = name.replaceAll(",", "");;
        this.description = description.replaceAll(",", "");
        this.image = image.replaceAll(",", "");;
        this.price = price;
        this.types = types.replaceAll(",", "");;
    }

    public String getContent() {
        return this.name + " " + this.description + " " + this.getTypes();
    }

    public String toCSV() {
        //Name, Description, Image, Price, Types
        return  this.name + ", " + this.description + ", " + this.image + ", " + this.price + ", " + this.types;
    }

    @Override
    public String toString(){
        return  this.name + ", " + this.description + ", " + this.image + ", " + this.price + ", " + this.types;
    }
}