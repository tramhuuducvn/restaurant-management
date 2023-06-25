package com.sdc.restaurantmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties
//@PropertySource(ignoreResourceNotFound = true, )
public class RestaurantManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementApplication.class, args);
	}
}