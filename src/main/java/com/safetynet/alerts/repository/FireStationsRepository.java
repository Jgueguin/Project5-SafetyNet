package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Fire Station Repository
 */

@Repository
public interface FireStationsRepository extends CrudRepository<FireStations, Long> {


}

