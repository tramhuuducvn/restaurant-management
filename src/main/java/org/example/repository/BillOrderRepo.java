package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.BillOrder;
import org.example.entity.MenuItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillOrderRepo {
    private static final BillOrderRepo instance;
    private static final File file;
    private List<BillOrder> billOrders;

    static {
        try {
            instance = new BillOrderRepo();
            file = new File("bill_order.csv");
            if(!file.exists()){
                file.createNewFile();
            }
        }
        catch (RuntimeException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating singleton object");
        }
        catch (IOException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating new file");
        }
    }

    private BillOrderRepo(){
        billOrders = new ArrayList<>();
    }

    public static BillOrderRepo getInstance(){
        return instance;
    }
    private void loadingMenuItems(){};
    public void getMenuItems(){};
    public void addItem(BillOrder billOrder){};
    public void deleteItem(){};
    public void saveAll(){};
}