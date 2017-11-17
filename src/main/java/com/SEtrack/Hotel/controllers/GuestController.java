package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Guest;

import java.util.ArrayList;
import com.SEtrack.Hotel.models.Guest;

public class GuestController {

    private ArrayList<Guest> guestList= new ArrayList<>();

    public void addGuest (Guest guest) {

        guestList.add(guest);
    }

    public void findGuestFromNr (int guestNr) {

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
        if(searchResults.size() == 0){
            System.out.println("No guests containing the letters '" + s + "' have been found.");
        }
        return searchResults;
    }

    public Guest getGuestByGuestNumber(int id){
        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).getGuestNr() == id) {
                return guestList.get(i);
            }
        }
        System.out.println("No guest with ID: " + id + " found.");
        return null;
    }

    public ArrayList<Guest> getGuestByZipCode(String zipCode){
        ArrayList<Guest> searchResults = new ArrayList<>();
        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).getZipcode().contains(zipCode)) {
                searchResults.add(guestList.get(i));
            }
        }
        if(searchResults.size() == 0){
            System.out.println("No guests with a zipcode containing '" + zipCode + "' have been found.");
        }
        return searchResults;
    }
}
