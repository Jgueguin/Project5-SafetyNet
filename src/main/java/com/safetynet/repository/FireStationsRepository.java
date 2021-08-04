package com.safetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.model.FireStations;

/**
 * Fire Station Repository
 */

@Repository
public interface FireStationsRepository extends CrudRepository<FireStations, Long> {

}

