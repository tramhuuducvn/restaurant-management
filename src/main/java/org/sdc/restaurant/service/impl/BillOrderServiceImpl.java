package org.sdc.restaurant.service.impl;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.repository.BillOrderRepository;
import org.sdc.restaurant.service.BillOrderService;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Singleton Design
public class BillOrderServiceImpl implements BillOrderService {
    private static final BillOrderRepository billOrderRepo;
    private static final BillOrderServiceImpl instance;

    static {
        try {
            billOrderRepo = BillOrderRepository.getInstance();
            instance = new BillOrderServiceImpl();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private final List<BillOrder> billOrders;

    private BillOrderServiceImpl() {
        billOrders = billOrderRepo.readBillOrder();
    }

    /**
     * 
     * @return singleton instance
     */
    public static BillOrderServiceImpl getInstance() {
        return instance;
    }

    /**
     * Create new bill order
     * 
     * @param billOrder data of new bill order
     * @return true if create successful else return false
     */
    public boolean create(BillOrder billOrder) {
        if (billOrder == null || billOrder.getItem() == null) {
            return false;
        }

        int existedBillIndex = this.findIndexItem(billOrder.getBillId(), billOrder.getItem().getName());
        if (existedBillIndex >= 0) {
            System.out.println("Item has been existed in bill");
            return false;
        }

        return billOrders.add(billOrder);
    }

    /**
     * get all bill orders
     * 
     * @return list all all bil order
     */
    public List<BillOrder> getAll() {
        return billOrders;
    }

    /**
     * Get bill order by bill number
     * 
     * @param billNumber
     * @return bill order has bill number equal the given bill number in param
     */
    public List<BillOrder> getByNumber(int billNumber) {
        return billOrders.stream().filter(item -> item.getBillId() == billNumber).collect(Collectors.toList());
    }

    /**
     * Get menu item by bill number
     * 
     * @param billNumber
     * @return list of dishes in bill order
     */
    public List<MenuItem> getMenuItemByBillNumber(int billNumber) {
        return billOrders.stream().filter(item -> item.getBillId() == billNumber).map(BillOrder::getItem)
                .collect(Collectors.toList());
    }

    /**
     * Find index of dish item in bill order by name
     * 
     * @param billNumber
     * @param name
     * @return index of bill have bill number and name of dish.
     */
    public int findIndexItem(int billNumber, String name) {
        try {
            MenuItemServiceImpl menuItemService = MenuItemServiceImpl.getInstance();
            MenuItem dish = menuItemService.findByName(name);
            System.out.println(name + " - " + dish.getName());
            return IntStream.range(0, billOrders.size())
                    .filter(item -> (billNumber + 1 == billOrders.get(item).getBillId() && name.equals(dish.getName())))
                    .findFirst().getAsInt();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    /**
     * Find index of item in bill order by dish index.
     * 
     * @param billNumber
     * @param dishIndex
     * @return index of bill have bill number and index of dish in that bill.
     */
    public int findIndexItemByDishIndex(int billNumber, int dishIndex) {
        int i = 0;
        while (i < billOrders.size()) {
            if (billOrders.get(i).getBillId() == billNumber) {
                dishIndex--;
            }
            if (dishIndex == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Update quantities item.
     * 
     * @param billNumber
     * @param dishIndex
     * @param quantity
     * @return return true if update successful
     */
    public boolean updateQuantitiesItem(int billNumber, int dishIndex, int quantity) {
        int index = this.findIndexItemByDishIndex(billNumber, dishIndex);
        if (index < 0) {
            System.out.println("Index: " + index);
            return false;
        }
        System.out.println("Index: " + index);
        BillOrder billOrder = billOrders.get(index);
        billOrder.setQuantities(quantity);
        return billOrders.set(index, billOrder) != null;
    }

    /**
     * Remove bill by bill number
     * 
     * @param billNumber similar to bill id
     * @param dishIndex  index of item in bill, ex: 1, 2, 3
     * @return true if remove successfully
     */
    public boolean remove(int billNumber, int dishIndex) {
        int index = this.findIndexItemByDishIndex(billNumber, dishIndex);
        if (index < 0) {
            System.out.println("Index: " + index);
            return false;
        }

        System.out.println("Index: " + index);
        return billOrders.remove(index) != null;
    }

    /**
     * Calculate total price
     * 
     * @param billNumber similar to bill id
     * @return total price of bill
     */
    public double calculateTotalPrice(int billNumber) {
        return billOrders.stream().filter(item -> item.getBillId() == billNumber).map(BillOrder::getTotalPrice)
                .reduce(0.0, Double::sum);
    }

    /**
     * export data to file csv
     */
    public void saveToFile() {
        billOrderRepo.writeAll(billOrders);
    }

    /**
     * print bill order information, that include list of dish, number and total
     * price, etc.
     * 
     * @param billNumber bill id
     */
    public void printBillOrder(int billNumber) {
        List<BillOrder> list = this.getByNumber(billNumber);
        if (list == null || list.isEmpty()) {
            return;
        }

        double totalBill = 0;
        System.out.println("\n------------------ Bill Order #" + billNumber + " ------------------");
        int i = 1;
        for (BillOrder item : list) {
            totalBill += item.getTotalPrice();
            System.out.println(i + "/ " + item.getItem().getName() + ", quantities: " + item.getQuantities()
                    + ", price: " + item.getItem().getPrice());
            i++;
        }
        System.out.println("### Total Price of bill #" + billNumber + " is: " + totalBill);
    }

    /**
     * Get max of bill number
     * 
     * @return max of bill number
     */
    public int getMaxBillNumber() {
        if (billOrders == null || billOrders.isEmpty()) {
            return 1;
        }
        BillOrder billOrder = billOrders.stream().max(Comparator.comparingInt(BillOrder::getBillId)).get();
        return billOrder.getBillId();
    }
}