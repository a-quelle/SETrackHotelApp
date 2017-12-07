package com.SEtrack.Hotel.repositories.bookable;

import com.SEtrack.Hotel.models.bookable.Bookable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * A base repository for the entity bookable and its subclasses
 * @author cgilbers
 */
@NoRepositoryBean
public interface BookableBaseRepository<T extends Bookable> extends CrudRepository<T, Long>{

}
