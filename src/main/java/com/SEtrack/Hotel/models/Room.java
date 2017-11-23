package com.SEtrack.Hotel.models;

import com.SEtrack.Hotel.controllers.RoomSize;
import com.SEtrack.Hotel.controllers.RoomType;

import java.time.LocalDate;

public class Room {

    private int roomNumber;
    private RoomType roomType;
    private RoomSize roomSize;
    private boolean available;
    private LocalDate dateAvailable;

    public Room() {

    }

    public Room(int roomNumber, RoomType roomType, RoomSize roomSize, LocalDate dateAvailable){
        this.roomNumber = roomNumber;
        this.dateAvailable = dateAvailable;
        this.roomSize = roomSize;
        this.roomType = roomType;
        this.dateAvailable = dateAvailable;
    }

    public String getDetails(){
        String returnString = "";
        returnString += "Room " + roomNumber;
        returnString += "\nAvailable starting from: " + dateAvailable;
        returnString += "\nRoomType: " + roomType.name();
        returnString += "\nRoomSize: " + roomSize.name();
        return returnString;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomSize getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(RoomSize roomSize) {
        this.roomSize = roomSize;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(LocalDate dateAvailable) {
        this.dateAvailable = dateAvailable;
    }
}
