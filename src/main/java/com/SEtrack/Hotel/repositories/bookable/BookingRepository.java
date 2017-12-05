package com.SEtrack.Hotel.repositories.bookable;

import com.SEtrack.Hotel.models.bookable.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * CrudRepository containing all the booking objects.
 * @author cgilbers
 **/

@Transactional
public interface BookingRepository extends BookableBaseRepository<Booking>{
}
