package org.example.repository;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillOrderRepositoryTest {

    @Test
    public void getInstance() {
        BillOrderRepository obj1 = BillOrderRepository.getInstance();
        BillOrderRepository obj2 = BillOrderRepository.getInstance();
        Assert.assertEquals(obj1, obj2);
    }
}