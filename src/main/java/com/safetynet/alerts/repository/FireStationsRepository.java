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
     * Find a fire Station by its adress
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



// 2021-08-25

/*    *//**
     * Find station by address iterable.
     *
     * @param address the address
     * @return the list of fire station number covered at address
     *//*
    @Query("SELECT f.station FROM firestations f WHERE f.address = :address")
     Iterable<Integer> findStationByAddress(String address)*/


} // END

