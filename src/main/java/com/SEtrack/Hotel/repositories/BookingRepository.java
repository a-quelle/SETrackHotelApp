package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Booking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {
    private List<Booking> bookingList = new ArrayList<>();

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void addBooking(Booking b) {
        bookingList.add(b);
    }

    public void removeBooking(Booking b) {
        bookingList.remove(b);
    }

}
