package org.sdc.restaurant.repository;

import org.junit.Test;
import org.sdc.restaurant.constant.SpecialCharacters;

import static org.junit.Assert.*;

public class BillOrderRepositoryTest {

    @Test
    public void getInstance() {
        BillOrderRepository obj1 = BillOrderRepository.getInstance();
        BillOrderRepository obj2 = BillOrderRepository.getInstance();
        assertEquals(obj1, obj2);
    }

    @Test
    public void createBillOrder(){
        String[] values = "2, Banh Xeo, 100, Fri May 26 16:49:20 ICT 2023, viet nam gao".split(SpecialCharacters.COMMA_SPACE);
        
    }
}