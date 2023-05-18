package org.example.repository;

import org.example.entity.BillOrder;
import org.example.entity.MenuItem;
import org.example.service.MenuItemService;
import org.example.utils.DateTimeHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Singleton Design
public class BillOrderRepository {
    private static final BillOrderRepository instance;
    private static File file;

    static {
        try {
            instance = new BillOrderRepository();
        }
        catch (RuntimeException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating singleton object");
        }

    }

    private BillOrderRepository(){
        try {
            file = new File("bill_order.csv");
            if(!file.exists()){
                file.createNewFile();
            }
        }
        catch (IOException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating new file");
        }
    }

    public static BillOrderRepository getInstance(){
        return instance;
    }

    private BillOrder createBillOrder(String[] values){
        if(values.length != 5){
            return null;
        }
        MenuItemService menuItemService = MenuItemService.getInstance();
        MenuItem newItem = menuItemService.findMenuItemByName(values[1]);
        if(newItem == null){
            return null;
        }
        return new BillOrder(Integer.parseInt(values[0]), newItem, Integer.parseInt(values[2]), DateTimeHelper.getDateFromText(values[3]));
    }

    public List<BillOrder> readBillOrder(){
        try {
            List<BillOrder> billOrders = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = "";
            while((line = bufferedReader.readLine()) != null){
                String[] values = line.split(", ");
                if(values[0].equals("Bill")){
                    continue;
                }
                BillOrder newBill = this.createBillOrder(values);
                if(newBill == null) {
                    continue;
                }
                billOrders.add(this.createBillOrder(values));
            }
            bufferedReader.close();
            return billOrders;
        }
        catch (IOException e){
            return new ArrayList<>();
        }
    }

    public void writeAll(List<BillOrder> data){
        if(data == null){
            return;
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);
            // Bill, Menu, Quantity, Types
            printWriter.println("Bill, Menu, Quantity, Ordered Time, Types");
            data.stream().map(BillOrder::toCSV).forEach(printWriter::println);
            printWriter.flush();
            printWriter.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}