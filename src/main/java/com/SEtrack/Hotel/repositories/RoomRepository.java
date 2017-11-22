package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Room;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository {

    private List<Room> rooms = new ArrayList();

    public List<Room> getRooms(){
        return rooms;
    }

    // Gets a room by its room number
    public Room getRoom(long roomNumber){
        for(Room room : getRooms()) {
            if(room.getRoomNumber() == roomNumber){
                return room;
            }
        }
        return null;

    }

    public void addRoom(Room room){
        int roomnumber = room.getRoomNumber();
        for(Room newRoom : getRooms()){
            if(room.getRoomNumber() == roomnumber){
                System.out.println("can't add room, room already exists");
                return;
            }
        }

        getRooms().add(room);
    }


    public void updateRoom(Room room) {
        for(int i = 0; i < getRooms().size(); i++) {
            if(getRooms().get(i).getRoomNumber() == room.getRoomNumber()) {
                // update the correct room
                getRooms().set(i, room);
            }

        }
    }



}
