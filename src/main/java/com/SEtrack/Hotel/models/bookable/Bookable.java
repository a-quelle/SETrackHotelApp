package com.SEtrack.Hotel.models.bookable;

import com.SEtrack.Hotel.models.Room;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Bookable class which represents a time period where a room is not available for other bookings
 * @author cgilbers
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "bookable")
public class Bookable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne
    private Room room;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * The room of the booking
     * @return a room object
     */
    public Room getRoom(){
        return this.room;
    }

    /**
     * The start date of the booking
     * @return a LocalDate object
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * The end Date of the booking
     * @return a LocalDate object
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the start date
     * @param startDate LocalDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date
     * @param endDate LocalDate
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Set the room
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

}
