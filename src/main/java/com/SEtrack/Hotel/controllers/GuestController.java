package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.exceptions.NotFoundException;
import com.SEtrack.Hotel.models.Guest;

import com.SEtrack.Hotel.repositories.GuestRepositoryIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

/**
 * represents the controller for guests
 */

@RestController
@RequestMapping("api/hotel/guests/")
public class GuestController {

    @Autowired
    private GuestRepositoryIn guestRepositoryIn;

    /**
     * Update function for guests.
     *
     * @param guest The new guest to put at a certain place. Keep in mind that the guest with this ID
     *              will be overwritten.
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Guest updateGuest(@RequestBody Guest guest) {
        if (guest != null) {
            Guest guestFromTable = guestRepositoryIn.findOne(guest.getId());
            if (guestFromTable != null) {
                return guestRepositoryIn.save(guest);
            }
        }
        // We did not find any guest.. throw exception
        throw new NotFoundException();
    }

    /**
     * this function is meant to create guests
     *
     * @param guest
     */
    @CrossOrigin
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Guest addGuest(@RequestBody Guest guest) {
        return guestRepositoryIn.save(guest);
    }

    /**
     * this function is meant to get all Guests
     *
     * @return all the guests information
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Guest> getGuests() {
        return guestRepositoryIn.findAll();
    }

    /**
     * this function is meant to get all information of one guest
     * by insert the guestNR in the endpoint id
     *
     * @param id = GuestNR
     * @return the guest information
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable long id) {
        return guestRepositoryIn.findOne(id);
    }

    /**
     * this function is meant to delete all information of one guest
     *
     * @param guest
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void removeGuest(@RequestBody Guest guest) {
        if(guest != null) {
            // First try to find the guest
            Guest db_guest = guestRepositoryIn.findOne(guest.getId());
            if(db_guest != null) {
                guestRepositoryIn.delete(guest);
            }
        }
        throw new NotFoundException();
    }
}