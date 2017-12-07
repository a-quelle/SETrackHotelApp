package com.SEtrack.Hotel.repositories.bookable;

import com.SEtrack.Hotel.models.bookable.Bookable;

import javax.transaction.Transactional;

/**
 * A repository for all bookable entities
 * @author cgilbers
 */
@Transactional
public interface BookableRepository extends BookableBaseRepository<Bookable>{
}
