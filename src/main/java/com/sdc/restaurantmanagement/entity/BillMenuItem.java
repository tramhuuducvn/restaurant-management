package com.sdc.restaurantmanagement.entity;

import lombok.Getter;
import lombok.Data;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill_menu_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"bill_order_id", "menu_item_id"})})
public class BillMenuItem {
    @Id
    @GeneratedValue
    private Long id;
    private Integer number;
    private Date updateTime;

    @ManyToOne(optional = false, targetEntity = MenuItem.class)
    @JoinColumn(name = "menu_item_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MenuItem menuItem;

    @ManyToOne(optional = false, targetEntity = BillOrder.class)
    @JoinColumn(name = "bill_order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BillOrder billOrder;
}