package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Booking;
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

@RestController
@RequestMapping("")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @RequestMapping(value="all", method= RequestMethod.GET)
    public List<Booking> index () {
        return bookingRepository.getBookingList();
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public void add (@RequestBody Booking bookingToAdd) {
        bookingRepository.addBooking(bookingToAdd);
    }

}
