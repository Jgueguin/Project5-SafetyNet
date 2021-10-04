package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Fire Station Repository
 */

@Repository
public interface DtoFireStationsRepository extends CrudRepository<FireStations, Long> {


/**
     * find a fire Station by its number
     * @param station
     * @return
     */
     List<FireStations> findByStation(Integer station);



} // END

