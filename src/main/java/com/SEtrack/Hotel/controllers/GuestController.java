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

    private ArrayList<Guest> guestList= new ArrayList<>();

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addGuest (@RequestBody Guest guest) {
        int id = generateGuestNr();
        guest.setGuestNr(id);
        guestList.add(guest);
        System.out.println("Added guest: " + guest.getFullName());

    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ArrayList<Guest> getGuests(){
        return guestList;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable int id){
        for(Guest guest : guestList){
            if(guest.getGuestNr() == id){
               return guest;
            }
        }
        System.out.println("Can't find guest by ID: " + id);
          return null;
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
