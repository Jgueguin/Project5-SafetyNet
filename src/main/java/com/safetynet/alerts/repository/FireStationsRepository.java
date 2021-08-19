package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Fire Station Repository
 */

@Repository
public interface FireStationsRepository extends CrudRepository<FireStations, Long> {

    //2021-08-19

    /**
     * Find a firestation by adress
     * @param address the adress
     * @return the firestations
     */
    FireStations findByAddress(String address);


    /**
     * Delete a firestation by an adress
     * @param address
     * @return
     */
    Persons deleteByAddress(String address);



}

