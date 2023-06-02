package org.sdc.restaurant.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sdc.restaurant.constant.SpecialCharacters;
import org.sdc.restaurant.entity.BillOrder;

import java.util.ArrayList;
import java.util.List;


public class BillOrderRepositoryTest {
    BillOrderRepository repository;

    @Before
    public void setUp(){
        repository = BillOrderRepository.getInstance();
    }

    @Test
    public void getInstance() {
        BillOrderRepository obj2 = BillOrderRepository.getInstance();
        Assert.assertEquals(repository, obj2);
    }

    @Test
    public void createBillOrder(){
        String src = "2, Banh Xeo, 100, Fri May 26 16:49:20 ICT 2023, viet nam gao";
        String[] values = src.split(SpecialCharacters.COMMA_SPACE);
        BillOrder billOrder = repository.createBillOrder(values);
        Assert.assertEquals(src, billOrder.toCSV());
    }

//    @Test
//    public void readAndWriteData() {
//        List<BillOrder> list = new ArrayList<>();
//        list.add(repository.createBillOrder("2, Banh Xeo, 100, Fri May 26 16:49:20 ICT 2023, viet nam gao".split(SpecialCharacters.COMMA_SPACE)));
//        repository.writeAll(list);
//        List <BillOrder> actual = repository.readBillOrder();
//        Assert.assertEquals(list, actual);
//    }
}