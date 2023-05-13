package org.example.repository;

import lombok.Getter;
import lombok.Setter;

public class BillOrderRepo {
    private static final BillOrderRepo instance;
    private static final String filename = "bill_order.csv";
    static {
        try {
            instance = new BillOrderRepo();
        }
        catch (RuntimeException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating singleton object");
        }
    }

    private BillOrderRepo(){

    }

    public static BillOrderRepo getInstance(){
        return instance;
    }
}