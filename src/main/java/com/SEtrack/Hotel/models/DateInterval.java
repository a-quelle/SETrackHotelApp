package com.SEtrack.Hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * @author Anton Quelle
 * @author Daniel Oliemans
 * DateInterval class
 * POJO class to return LocalDate objects
 */
public class DateInterval {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * Gets the start date
     * @return startDate of a booking
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date
     * @param startDate change the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date
     * @return endDate of a booking
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date
     * @param endDate change the end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
