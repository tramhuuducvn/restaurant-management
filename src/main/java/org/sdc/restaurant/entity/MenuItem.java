package org.sdc.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sdc.restaurant.constant.SpecialCharacters;


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
        this.name = name.replaceAll(SpecialCharacters.COMMA, SpecialCharacters.SPACE);
        this.description = description.replaceAll(SpecialCharacters.COMMA, SpecialCharacters.SPACE);
        this.image = image.replaceAll(SpecialCharacters.COMMA, SpecialCharacters.SPACE);
        this.price = price;
        this.types = types.replaceAll(SpecialCharacters.COMMA, SpecialCharacters.SPACE);
    }

    public String getContent() {
        return this.name + SpecialCharacters.SPACE + this.description + SpecialCharacters.SPACE + this.getTypes();
    }

    public String toCSV() {
        //Name, Description, Image, Price, Types
        return this.name + SpecialCharacters.COMMA_SPACE + this.description + SpecialCharacters.COMMA_SPACE + this.image + SpecialCharacters.COMMA_SPACE + this.price + SpecialCharacters.COMMA_SPACE + this.types;
    }

    @Override
    public String toString(){
        return this.name + SpecialCharacters.COMMA_SPACE + this.description + SpecialCharacters.COMMA_SPACE + this.image + SpecialCharacters.COMMA_SPACE + this.price + SpecialCharacters.COMMA_SPACE + this.types;
    }

    @Override
    public boolean equals(Object obj) {
        MenuItem target = (MenuItem) obj;
        return this.name.equals(target.getName());
    }
}