package com.sdc.restaurantmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill_order")
@Entity
public class BillOrder {
    @Id
    @SequenceGenerator(
            name = "bill_order_sequence",
            sequenceName = "bill_order_sequence",
            allocationSize = 1 // increment by 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    //    @Column(nullable = false, unique = true, length = 300)
    private ConcurrentHashMap<MenuItem, Long> items;
    private Date orderedTime;
}