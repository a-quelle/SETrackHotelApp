package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CrudRepository containing all the guests objects.
 **/

@Repository
public interface GuestRepositoryIn extends CrudRepository<Guest, Long>{
}
