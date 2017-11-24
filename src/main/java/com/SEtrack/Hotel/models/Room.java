package com.SEtrack.Hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * This class represents a model of a room
 * @author jcapel
 * @author kgriffioen
 */
public class Room {

    // Definition of all instance variables
    private int roomNumber;
    private RoomType roomType = RoomType.Budget;
    private RoomSize roomSize = RoomSize.FiveSixPerson;
    private boolean available = false;

    // Calls JSonformat so that local date becomes a string
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateAvailable;

    /**
     * Empty constructor for Springboot.
     */
    public Room(){

    }

    /**
     * Constructor for room.
     * @param roomNumber
     * @param roomType
     * @param roomSize
     * @param localDate
     */
    public Room(int roomNumber, RoomType roomType, RoomSize roomSize, LocalDate localDate){
        this.roomNumber = roomNumber;
        this.dateAvailable = localDate;
        this.roomSize = roomSize;
        this.roomType = roomType;
    }

    // Getters and Setters for all variables

    /**
     * Get the roomnumber
     * @return roomnumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Set the roomnumber
     * @param roomNumber
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Get the roomtype
     * @return roomtype
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Set roomtype
     * @param roomType
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Get roomsize
     * @return roomsize
     */
    public RoomSize getRoomSize() {
        return roomSize;
    }

    /**
     * Set roomsize
     * @param roomSize
     */
    public void setRoomSize(RoomSize roomSize) {
        this.roomSize = roomSize;
    }

    /**
     * Checks availability for the room
     * @return isAvailable
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Set the boolean setAvailable
     * @param available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Get localdate
     * @return localdate
     */
    public LocalDate getDateAvailable() {
        return dateAvailable;
    }

    /**
     * Set dateavailable
     * @param dateAvailable
     */
    public void setDateAvailable(LocalDate dateAvailable) {
        this.dateAvailable = dateAvailable;
    }
}
