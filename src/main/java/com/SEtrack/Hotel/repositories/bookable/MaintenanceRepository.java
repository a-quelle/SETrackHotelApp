package com.SEtrack.Hotel.repositories.bookable;

import com.SEtrack.Hotel.models.bookable.Maintenance;

import javax.transaction.Transactional;

/**
 * Repository for the maintenance entity
 * @author cgilbers
 */
@Transactional
public interface MaintenanceRepository extends BookableBaseRepository<Maintenance>{
}
