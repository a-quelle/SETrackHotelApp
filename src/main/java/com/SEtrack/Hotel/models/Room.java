package com.SEtrack.Hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Room {

    private int roomNumber;
    private RoomType roomType = RoomType.Budget;
    private RoomSize roomSize = RoomSize.FiveSixPerson;
    private boolean available = false;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateAvailable;

    public Room(){

    }

    public Room(int roomNumber, RoomType roomType, RoomSize roomSize, LocalDate localDate){
        this.roomNumber = roomNumber;
        this.dateAvailable = localDate;
        this.roomSize = roomSize;
        this.roomType = roomType;
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
