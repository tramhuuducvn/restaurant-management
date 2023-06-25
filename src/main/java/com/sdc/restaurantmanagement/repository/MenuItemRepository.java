package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
     List<MenuItem> findAllByIsDeletedFalse();
//     List<MenuItem> findAllByIsDeletedFalse();

     Page<MenuItem> findAllByIsDeletedFalse(Pageable pageable);

     Optional<MenuItem> findByIdAndIsDeletedFalse(Long id);

     /**
      * Query all menu items have at least a field match with the given information.
      * @param name name of menu item.
      * @param description description of menu item.
      * @param type additional information.
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