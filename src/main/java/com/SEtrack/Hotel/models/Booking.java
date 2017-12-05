package com.SEtrack.Hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * This class represents a model of a booking
 * @author cgilbers
 */

@Entity
public class Booking {

    /**
     * This instantiates a new booking
     * @param id booking identification number
     * @param guest the guest who uses the room
     * @param room the room of the booking
     * @param startDate the start date of the booking
     * @param endDate the end date of the booking
     * @param checkIn whether the guest has checked in yet
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @ManyToOne
    private Guest guest;
    @NotNull
    @ManyToOne
    private Room room;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull
    private boolean checkIn;


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
     * The end Date of the booking
     * @return a LocalDate object
     */
    public LocalDate getEndDate() {
        return endDate;
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

    public long getId() {
        return id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
