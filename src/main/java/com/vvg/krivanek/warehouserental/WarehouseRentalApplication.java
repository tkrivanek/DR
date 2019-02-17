package com.vvg.krivanek.warehouserental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@SpringBootApplication
@EnableScheduling
public class WarehouseRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseRentalApplication.class, args);
	}

}

