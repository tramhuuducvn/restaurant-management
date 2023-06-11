package com.sdc.restaurantmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
public class RestaurantManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementApplication.class, args);
//		System.out.println(Constant.DEFAULT_ERROR_MESSAGE);
	}

}