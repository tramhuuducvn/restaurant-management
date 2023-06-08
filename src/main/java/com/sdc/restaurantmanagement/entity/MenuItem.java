package com.sdc.restaurantmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @SequenceGenerator(
        name = "menu_item_sequence",
        sequenceName = "menu_item_sequence",
        allocationSize = 1 // increment by 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Column(nullable = false, unique = true, length = 300)
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private String type;
}