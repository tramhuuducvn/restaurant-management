package org.sdc.restaurant.service;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;

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
     * Get bill order by bill number
     *
     * @param billNumber bill number or bill id
     * @return bill order
     */
    List<BillOrder> getById(int billNumber);

    /**
     * Get menu item by bill id
     *
     * @param billId bill id
     * @return list menu item in bill order
     */
    List<MenuItem> getMenuItemByBillId(int billId);

    /**
     * Find index of dish item in bill order by name
     *
     * @param billId bill id or bill id
     * @param name
     * @return index of menu item has the given name
     */
    int findIndexItem(int billId, String name);

    /**
     * Find index of item in bill order by dish index.
     *
     * @param billId bill number or bill id
     * @param dishIndex
     * @return index of bill order has a given dish
     */
    int findIndexItemByDishIndex(int billId, int dishIndex);

    /**
     * Update quantities item.
     *
     * @param billId bill id
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
     * @param billId
     * @return total price of bill
     */
    public double calculateTotalPrice(int billId);

    /**
     * export data to file csv
     */
    void saveToFile();


    /**
     * print bill order information, that include list of dish, number and total price, etc.
     *
     * @param billId bill id
     */
    void printBillOrder(int billId);

    /**
     * Get max of bill id
     * @return max of bill id
     */
     int getMaxBillNumber();
}