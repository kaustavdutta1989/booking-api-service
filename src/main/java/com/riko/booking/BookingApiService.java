package com.riko.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingApiService {

	public static void main(String[] args) {
		SpringApplication.run(BookingApiService.class, args);
	}

}
