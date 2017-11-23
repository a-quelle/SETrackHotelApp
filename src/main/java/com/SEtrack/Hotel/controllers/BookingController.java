package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.HotelApplication;
import com.SEtrack.Hotel.models.Booking;
import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the controller for bookings
 * @author mstienst
 */


//The rest controller for the bookings.
@RestController
@RequestMapping("")
public class BookingController {

    //It is connected to the booking repository, which contains all the bookings.
    @Autowired
    BookingRepository bookingRepository;

    // Give a list of bookings to the website following a webrequest.

    @RequestMapping(value="all", method= RequestMethod.GET)
    public List<Booking> index () {
        return bookingRepository.getBookingList();
    }

    @RequestMapping(value="guests", method= RequestMethod.GET)
    public List<Guest> testGuests () {
        return HotelApplication.testGuestList;
    }

    @RequestMapping(value="rooms", method= RequestMethod.GET)
    public List<Room> testRooms () {
        return HotelApplication.testRoomList;
    }

    // Add a booking to the controller through a webrequest.

    @RequestMapping(value="add", method=RequestMethod.POST)
    public void add (@RequestBody Booking bookingToAdd) {
        int roomNumber = bookingToAdd.getRoom().getRoomNumber();
        int guestNumber = bookingToAdd.getGuest().getGuestNr();
        // GEt room with <Roomnumber>
        // Get Guest with <Guestnumber>
        bookingRepository.addBooking(bookingToAdd);
    }

}
