package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillOrderRepository extends JpaRepository<BillOrder, Long> {

}