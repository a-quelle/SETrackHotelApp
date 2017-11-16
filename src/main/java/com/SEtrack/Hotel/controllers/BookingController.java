package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Booking;
import java.util.ArrayList;

/**
 * represents the controller for bookings
 * @author mstienst
 */
public class BookingController {

    private ArrayList bookingList;

    /**
     * constructor of booking controller
     */
    public BookingController (){
        this.bookingList = new ArrayList<Booking>();
    }

    /**
     * method to add booking to bookingList
     * @param booking Booking: the booking object
     */
    public void addBooking (Booking booking){

        bookingList.add(booking);
        System.out.println("Boeking is toegevoegd. Boekingnummer: " + booking.getBookingNr());
    }

}
