package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Room;

import java.util.ArrayList;

//This is the RoomController class. Has an ArrayList holding the rooms. Methods: Add, remove and update the room.

public class RoomController {

    ArrayList<Room> rooms;

    public RoomController(){
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room){
        int roomnumber = room.getRoomNumber();
        for(Room newroom :rooms){
            if(room.getRoomNumber() == roomnumber){
                System.out.println("can't add room, room already exists");
                return;
            }
        }

        rooms.add(room);
    }

    public void updateRoom(Room room){
        for(int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getRoomNumber() == room.getRoomNumber()) {
                // update the correct room
                rooms.set(i, room);
            }

        }
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public ArrayList<Room> getFreeRooms(){
        ArrayList<Room> returnArray = new ArrayList<Room>();
        for(Room room: rooms){
            if(room.isAvailable()){
                returnArray.add(room);
            }
        }

        return returnArray;
    }

}
