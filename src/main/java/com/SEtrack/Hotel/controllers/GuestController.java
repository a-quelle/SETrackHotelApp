package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Guest;

import java.util.ArrayList;
import com.SEtrack.Hotel.models.Guest;

public class GuestController {

    private ArrayList<Guest> guestList= new ArrayList<>();

    public void addGuest (...) {


    }


    public void removeGuest(int guestNr) {

    }

    public GuestController(){
        guestList = new ArrayList<>();
    }

    public ArrayList<Guest> getGuestList(){
        return getGuestList();
    }

    public ArrayList<Guest> getGuestByName(String s) {
        ArrayList<Guest> searchResults = new ArrayList<>();
        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).getFullName().contains(s)) {
                searchResults.add(guestList.get(i));
            }
        }
        return searchResults;
    }
}
