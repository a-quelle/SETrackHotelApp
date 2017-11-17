package com.SEtrack.Hotel;

import com.SEtrack.Hotel.controllers.GuestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {

		SpringApplication.run(HotelApplication.class, args);

		new GuestController();

	}
}
