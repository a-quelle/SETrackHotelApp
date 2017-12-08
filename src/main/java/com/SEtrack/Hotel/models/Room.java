package com.SEtrack.Hotel.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This class represents a model of a room
 * @author jcapel
 * @author kgriffioen
 */

@Entity
public class Room {

    /**
     * @param id Unique identifyer of the Room object
     * @param roomNumber the number of the room in the hotel
     * @param roomType the type of room from an Enum
     * @param roomSize the number of guests that fit in the room from an Enum
     */
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

    public Room(long id, String roomNumber, RoomType roomType, RoomSize roomSize) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomSize = roomSize;
    }

    public Room (){}

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
