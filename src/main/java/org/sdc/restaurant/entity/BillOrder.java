package org.sdc.restaurant.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sdc.restaurant.constant.SpecialCharacters;

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
        return  billNumber + SpecialCharacters.COMMA_SPACE + item.getName() + SpecialCharacters.COMMA_SPACE + quantities + SpecialCharacters.COMMA_SPACE + orderedTime.toString() + SpecialCharacters.COMMA_SPACE + item.getTypes();
    }

    @Override
    public String toString() {
        return  billNumber + SpecialCharacters.COMMA_SPACE + item.getName() + SpecialCharacters.COMMA_SPACE + quantities + SpecialCharacters.COMMA_SPACE + orderedTime.toString() + SpecialCharacters.COMMA_SPACE + item.getTypes();
    }

    @Override
    public boolean equals(Object obj) {
        BillOrder target = (BillOrder) obj;
        return this.billNumber == target.billNumber && this.item.equals(target.item);// && this.quantities == target.quantities;
    }
}