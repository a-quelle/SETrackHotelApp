package com.SEtrack.Hotel;

import com.SEtrack.Hotel.controllers.*;
import com.SEtrack.Hotel.models.Booking;
import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class containing the entry point for java.
 */

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
		
	}
}
