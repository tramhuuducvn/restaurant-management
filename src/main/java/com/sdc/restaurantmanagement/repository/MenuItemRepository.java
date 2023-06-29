package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
       /**
        * Find all available menu item, that means the menu item is exists and not be
        * deleted
        * 
        * @return Menu item if found or else return null
        */
       List<MenuItem> findAllByIsDeletedFalse();

       /**
        * Find all menu item and support pageable
        * 
        * @param pageable config of pageable
        * @return a page of menu item
        */
       Page<MenuItem> findAllByIsDeletedFalse(Pageable pageable);

       /**
        * Find a menu item with the given id
        * 
        * @param id id of menu item
        * @return MenuItem if found of else return null
        */
       Optional<MenuItem> findByIdAndIsDeletedFalse(long id);

       /**
        * Query all menu items have at least a field match with the given information.
        * 
        * @param name        name of menu item.
        * @param description description of menu item.
        * @param type        additional information.
        * @return list of menu items.
        */
       @Query("select item from MenuItem item where " +
                     "item.name like %:name% and " +
                     "item.description like %:description% and " +
                     "item.type like %:type% and item.isDeleted = false")
       List<MenuItem> search(
                     @Param("name") String name,
                     @Param("description") String description,
                     @Param("type") String type);

}