package com.SEtrack.Hotel.models.bookable;

import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.bookable.Bookable;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This class represents a model of a booking
 * @author cgilbers
 */
@Entity
@Table(name = "booking_bookable")
public class Booking extends Bookable {

    @NotNull
    @ManyToOne
    private Guest guest;
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


}
