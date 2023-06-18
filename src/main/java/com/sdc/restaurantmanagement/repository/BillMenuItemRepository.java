package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface BillMenuItemRepository extends JpaRepository<BillMenuItem, Long> {
    Optional<BillMenuItem> findByBillOrderIdAndMenuItemId(Long billOrderId, Long menuItemId);

    @Modifying
    @Query(value = "update bill_menu_item item set item.is_deleted = true where item.bill_order_id = :bill_order_id and item.menu_item_id = :menu_item_id", nativeQuery = true)
    void softDeleteByBillOrderIdAndMenuItemId(@Param("bill_order_id") Long billId, @Param("menu_item_id") Long menuItemId);
}