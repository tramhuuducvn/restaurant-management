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
    private int billId;
    private MenuItem item;
    private int quantities;
    private Date orderedTime;
    private double totalPrice;

    public BillOrder(int billId, MenuItem item, int quantities, Date orderedTime) {
        this.billId = billId;
        this.item = item;
        this.quantities = quantities;
        this.orderedTime = orderedTime;
    }

    /**
     * 
     * @return total price of bill item
     */
    public double getTotalPrice() {
        return item.getPrice() * quantities;
    }

    public String toCSV() {
        return billId + SpecialCharacters.COMMA_SPACE + item.getName() + SpecialCharacters.COMMA_SPACE + quantities
                + SpecialCharacters.COMMA_SPACE + orderedTime.toString() + SpecialCharacters.COMMA_SPACE
                + item.getTypes();
    }

    @Override
    public String toString() {
        return billId + SpecialCharacters.COMMA_SPACE + item.getName() + SpecialCharacters.COMMA_SPACE + quantities
                + SpecialCharacters.COMMA_SPACE + orderedTime.toString() + SpecialCharacters.COMMA_SPACE
                + item.getTypes();
    }

    @Override
    public boolean equals(Object obj) {
        BillOrder target = (BillOrder) obj;
        return this.billId == target.billId && this.item.equals(target.item);// && this.quantities == target.quantities;
    }
}