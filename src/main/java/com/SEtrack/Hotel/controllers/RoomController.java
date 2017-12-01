package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Booking;
import com.SEtrack.Hotel.models.DateInterval;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.repositories.RoomRepositoryIn;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.SEtrack.Hotel.repositories.BookingRepositoryIn;

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
    private BookingRepositoryIn bookingRepositoryIn;


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
    public void addRoom(@RequestBody Room room){
        roomRepositoryIn.save(room);
    }

    /**
     * Update a certain room. Room in the list is replaced with the new one based on it's ID.
     * @param room Room to update
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public void updateRoom(@RequestBody Room room){
        if(room != null){
            Room roomFromTable = roomRepositoryIn.findOne(room.getId());
            if(roomFromTable != null){
                roomRepositoryIn.save(room);
            }
        }
    }

    /**
     * Removes room.
     * @param room Room to remove. NOTE: Does not work by ID, but by room object reference!!
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void removeRoom(Room room) {
        roomRepositoryIn.delete(room);
    }

    /**
     * Returns a list of all rooms
     * @return list of all rooms
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Room> getRooms(){
        return roomRepositoryIn.findAll();
    }

    /**
     * Get certain room by ID.
     * @param id ID of room to get.
     * @return Returns room object when found, NULL if not found.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable long id){
        return roomRepositoryIn.findOne(id);
    }

    /**
     * Returns all rooms that are free from startDate to endDate.
     * @param dates contains two LocalDate objects
     * @param bookingId id of the booking that is currently updated. For no booking, set as -1
     * @return Returns a list of Room objects.
     */
    @RequestMapping(value = {"available", "available/{bookingId}"}, method = RequestMethod.POST)
    public List<Room> getAvailableRooms (@RequestBody DateInterval dates, @PathVariable Optional<Long> bookingId) {
        if(dates.getStartDate() == null || dates.getEndDate() == null){
            return (List)roomRepositoryIn.findAll();
        }
        List<Room> list = new ArrayList<>();
        for(Room r : roomRepositoryIn.findAll()){
            boolean free = true;
            for (Booking b : bookingRepositoryIn.findAll()) {
                if (b.getRoom() == r) {

                    // if the booking is currently updated, ignore current booking in availability check
                    if(bookingId.isPresent() && bookingId.get().equals(b.getId())) {
                            System.out.println("updated booking is present");
                    }
                    else {
                        if (dates.getStartDate().isBefore(b.getEndDate()) && dates.getEndDate().isAfter(b.getStartDate()))
                            free = false;
                    }
                }
            }
            if (free)
                list.add(r);
        }
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getRoomNumber());
        }
        return list;
    }


}
