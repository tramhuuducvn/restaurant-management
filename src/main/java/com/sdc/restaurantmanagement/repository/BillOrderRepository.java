package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import com.sdc.restaurantmanagement.entity.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillOrderRepository extends JpaRepository<BillOrder, Long> {

}