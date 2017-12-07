package com.SEtrack.Hotel.controllers;


import com.SEtrack.Hotel.exceptions.ForbiddenException;
import com.SEtrack.Hotel.exceptions.NotFoundException;
import com.SEtrack.Hotel.models.bookable.Booking;
import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.repositories.bookable.BookingRepository;
import com.SEtrack.Hotel.repositories.GuestRepositoryIn;
import com.SEtrack.Hotel.repositories.RoomRepositoryIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * represents the controller for bookings
 * @author aquelle
 * @author mstienst
 */
@RestController
@RequestMapping("api/hotel/booking")
public class BookingController {

    //It is connected to the repositories.
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepositoryIn roomRepositoryIn;

    @Autowired
    private GuestRepositoryIn guestRepositoryIn;

    /**
     * Gets list of bookings
     * @return The list of bookings
     */
    // Give a list of bookings to the website following a webrequest.
    @RequestMapping(value="all", method= RequestMethod.GET)
    public Iterable<Booking> index () {
        return bookingRepository.findAll();
    }

    /**
     * Add the booking to the repository. Since the server receives copies of existing
     * Room and Guest objects, these have to be replaced by their originals.
     * @param bookingToAdd Booking to add. Contains the id of the guest and the id of the room.
     */
    // Add a booking to the controller through a webrequest.
    @RequestMapping(value="add", method=RequestMethod.POST)
    public void add (@RequestBody Booking bookingToAdd) {
        long roomId = bookingToAdd.getRoom().getId();
        long guestId = bookingToAdd.getGuest().getId();
        // Replace the guest and room copies by their originals.
        Guest guest = guestRepositoryIn.findOne(guestId);
        Room room = roomRepositoryIn.findOne(roomId);
        if (bookingToAdd.getEndDate().isBefore(bookingToAdd.getStartDate())) {
            throw new ForbiddenException();
        }
        if(guest == null) {
            // Throw notfoundexception if this fails
            throw new NotFoundException();
        }
        else if(room == null){
            // Print out warning if this fails
            throw new NotFoundException();
        }
        else {
            bookingToAdd.setGuest(guest);
            bookingToAdd.setRoom(room);
            bookingRepository.save(bookingToAdd);
        }
    }

    /**
     * Update function for Bookings.
     * @param booking Checks if a guest with the same ID already exists in the repository. If so, it overwrites this guest.
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public void updateGuest(@RequestBody Booking booking){
        if(booking != null){
            Booking bookingFromTable = bookingRepository.findOne(booking.getId());
            if(bookingFromTable != null){

                if (booking.getEndDate().isBefore(booking.getStartDate())) {
                    throw new ForbiddenException();
                }
                 bookingRepository.save(booking);

            }
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void deleteGuest(@RequestBody Booking booking){
        if(booking != null) {
            bookingRepository.delete(booking);
        }
    }


}
