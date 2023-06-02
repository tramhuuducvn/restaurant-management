package org.sdc.restaurant.service.impl;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.repository.BillOrderRepository;
import org.sdc.restaurant.service.BillOrderService;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class BillOrderServiceImpl is designed follow singleton design pattern
 * This class includes the necessary method for handling business logic for each request related to BillOrder.
 */
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
        int existedBillIndex = this.findBillIndexByItemId(billOrder.getBillId(), billOrder.getItem().getItemId());
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
     * Get bill order by bill id
     *
     * @param billId bill id
     * @return bill order has bill id equal the given bill id in param
     */
    public List<BillOrder> getById(int billId) {
        return billOrders.stream().filter(item -> item.getBillId() == billId).collect(Collectors.toList());
    }

    /**
     * Find index bill by dish index.
     * 
     * @param billId id of bill order
     * @param dishIndex index of dish bill order
     * @return index of bill have bill id and index of dish in that bill.
     */
    public int findBillIndexByDishIndex(int billId, int dishIndex) {
        int i = 0;
        while (i < billOrders.size()) {
            if (billOrders.get(i).getBillId() == billId) {
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
     * Find index bill by menu item id
     * @param billId id of bill order
     * @param itemId id of menu item
     * @return index of bill order in list bill
     */
    public int findBillIndexByItemId(int billId, int itemId) {
        int i = 0;
        for (BillOrder bill : billOrders) {
            if (bill.getBillId() == billId && bill.getItem().getItemId() == itemId) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Find index item by dish index
     * @param billId id of bill order
     * @param dishIndex index of dish in that bill
     * @return BillOrder
     */
    @Override
    public BillOrder getBillByDishIndex(int billId, int dishIndex){
        int index = this.findBillIndexByDishIndex(billId, dishIndex);
        if(index < 0){
            return null;
        }
        return billOrders.get(index);
    }

    /**
     * Update quantities item.
     *
     * @param billId id of bill order
     * @param dishIndex index of dish in that bill
     * @param quantity new quantity
     * @return return true if update successful
     */
    public boolean updateQuantitiesItem(int billId, int dishIndex, int quantity) {
        int index = this.findBillIndexByDishIndex(billId, dishIndex);
        if (index < 0) {
            return false;
        }

        BillOrder billOrder = billOrders.get(index);
        billOrder.setQuantities(quantity);
        billOrder.setOrderedTime(new Date());
        return billOrders.set(index, billOrder) != null;
    }

    /**
     * Remove bill by bill id
     * 
     * @param billId similar to bill id
     * @param dishIndex  index of item in bill, ex: 1, 2, 3
     * @return true if remove successfully
     */
    public boolean remove(int billId, int dishIndex) {
        int index = this.findBillIndexByDishIndex(billId, dishIndex);
        if (index < 0) {
            return false;
        }

        return billOrders.remove(index) != null;
    }

    /**
     * Calculate total price
     * 
     * @param billId similar to bill id
     * @return total price of bill
     */
    public double calculateTotalPrice(int billId) {
        return billOrders.stream().filter(item -> item.getBillId() == billId).map(BillOrder::getTotalPrice)
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
     * @param billId bill id
     */
    public void printBillOrder(int billId) {
        List<BillOrder> list = this.getById(billId);
        if (list == null || list.isEmpty()) {
            return;
        }

        double totalBill = 0;
        System.out.println("\n------------------ Bill Order #" + billId + " ------------------");
        int i = 1;
        for (BillOrder item : list) {
            totalBill += item.getTotalPrice();
            System.out.println(i + "/ " + item.getItem().getName() + ", quantities: " + item.getQuantities()
                    + ", price: " + item.getItem().getPrice());
            i++;
        }

        System.out.println("### Ordered time #" + billId + " is: " + this.getOrderedDate(billId));
        System.out.println("### Total Price of bill #" + billId + " is: " + totalBill);
    }

    /**
     * Get max of bill id
     * 
     * @return max of bill id
     */
    public int getMaxBillNumber() {
        if (billOrders == null || billOrders.isEmpty()) {
            return 0;
        }
        BillOrder billOrder = billOrders.stream().max(Comparator.comparingInt(BillOrder::getBillId)).get();
        return billOrder.getBillId();
    }


    /**
     * Get ordered time of bill order
     * @param billId id of bill order
     */
    public Date getOrderedDate(int billId){
        BillOrder billOrder = billOrders.stream().max((a, b) -> (int)(a.getOrderedTime().getTime() - b.getOrderedTime().getTime())).get();
        if(billOrder == null){
            return null;
        }
        return billOrder.getOrderedTime();
    }
}