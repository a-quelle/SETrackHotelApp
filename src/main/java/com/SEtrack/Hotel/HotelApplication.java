package com.SEtrack.Hotel;

import com.SEtrack.Hotel.controllers.GuestController;
import com.SEtrack.Hotel.models.Guest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);

		GuestController gc = new GuestController();
		gc.addGuest(new Guest("Daniel", "Oliemans", "1059VM", 0));
		gc.addGuest(new Guest("Koen", "Griffioen", "7779OK", 1));
		gc.addGuest(new Guest("Joran", "Capel", "6696FU", 2));

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

	}
}
