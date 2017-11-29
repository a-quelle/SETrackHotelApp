package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Booking;
import com.SEtrack.Hotel.models.DateInterval;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.repositories.RoomRepository;
import com.SEtrack.Hotel.repositories.RoomRepositoryIn;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.SEtrack.Hotel.repositories.BookingRepository;

//This is the RoomController class. Has an ArrayList holding the rooms. Methods: Add, remove and update the room.

/**
 * @author Joran Capel
 * @author Koen Griffioen
 * RoomController Class
 * Controller with endpoints for the room class.
 */

@RestController
@RequestMapping("api/hotel/room")
public class RoomController {

    @Autowired
    private RoomRepositoryIn roomRepositoryIn;

    @Autowired
    private BookingRepository bookingRepository;


    /**
     * RoomController constructor.
     */
    public RoomController(){
    }

    /**
     * Add a room to the repository.
     * @param room Room to add to the list
     * @return Returns true in case of success, false in case of failure.
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    //Add room to the ArrayList
    public void addRoom(@RequestBody Room room){
        roomRepositoryIn.save(room);
    }

    /**
     * Update a certain room. Room in the list is replaced with the new one based on it's ID.
     * @param room Room to update
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    //Updates an existing room
    public void updateRoom(Room room){
        roomRepositoryIn.save(room);
    }

    /**
     * Removes room.
     * @param room Room to remove. NOTE: Does not work by ID, but by room object reference!!
     */
    //Remove room from repository
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void removeRoom(Room room) {
        roomRepositoryIn.delete(room);
    }

    /**
     * Returns a list of all rooms
     * @return list of all rooms
     */
    //Returns an ArrayList containing all rooms
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Room> getRooms(){
        return roomRepositoryIn.findAll();
    }

    /**
     * Get certain room by ID.
     * @param id ID of room to get.
     * @return Returns room object when found, NULL if not found.
     */
    //Returns an ArrayList containing all rooms
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable long id){
        return roomRepositoryIn.findOne(id);
    }

    /**
     * Returns all rooms that are free from startDate to endDate.
     * @param dates contains two LocalDate objects
     * @return Returns a list of Room objects.
     */

    @RequestMapping(value = "available", method = RequestMethod.POST)
    public List<Room> getAvailableRooms (DateInterval dates) {
        List<Room> list = new ArrayList<>();
        for(Room r : roomRepositoryIn.findAll()){
            boolean free = true;
            for (Booking b : bookingRepository.getBookingList()) {
                if (b.getRoom() == r) {
                    if (dates.getStartDate().isBefore(b.getEndDate()) && dates.getEndDate().isAfter(b.getStartDate()))
                        free = false;
                }

            }
            if (free)
                list.add(r);
        }
        return list;
    }

}
