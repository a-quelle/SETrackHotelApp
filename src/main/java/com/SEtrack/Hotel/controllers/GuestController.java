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
        return guestList;
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
