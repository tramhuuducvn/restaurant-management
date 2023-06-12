package com.sdc.restaurantmanagement.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill_order")
@Entity
public class BillOrder {
    @Id
    @GeneratedValue
    private Long id;
    private Date orderedTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "bill_order_menu_item", joinColumns = @JoinColumn(name = "bill_id"), inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
    private Collection<MenuItem> menuItems;
}