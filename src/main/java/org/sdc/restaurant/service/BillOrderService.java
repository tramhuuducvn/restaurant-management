package org.sdc.restaurant.service;

import org.sdc.restaurant.entity.BillOrder;

import java.util.Date;
import java.util.List;

public interface BillOrderService {

    /**
     * Create new bill order
     *
     * @param billOrder data of new bill order
     * @return true if create successful
     */
    boolean create(BillOrder billOrder);

    /**
     * get all bill orders
     *
     * @return list of bill order
     */
    List<BillOrder> getAll();

    /**
     * Get bill order by bill id
     *
     * @param billId  bill id
     * @return bill order
     */
    List<BillOrder> getById(int billId);

    /**
     * @param billId id of bill order
     * @param dishId index of menu item in bill order
     * @return index of bill order in list bill
     */
    int findBillIndexByItemId(int billId, int dishId);

        /**
         * Find index of item in bill order by dish index.
         *
         * @param billId id of bill order
         * @param dishIndex index of dish in bill order
         * @return index of bill order has a given dish
         */
    int findBillIndexByDishIndex(int billId, int dishIndex);

    /**
     * Update quantities item.
     *
     * @param billId id of bill order
     * @param dishIndex  dish index
     * @param quantity   quantity
     * @return true if update success
     */
    boolean updateQuantitiesItem(int billId, int dishIndex, int quantity);

    /**
     * Remove bill by bill id
     *
     * @param billId similar to bill id
     * @param dishIndex  index of item in bill, ex: 1, 2, 3
     * @return true if remove successfully
     */
    boolean remove(int billId, int dishIndex);

    /**
     * Calculate total price
     *
     * @param billId id of bill order
     * @return total price of bill
     */
    double calculateTotalPrice(int billId);

    /**
     * export data to file csv
     */
    void saveToFile();


    /**
     * print bill order information, that include list of dish, number and total price, etc.
     *
     * @param billId id of bill order
     */
    void printBillOrder(int billId);

    /**
     * Get max of bill id
     * @return max of bill id
     */
    int getMaxBillNumber();

    /**
     * Find index item by dish index
     *
     * @param billId    id of bill order
     * @param dishIndex index of dish in that bill
     * @return BillOrder
     */
    BillOrder getBillByDishIndex(int billId, int dishIndex);

    /**
     * Get ordered time of bill order
     * @param billId id of bill order
     */
    Date getOrderedDate(int billId);
}