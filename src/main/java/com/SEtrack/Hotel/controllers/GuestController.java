package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Guest;
import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import com.SEtrack.Hotel.models.Guest;

public class GuestController {

    private ArrayList<Guest> guestList= new ArrayList<>();

    public void addGuest (String firstName, String lastName, String streetName, String zipCode, String city, String country
            ,int houseNumber, String phoneNumber, String emailAddress) {

        Guest g= new Guest( generateGuestNr(),firstName,lastName,streetName,zipCode,city,country
                ,houseNumber,phoneNumber,emailAddress);
        guestList.add(g);

    }

    private int generateGuestNr() {
        boolean occurs=false;
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
        for(Guest g : guestList) {
            if (randomNum==g.getGuestNr())
                occurs=true;
        }
        if (occurs)
            randomNum= generateGuestNr();
        return randomNum;
    }

    public void removeGuest(Guest guest) {
        boolean check=false;
        for (Guest g : guestList) {
            if (g==guest) {
                guestList.remove(guest);
                check = true;
            }
        }
        if (!check)
            System.out.println("The guest was not found in the guest list.");
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
