package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class GuestRepository {
    private List<Guest> guests = new ArrayList<>();

    /**
     * Generate a random guestNr and check if iID allready exists.
     * @return
     */
    private int generateGuestNr() {
        boolean occurs = false;
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
        for (Guest g : guests) {
            if (randomNum == g.getGuestNr())
                occurs = true;
        }
        if (occurs)
            randomNum = generateGuestNr();
        return randomNum;
    }

    public void addGuest(@RequestBody Guest guest) {
        int id = generateGuestNr();
        guest.setGuestNr(id);
        guests.add(guest);
        System.out.println("Added guest: " + guest.getFullName());
    }

    public Guest getGuest(@PathVariable int id) {
        for (Guest guest : guests) {
            if (guest.getGuestNr() == id) {
                return guest;
            }
        }
        System.out.println("Can't find guest by ID: " + id);
        return null;
    }

    public List<Guest> getGuests() {
        return guests;
    }
}
