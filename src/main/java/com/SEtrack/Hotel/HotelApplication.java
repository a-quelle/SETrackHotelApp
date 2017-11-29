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

		/*
		GuestController gc = new GuestController();
		RoomController rc = new RoomController();
		BookingController bc = new BookingController();

		gc.addGuest(new Guest("Daniel", "Oliemans", "1059VM", 0));
		gc.addGuest(new Guest("Koen", "Griffioen", "7779OK", 1));
		gc.addGuest(new Guest("Joran", "Capel", "6696FU", 2));

		rc.addRoom(new Room(1, RoomType.Normal, RoomSize.FiveSixPerson,
				LocalDate.of(2017, Month.NOVEMBER, 17)));
		rc.addRoom(new Room(2, RoomType.Budget, RoomSize.FiveSixPerson,
				LocalDate.of(2017, Month.DECEMBER, 19)));
		rc.addRoom(new Room(3, RoomType.Luxurious, RoomSize.OnePerson,
				LocalDate.of(2017, Month.DECEMBER, 20)));

		System.out.println("");

		for(Room r : rc.getRooms()){
			System.out.println(r.getDetails());
			System.out.println("");
		}

		bc.addBooking(new Booking(1, gc.getGuestList().get(0), rc.getRooms().get(0), LocalDate.of(2017, Month.NOVEMBER, 17), 2));
		System.out.println("");

		System.out.println("Enter a name: ");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		for(int i = 0; i < gc.getGuestByName(name).size(); i++) {
			System.out.println(gc.getGuestByName(name).get(i).getFullName());
		}

		System.out.println("Enter a Zipcode: ");
		String zipcode = scanner.nextLine();

		for(int i = 0; i < gc.getGuestByZipCode(zipcode).size(); i++){
			System.out.println(gc.getGuestByZipCode(zipcode).get(i).getFullName() + " - Zipcode: " + gc.getGuestByZipCode(zipcode).get(i).getZipcode());
		}

		System.out.println("Enter a guest ID: ");
		int guestID = scanner.nextInt();

		while(gc.getGuestByGuestNumber(guestID) == null){
			System.out.println("Please re-enter a valid guest ID.");
			guestID = scanner.nextInt();
		}

		System.out.println(gc.getGuestByGuestNumber(guestID).getFullName());

		scanner.close();
		*/
	}
}
