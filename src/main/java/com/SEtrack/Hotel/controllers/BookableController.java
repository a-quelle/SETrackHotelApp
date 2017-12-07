package com.SEtrack.Hotel.controllers;

import com.SEtrack.Hotel.models.bookable.Bookable;
import com.SEtrack.Hotel.models.bookable.Booking;
import com.SEtrack.Hotel.models.bookable.Maintenance;
import com.SEtrack.Hotel.repositories.bookable.BookableRepository;
import com.SEtrack.Hotel.repositories.bookable.BookingRepository;
import com.SEtrack.Hotel.repositories.bookable.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for bookables
 * @author cgilbers
 */
@RestController
@RequestMapping("api/hotel/bookable")
public class BookableController {

    @Autowired
    private BookableRepository bookableRepository;

    /**
     * Gets a list of all bookables
     * @return a list of Bookables
     */
    @RequestMapping(value="all", method= RequestMethod.GET)
    public Iterable<Bookable> index () {
        return bookableRepository.findAll();
    }

}
