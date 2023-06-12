package com.sdc.restaurantmanagement;


import com.sdc.restaurantmanagement.repository.BillOrderRepository;
import com.sdc.restaurantmanagement.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
@RequiredArgsConstructor
public class RestaurantManagementApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementApplication.class, args);
	}

	private final BillOrderRepository billOrderRepository;
	private final MenuItemRepository menuItemRepository;

	@Override
	@Transactional
	public void run(String...args) throws  Exception{
//		billOrderRepository.findById(4L);
	}

}