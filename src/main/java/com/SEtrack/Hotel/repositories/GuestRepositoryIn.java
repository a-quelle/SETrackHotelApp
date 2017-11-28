package com.SEtrack.Hotel.repositories;

import com.SEtrack.Hotel.models.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepositoryIn extends CrudRepository<Guest, Long>{

}