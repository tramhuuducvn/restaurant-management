package com.sdc.restaurantmanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

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
    private Boolean deleted = false;

    @ManyToMany(mappedBy = "menuItems")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<BillOrder> billOrders;

}