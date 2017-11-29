package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Room;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository containing all the room objects.
 **/

public interface RoomRepositoryIn extends CrudRepository <Room, Long> {
}
