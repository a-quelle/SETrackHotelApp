package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Guest;

import com.SEtrack.Hotel.repositories.GuestRepositoryIn;
import org.springframework.beans.factory.annotation.Autowired;
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
     * this function is meant to create guests
     * @param guest
     */
    @CrossOrigin
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addGuest(@RequestBody Guest guest) {
        guestRepositoryIn.save(guest);
    }

    /**
     * this function is meant to get all Guests
     * @return all the guests information
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Guest> getGuests() {
        return guestRepositoryIn.findAll();
    }

    /**
     * this function is meant to get all information of one guest
     * by insert the guestNR in the endpoint id
     * @param id = GuestNR
     * @return the guest information
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable long id) {
        return guestRepositoryIn.findOne(id);
    }

    /**
     * this function is meant to delete all information of one guest
     * @param guest
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void removeGuest(Guest guest) {
        guestRepositoryIn.delete(guest);
    }}