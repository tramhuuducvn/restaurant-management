package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BillOrderRepository extends JpaRepository<BillOrder, Long> {
    /**
     * Export a bill order, set true to isPaid field and set current to orderedTime
     * @param id id of bill order
     */
    @Modifying
    @Query(value = "update bill_order bill set bill.is_paid = true, bill.order_time = CURRENT_TIMESTAMP where bill.id = :id", nativeQuery = true)
    void payBillOrder(@Param("id") Long id);

    @Modifying
    @Query(value = "update bill_order bill set bill.is_paid = false, bill.order_time = NULL where bill.id = :id", nativeQuery = true)
    void unPayBillOrder(@Param("id") Long id);
}