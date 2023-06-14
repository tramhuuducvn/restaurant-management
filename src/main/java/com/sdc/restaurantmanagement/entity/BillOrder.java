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

    @OneToMany(targetEntity = BillMenuItem.class, mappedBy = "billOrder",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<BillMenuItem> items;
}