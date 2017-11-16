package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Room;

import java.util.ArrayList;

public class RoomController {

    ArrayList<Room> rooms;

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void updateRoom(Room room){
        Room seekRoom;
        rooms.get(rooms.indexOf(room));

    }

}
