package com.vvg.krivanek.warehouserental;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@SpringBootApplication(scanBasePackages = "com.vvg.krivanek")
@EnableScheduling
public class WarehouseRentalApplication {

	public static void main(String[] args) {
		System.out.println(TimeZone.getDefault());
		SpringApplication.run(WarehouseRentalApplication.class, args);
		
	}

}

