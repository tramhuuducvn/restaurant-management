package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillMenuItemRepository extends JpaRepository<BillMenuItem, Long> {
    Optional<BillMenuItem> findByBillOrderIdAndMenuItemId(Long billOrderId, Long menuItemId);
    List<BillMenuItem> findByMenuItemId(Long menuItemId);

}