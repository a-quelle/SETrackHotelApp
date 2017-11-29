package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepositoryIn extends CrudRepository <Room, Long> {
    Room findById(Long id);
}
