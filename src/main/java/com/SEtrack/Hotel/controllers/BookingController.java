package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.HotelApplication;
import com.SEtrack.Hotel.models.Booking;
import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.repositories.BookingRepository;
import com.SEtrack.Hotel.repositories.GuestRepository;
import com.SEtrack.Hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the controller for bookings
 * @author aquelle
 * @author mstienst
 */


//The rest controller for the bookings.
@RestController
@RequestMapping("api/hotel/booking")
public class BookingController {

    //It is connected to the booking repository, which contains all the bookings.
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    /**
     * Gets list of bookings
     * @return The list of bookings
     */
    // Give a list of bookings to the website following a webrequest.
    @RequestMapping(value="all", method= RequestMethod.GET)
    public List<Booking> index () {
        return bookingRepository.getBookingList();
    }

    /**
     * Add the booking to the repository. Since the server receives copies of existing
     * Room and Guest objects, these have to be replaced by their originals.
     * @param bookingToAdd Booking to add. Contains the id of the guest and the id of the room.
     */
    // Add a booking to the controller through a webrequest.
    @RequestMapping(value="add", method=RequestMethod.POST)
    public void add (@RequestBody Booking bookingToAdd) {
        int roomNumber = bookingToAdd.getRoom().getRoomNumber();
        int guestNumber = bookingToAdd.getGuest().getGuestNr();
        // Replace the guest and room copies by their originals.
        Guest guest = guestRepository.getGuest(guestNumber);
        Room room = roomRepository.getRoom(roomNumber);
        if(guest == null) {
            // Print out warning if this fails
            System.out.println("We could not find the guest we were looking for..");
        }
        else if(room == null){
            // Print out warning if this fails
            System.out.println("We could not find the room we were looking for..");
        }
        else {
            bookingToAdd.setGuest(guest);
            bookingToAdd.setRoom(room);
            bookingRepository.addBooking(bookingToAdd);
        }
    }




}
