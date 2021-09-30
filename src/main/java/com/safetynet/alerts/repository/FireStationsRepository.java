package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Fire Station Repository
 */

@Repository
public interface FireStationsRepository extends CrudRepository<FireStations, Long> {

    // 2021-08-26
    /**
     * Find a fire Station by its id
     * @param id the id
     * @return the firestations
     */
    Optional<FireStations> findById(Long id);


    // 2021-08-26

/**
     * Find a fire Station by its address
     * @param address the address
     * @return the firestations
     */

    FireStations findByAddress(String address);


/**
     * Delete a firestation by an adress
     * @param address
     * @return
     */
    FireStations deleteByAddress(String address);




    // 2021-09-16
    // pour DTO

/**
     * find a fire Station by its number
     * @param station
     * @return
     */
     List<FireStations> findByStation(Integer station);



} // END

