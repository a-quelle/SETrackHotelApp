package com.SEtrack.Hotel.controllers;

import java.util.ArrayList;
import com.SEtrack.Hotel.models.Guest;

public class GuestController {

    ArrayList<Guest> guestList= new ArrayList<>();

    public void addGuest (Guest guest) {

        guestList.add(guest);
    }

    public void findGuestFromNr (int guestNr) {

    }

    public void removeGuest(int guestNr) {


    }
}
