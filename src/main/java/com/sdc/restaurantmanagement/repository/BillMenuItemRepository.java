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
        /**
         * Find bill menu item in the bill_menu_item table in database with the given
         * bill id and menu item id
         * 
         * @param billOrderId id of bill order
         * @param menuItemId  id of menu item
         * @return bill menu item if found or else return null
         */
        Optional<BillMenuItem> findByBillOrderIdAndMenuItemId(Long billOrderId, Long menuItemId);

        /**
         * Soft delete bill menu item in database with the given bill id anh menu item
         * id
         * id
         * 
         * @param billId     id of bill order
         * @param menuItemId id of menu item
         */
        @Modifying
        @Query(value = "update bill_menu_item item set item.is_deleted = true where item.bill_order_id = :bill_order_id and item.menu_item_id = :menu_item_id", nativeQuery = true)
        void softDeleteByBillOrderIdAndMenuItemId(@Param("bill_order_id") Long billId,
                        @Param("menu_item_id") Long menuItemId);

        /**
         * Restore a deleted bill menu item with the given bill id anh menu item id
         * 
         * @param billId     id of bill order
         * @param menuItemId id of menu item
         */
        @Modifying
        @Query(value = "update bill_menu_item item set item.is_deleted = false where item.bill_order_id = :bill_order_id and item.menu_item_id = :menu_item_id", nativeQuery = true)
        void unSoftDeleteByBillOrderIdAndMenuItemId(@Param("bill_order_id") Long billId,
                        @Param("menu_item_id") Long menuItemId);
}