package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BillOrder {
    private int billNumber;
    private MenuItem item;
    private int quantities;
    private Date orderedTime;
    private double totalPrice;

    public BillOrder(int billNumber, MenuItem item, int quantities, Date orderedTime) {
        this.billNumber = billNumber;
        this.item = item;
        this.quantities = quantities;
        this.orderedTime = orderedTime;
    }

    public double getTotalPrice() {
        return item.getPrice()*quantities;
    }

    public String toCSV() {
        return  billNumber + ", " + item.getName() + ", " + quantities + ", " + orderedTime.toString() + ", " + item.getTypes();
    }
}