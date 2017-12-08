package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.bookable.Bookable;
import com.SEtrack.Hotel.models.bookable.Booking;
import com.SEtrack.Hotel.models.bookable.Maintenance;
import com.SEtrack.Hotel.repositories.bookable.BookableRepository;
import com.SEtrack.Hotel.repositories.bookable.BookingRepository;
import com.SEtrack.Hotel.repositories.bookable.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

/**
 * The controller for bookables
 * @author cgilbers
 */
@RestController
@RequestMapping("api/hotel/maintenance")
public class MaintenanceController {

    @Autowired
    private BookableRepository bookableRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    /**
     * Gets a list of all maintenance bookings
     * @return a list of maintenance bookings
     */
    @RequestMapping(value ="all", method = RequestMethod.GET)
    public Iterable<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }

    /**
     * Adds a maintenance booking to the repository
     * @return the added Maintenance object
     */
    @RequestMapping(value ="add", method = RequestMethod.POST)
    public Maintenance add(@RequestBody Maintenance maintenance){
        maintenanceRepository.save(maintenance);
        return maintenance;
    }

}
