package org.sdc.restaurant.service;

import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.repository.BillOrderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Singleton Design
public class BillOrderService {
    private static final BillOrderRepository billOrderRepo;
    private static final BillOrderService instance;

    static {
        try {
            billOrderRepo = BillOrderRepository.getInstance();
            instance = new BillOrderService();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private final List<BillOrder> billOrders;

    private BillOrderService() {
        billOrders = billOrderRepo.readBillOrder();
    }

    public static BillOrderService getInstance() {
        return instance;
    }

    public boolean create(BillOrder billOrder) {
        if (billOrder == null || billOrder.getItem() == null) {
            return false;
        }

        int existedBillIndex = this.findIndexItem(billOrder.getBillNumber(), billOrder.getItem().getName());
        if (existedBillIndex >= 0) {
            return this.updateQuantitiesItem(billOrder.getBillNumber(), billOrder.getItem().getName(), billOrder.getQuantities() + billOrders.get(existedBillIndex).getQuantities());
        }

        return billOrders.add(billOrder);
    }

    public List<BillOrder> getAll() {
        return billOrders;
    }

    public List<BillOrder> getByNumber(int billNumber) {
        return billOrders.stream().filter(item -> item.getBillNumber() == billNumber).collect(Collectors.toList());
    }

    public List<MenuItem> getMenuItemByBillNumber(int billNumber) {
        return billOrders.stream().filter(item -> item.getBillNumber() == billNumber).map(BillOrder::getItem).collect(Collectors.toList());
    }

    private int findIndexItem(int billNumber, String name) {
        try {
            MenuItemService menuItemService = MenuItemService.getInstance();
            MenuItem dish = menuItemService.findByName(name);
            System.out.println(name + " - " + dish.getName());
            return IntStream.range(0, billOrders.size()).filter(item -> (billNumber + 1 == billOrders.get(item).getBillNumber() && name.equals(dish.getName()))).findFirst().getAsInt();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    public boolean updateQuantitiesItem(int billNumber, String name, int quantity) {
        int index = this.findIndexItem(billNumber, name);
        if (index < 0) {
            System.out.println("Index: " + index);
            return false;
        }
        System.out.println("Index: " + index);
        BillOrder billOrder = billOrders.get(index);
        billOrder.setQuantities(quantity);
        return billOrders.set(index, billOrder) != null;
    }

    public boolean remove(int billNumber, String name) {
        int index = this.findIndexItem(billNumber, name);
        if (index < 0) {
            return false;
        }

        return billOrders.remove(index) != null;
    }

    public double calculateTotalPrice(int billNumber) {
        return billOrders.stream().filter(item -> item.getBillNumber() == billNumber).map(BillOrder::getTotalPrice).reduce(0.0, Double::sum);
    }

    public void saveToFile() {
        billOrderRepo.writeAll(billOrders);
    }

    public void clearAll() {
        billOrders.clear();
    }

    public void printBillOrder(int billNumber) {
        List<BillOrder> list = this.getByNumber(billNumber);
        if (list.size() < 1) {
            return;
        }

        double totalBill = 0;
        System.out.println("\n------------------ Bill Order #" + billNumber + " ------------------");
        for (BillOrder item : list) {
            totalBill += item.getTotalPrice();
            System.out.println("Dish: " + item.getItem().getName() + ", quantities: " + item.getQuantities() + ", total price: " + item.getTotalPrice());
        }
        System.out.println("### Total Price of bill #" + billNumber + " is: " + totalBill);
    }
}