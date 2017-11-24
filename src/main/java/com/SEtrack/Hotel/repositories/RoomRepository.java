package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Room;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * RoomRepository class
 * Used as database, holds a list with the rooms and methods to modify the list
 * @author kgriffio
 * @author jcapel
 */
@Repository
public class RoomRepository {

    private List<Room> rooms = new ArrayList();

    public List<Room> getRooms(){
        return rooms;
    }

    /**
     * Gets a room by its room number
     * @param roomNumber room number of the room that we want to get.
     * @return Room object if found, null otherwise
     */
    // Gets a room by its room number
    public Room getRoom(long roomNumber){
        for(Room room : getRooms()) {
            if(room.getRoomNumber() == roomNumber){
                return room;
            }
        }
        return null;

    }

    /**
     * Adds room to the list.
     * @param newRoom Room to add
     * @return Returns true on success, false on duplicate.
     */
    // Adds a room to the list. Outputs a warning in case of a duplicate.
    public boolean addRoom(Room newRoom){
        int roomNumber = newRoom.getRoomNumber();
        for(Room room : rooms){
            if(room.getRoomNumber() == roomNumber){
                System.out.println("can't add room, room already exists");
                return false;
            }
        }

        rooms.add(newRoom);
        return true;
    }

    /**
     * Update a room by id
     * @param room room info to overwrite the room object with.
     */
    // Updates the room with corresponding ID and displays a warning if it isn't found.
    public void updateRoom(Room room) {
        for(int i = 0; i < getRooms().size(); i++) {
            if(getRooms().get(i).getRoomNumber() == room.getRoomNumber()) {
                // update the correct room
                getRooms().set(i, room);
                return;
            }
        }
        // Display a warning
        System.out.println("Could not find room to update");

    }



}