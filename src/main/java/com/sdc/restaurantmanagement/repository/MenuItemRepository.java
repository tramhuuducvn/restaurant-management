package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
     List<MenuItem> findAllByDeleted(Boolean state);

     /**
      * Query all menu items have at least a field match with the given information.
      * @param name name of menu item.
      * @param description description of menu item.
      * @param type additional information.
      * @return List of menu items.
      */
     @Query("select item from MenuItem item where " +
            "item.name like %:name% and " +
            "item.description like %:description% and " +
            "item.type like %:type%")
     List<MenuItem> search(
             @Param("name") String name,
             @Param("description") String description,
             @Param("type") String type);
}