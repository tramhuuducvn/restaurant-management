package org.example.service;

import org.example.entity.BillOrder;
import org.example.entity.MenuItem;
import org.example.repository.BillOrderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Singleton Design
public class BillOrderService {
    private static BillOrderRepository billOrderRepo;
    private static BillOrderService instance;
    private List<BillOrder> billOrders;

    static {
        try {
            billOrderRepo = BillOrderRepository.getInstance();
            instance = new BillOrderService();
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    private BillOrderService(){
        billOrders = billOrderRepo.readBillOrder();
    }
    
    public static BillOrderService getInstance(){
        return instance;
    }

    public boolean createBillOrder(BillOrder billOrder){
        if(billOrder == null){
            return false;
        }
        boolean result = billOrders.add(billOrder);
        this.saveToFile();
        return result;
    }

    public List<BillOrder> getBillOrderByNumber(int billNumber){
        return billOrders.stream().filter(item->item.getBillNumber() == billNumber).collect(Collectors.toList());
    }

    public List<MenuItem> getMenuItemByBillNumber(int billNumber){
        return billOrders.stream().filter(item->item.getBillNumber() == billNumber).map(BillOrder::getItem).collect(Collectors.toList());
    }

    private int findIndexItem(int billNumber, String name) {
        try {
            return IntStream.range(0, billOrders.size()).filter(item -> (billNumber == billOrders.get(item).getBillNumber() && name.equals(billOrders.get(item).getItem().getName()))).findFirst().getAsInt();
        }
        catch (NoSuchElementException e){
            return -1;
        }
    }

    public boolean updateQuantitiesItem(int billNumber, String name, int quantity){
        int index =  this.findIndexItem(billNumber, name);
        if(index < 0) {
            return false;
        }
        BillOrder billOrder = billOrders.get(index);
        billOrder.setQuantities(quantity);
        boolean result = billOrders.set(index, billOrder) != null;
        this.saveToFile();
        return result;
    }

    public boolean removeBillItem(int billNumber, String name){
        int index =  this.findIndexItem(billNumber, name);
        if(index < 0) {
            return false;
        }
        boolean result = billOrders.remove(index) != null;
        this.saveToFile();
        return result;
    }

    public double calculateBillOrder(int billNumber) {
       return billOrders.stream().filter(item->item.getBillNumber() == billNumber).map(BillOrder::getTotalPrice).reduce(0.0, (a, b)->a+b);
    }

    public void saveToFile(){
        billOrderRepo.writeAll(billOrders);
    }

    public void clearAll(){
        billOrders.clear();
    }
}