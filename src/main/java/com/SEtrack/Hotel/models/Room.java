package com.SEtrack.Hotel.models;

import com.SEtrack.Hotel.controllers.RoomSize;
import com.SEtrack.Hotel.controllers.RoomType;

import java.time.LocalDate;

public class Room {

    private int bookingNumber;
    private RoomType roomType;
    private RoomSize roomSize;
    private boolean available;
    private LocalDate dateAvailable;

    public Room(int bookingNumber, RoomType roomType, RoomSize roomSize, LocalDate dateAvailable){
        this.dateAvailable = dateAvailable;
        this.roomSize = roomSize;
        this.roomType = roomType;
        this.dateAvailable = dateAvailable;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
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
