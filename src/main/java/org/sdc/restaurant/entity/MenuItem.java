package org.sdc.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sdc.restaurant.constant.SpecialCharacters;
import org.sdc.restaurant.util.Helper;

/**
 * This class includes all attribute represents for BillOrder
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    private int itemId;
    private String name;
    private String description;
    private String image;
    private double price;
    private String types;
    private boolean isDeleted;

    /**
     * Custom constructor of MenuItem
     * @param name name of item
     * @param description description of dish
     * @param image url of item
     * @param price price of item
     * @param types type of item
     */
    public MenuItem(String name, String description, String image, double price, String types) {
        this.name = Helper.reformatText(name);
        this.description = Helper.reformatText(description);
        this.image = Helper.reformatText(image);
        this.price = price;
        this.types = Helper.reformatText(types);
        this.isDeleted = false;
    }

    /**
     * Get overall content of menu item
     * @return combination content of name, description, types.
     */
    public String getContent() {
        return this.name + SpecialCharacters.SPACE + this.description + SpecialCharacters.SPACE + this.getTypes();
    }

    public String toCSV() {
        // Name, Description, Image, Price, Types, Deleted
        return this.name + SpecialCharacters.COMMA_SPACE + this.description + SpecialCharacters.COMMA_SPACE + this.image
                + SpecialCharacters.COMMA_SPACE + this.price + SpecialCharacters.COMMA_SPACE + this.types
                + SpecialCharacters.COMMA_SPACE + this.isDeleted;
    }

    @Override
    public String toString() {
        return this.itemId + SpecialCharacters.COMMA_SPACE + this.name + SpecialCharacters.COMMA_SPACE
                + this.description + SpecialCharacters.COMMA_SPACE + this.image + SpecialCharacters.COMMA_SPACE
                + this.price + SpecialCharacters.COMMA_SPACE + this.types;
    }

    @Override
    public boolean equals(Object obj) {
        MenuItem target = (MenuItem) obj;
        return this.name.equals(target.getName());
    }

    public void setName(String name) {
        this.name = Helper.reformatText(name);
    }

    public void setDescription(String description) {
        this.description = Helper.reformatText(description);
    }

    public void setImage(String image) {
        this.image = Helper.reformatText(image);
    }

    public void setTypes(String types) {
        this.types = Helper.reformatText(types);
    }
}