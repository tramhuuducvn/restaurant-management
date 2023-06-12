package com.sdc.restaurantmanagement.repository;

import com.sdc.restaurantmanagement.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
     List<MenuItem> findAllByDeleted(Boolean state);
}