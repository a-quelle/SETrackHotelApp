package com.SEtrack.Hotel.models.bookable;

import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.bookable.Bookable;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * This class represents a modal of a time period where a room is under maintenance and not available
 * @author cgilbers
 */
@Entity
@Table(name = "maintenance_bookable")
public class Maintenance extends Bookable {

    /**
     * The reason for maintenance
     */
    @NotNull
    private String reason;
    /**
     * Optional extra information about the maintenance reason
     */
    private String message;

    public Maintenance(){}

    public Maintenance(Room room, LocalDate startDate, LocalDate endDate, String reason, String message) {
        super(room, startDate, endDate);
        this.reason = reason;
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
