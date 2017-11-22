package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//This is the RoomController class. Has an ArrayList holding the rooms. Methods: Add, remove and update the room.

@RestController
@RequestMapping("api/hotel/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    public RoomController(){
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    //Add room to the ArrayList
    public void addRoom(@RequestBody Room room){
        roomRepository.addRoom(room);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    //Updates an excisting room
    public void updateRoom(Room room){
        roomRepository.updateRoom(room);
    }

    //Remove room from the ArrayList
    public void removeRoom(Room room) {
        roomRepository.getRooms().remove(room);
    }


    //Returns an ArrayList containing all rooms
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Room> getRooms(){
        return roomRepository.getRooms();
    }
    //Returns an ArrayList containing all rooms
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable long id){
        return roomRepository.getRoom(id);
    }

    //Returns an ArrayList containing available rooms
    public ArrayList<Room> getFreeRooms(){
        ArrayList<Room> returnArray = new ArrayList<Room>();
        for(Room room: roomRepository.getRooms()){
            if(room.isAvailable()){
                returnArray.add(room);
            }
        }

        return returnArray;
    }

}
