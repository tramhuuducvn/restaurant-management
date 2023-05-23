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
    List<BillOrder> getByNumber(int billNumber);

    /**
     * Get menu item by bill number
     *
     * @param billNumber bill number or bill id
     * @return list menu item in bill order
     */
    List<MenuItem> getMenuItemByBillNumber(int billNumber);

    /**
     * Find index of dish item in bill order by name
     *
     * @param billNumber bill number or bill id
     * @param name
     * @return index of menu item has the given name
     */
    int findIndexItem(int billNumber, String name);

    /**
     * Find index of item in bill order by dish index.
     *
     * @param billNumber bill number or bill id
     * @param dishIndex
     * @return index of bill order has a given dish
     */
    int findIndexItemByDishIndex(int billNumber, int dishIndex);

    /**
     * Update quantities item.
     *
     * @param billNumber bill number
     * @param dishIndex  dish index
     * @param quantity   quantity
     * @return true if update success
     */
    boolean updateQuantitiesItem(int billNumber, int dishIndex, int quantity);

    /**
     * Remove bill by bill number
     *
     * @param billNumber similar to bill id
     * @param dishIndex  index of item in bill, ex: 1, 2, 3
     * @return true if remove successfully
     */
    boolean remove(int billNumber, int dishIndex);

    /**
     * Calculate total price
     *
     * @param billNumber similar to bill id
     * @return total price of bill
     */
    public double calculateTotalPrice(int billNumber);

    /**
     * export data to file csv
     */
    void saveToFile();


    /**
     * print bill order information, that include list of dish, number and total price, etc.
     *
     * @param billNumber bill id
     */
    void printBillOrder(int billNumber);

    /**
     * Get max of bill number
     * @return max of bill number
     */
     int getMaxBillNumber();
}
