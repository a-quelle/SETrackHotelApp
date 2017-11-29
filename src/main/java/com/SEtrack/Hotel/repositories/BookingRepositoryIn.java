package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * CrudRepository containing all the booking objects.
 **/

@Repository
public interface BookingRepositoryIn extends CrudRepository<Booking, Long>{
}
