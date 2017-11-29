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


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
