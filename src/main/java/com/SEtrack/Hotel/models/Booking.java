package com.SEtrack.Hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

/**
 * This class represents a model of a booking
 * @author cgilbers
 */
public class Booking {

    /**
     * This instantiates a new booking
     * @param bookingNr booking number
     * @param guest the guest who uses the room
     * @param room the room of the booking
     * @param startDate the start date of the booking
     * @param nrOfNights the number of nights the guest is staying
     * @param checkIn whether the guest has checked in yet
     */
    private int bookingNr;
    private Guest guest;
    private Room room;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private int nrOfNights;
    private boolean checkIn;



    public Booking() {

    }

    public Booking(int bookingNr, Guest guest, Room room, LocalDate startDate, int nrOfNights){

        this.bookingNr = bookingNr;
        this.guest = guest;
        //guest.setLastActiveDate(startDate);
        this.room = room;
        this.startDate = startDate;
        this.nrOfNights = nrOfNights;
    }

    /**
     * The booking number
     * @return int: the booking number
     */
    public int getBookingNr(){
        return this.bookingNr;
    }

    /**
     * The guest of the booking
     * @return a guest object
     */
    public Guest getGuest(){
        return this.guest;
    }

    /**
     * The room of the booking
     * @return a room object
     */
    public Room getRoom(){
        return this.room;
    }

    /**
     * The start date of the booking
     * @return a LocalDate object
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * The number of nights the guest has booked the room
     * @return int: the number of nights
     */
    public int getNrOfNights() {
        return nrOfNights;
    }

    /**
     * The guest has checked in or not
     * @return boolean: has the guest checked in
     */
    public boolean hasCheckedIn() {
        return checkIn;
    }

    /**
     * Sets checkIn to true
     */
    public void checkInGuest(){
        this.checkIn = true;
    }

    /**
     * Getter for Spring
     * @return whether a guest is checked in
     */
    public boolean isCheckIn() {
        return checkIn;
    }

    /**
     * Sets the checkin value.
     * @param checkIn whether the guest has already checked in
     */
    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
