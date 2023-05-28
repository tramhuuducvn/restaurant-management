package org.sdc.restaurant.repository;

import org.sdc.restaurant.constant.Constant;
import org.sdc.restaurant.constant.SpecialCharacters;
import org.sdc.restaurant.entity.BillOrder;
import org.sdc.restaurant.entity.MenuItem;
import org.sdc.restaurant.util.DateTimeHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class BillOrderRepository is designed follow singleton design pattern
 * This class includes the necessary method for read/write data if bill order.
 */
public class BillOrderRepository {
    private static final BillOrderRepository instance;
    private static File file;

    static {
        try {
            instance = new BillOrderRepository();
        }
        catch (RuntimeException e){
            throw new RuntimeException("Exception occurred in creating singleton object");
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
            throw new RuntimeException("Exception occurred in creating new file");
        }
    }

    public static BillOrderRepository getInstance(){
        return instance;
    }

    /**
     * Find the MenuItem that has the name as same as the name in values array, return MenuItem if found and return null otherwise
     * @param values is an array of string values represented by fields in MenuItem object.
     * @return a MenuItem that was created from raw data in values array.
     */
    private BillOrder createBillOrder(String[] values){
        // Five element data is: Bill, Menu, Quantity, Ordered Time, Types
        if(values.length != Constant.NUMBER_OF_BILL_ORDER_FIELDS){
            return null;
        }

        MenuItemRepository menuItemRepository = MenuItemRepository.getInstance();
        List<MenuItem> menuItems = menuItemRepository.readMenuItems();
        MenuItem newItem = menuItems.stream().filter(item -> item.getName().equals(values[1])).findFirst().orElse(null);
        if (newItem == null) {
            return null;
        }
        return new BillOrder(Integer.parseInt(values[0]), newItem, Integer.parseInt(values[2]), DateTimeHelper.getDateFromText(values[3]));
    }

    /**
     * Import data from file csv and convert it to MenuItem Object.
     * @return list of menu item.
     */
    public List<BillOrder> readBillOrder(){
        try {
            List<BillOrder> billOrders = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] values = line.split(SpecialCharacters.COMMA_SPACE);
                if(values[0].equals("Bill Number")){
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
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**
     * Import data from file csv and convert it to MenuItem Object.
     * @return list of menu item.
     */
    public void writeAll(List<BillOrder> data){
        if(data == null){
            return;
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);
            // Bill, Menu, Quantity, Ordered Time, Types
            printWriter.println("Bill Number, Menu, Quantity, Ordered Time, Types");
            data.stream().map(BillOrder::toCSV).forEach(printWriter::println);
            printWriter.flush();
            printWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}