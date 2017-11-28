package com.SEtrack.Hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * This class represents a model of a room
 * @author jcapel
 * @author kgriffioen
 */

@Entity
public class Room {

    // Definition of all instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotNull
    @Column(unique=true)
    private String roomNumber;
    @NotNull
    private RoomType roomType = RoomType.Budget;
    @NotNull
    private RoomSize roomSize = RoomSize.FiveSixPerson;
    @NotNull
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
    public Room(String roomNumber, RoomType roomType, RoomSize roomSize, LocalDate localDate, long id){

        this.roomNumber = roomNumber;
        this.dateAvailable = localDate;
        this.roomSize = roomSize;
        this.roomType = roomType;
        this.id = id;
    }

    // Getters and Setters for all variables

    /**
     * Get the roomnumber
     * @return roomnumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Set the roomnumber
     * @param roomNumber
     */
    public void setRoomNumber(String roomNumber) {
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

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
