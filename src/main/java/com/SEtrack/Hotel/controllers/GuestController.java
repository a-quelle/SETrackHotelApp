package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Guest;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import com.SEtrack.Hotel.models.Guest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/guests/")
public class GuestController {

    private ArrayList<Guest> guestList = new ArrayList<>();

    /**
     * this function is meant to create guests
     * @param guest
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addGuest(@RequestBody Guest guest) {
        int id = generateGuestNr();
        guest.setGuestNr(id);
        guestList.add(guest);
        System.out.println("Added guest: " + guest.getFullName());

    }

    /**
     * this function is meant to get all Guests
     * @return all the guests information
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ArrayList<Guest> getGuests() {
        return guestList;
    }

    /**
     * this function is meant to get all information of one guest
     * by insert the guestNR in the endpoint id
     * @param id = GuestNR
     * @return the guest information
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable int id) {
        for (Guest guest : guestList) {
            if (guest.getGuestNr() == id) {
                return guest;
            }
        }
        System.out.println("Can't find guest by ID: " + id);
        return null;
    }

    /**
     * Generate a random guestNr and check if iID allready exists.
     * @return
     */
    private int generateGuestNr() {
        boolean occurs = false;
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
        for (Guest g : guestList) {
            if (randomNum == g.getGuestNr())
                occurs = true;
        }
        if (occurs)
            randomNum = generateGuestNr();
        return randomNum;
    }

    /**
     * Constructer GuestController
     */
    public GuestController() {
        guestList = new ArrayList<>();
    }

    //TODO: add a remove guest function
}