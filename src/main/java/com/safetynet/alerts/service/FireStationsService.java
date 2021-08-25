package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.repository.FireStationsRepository;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Fire Stations Service
 */

@Data
@Service
public class FireStationsService {
    @Autowired
    private FireStationsRepository fireStationsRepository;

    /**
     * Select a choosen Fire Station between all
     * @param id : parameter to choose the fire station
     * @return : the information for the choosen fire station
     */
    public Optional<FireStations> getFireStations(final Long id) {

        return fireStationsRepository.findById(id);
    }

    /**
     * Select all the fire stations
     * @return: the informations for all the firestations
     */
    public Iterable<FireStations> getFireStations() {

        return fireStationsRepository.findAll();
    }

    /**
     * delete a choosen firestation
     * @param id : parameter to choose the fire station to delete
     */
    public void deleteFirestations(final Long id) {

        fireStationsRepository.deleteById(id);


    }

    /**
     * Save a new fire station
     * @param firestations
     * @return : save the new fire station into Repository
     */
    public FireStations saveFirestations(FireStations firestations) {

        FireStations savedFirestations = fireStationsRepository.save(firestations);
        return firestations;
    }


    /**
     * Save all fire stations.
     *
     * @param firestations the firestations
     * @return list of fire stations saved
     */
    public Iterable<FireStations> saveAll(Iterable<FireStations> firestations) {
        return fireStationsRepository.saveAll(firestations);
    }


    /**
     * * Update a fire station.( Put )
     *
     * @param id
     * @param fireStations the firestations
     * @return update the fire stations
     */
    public FireStations updateFireStationsById (final Long id, FireStations fireStations) {

        fireStationsRepository.findById(id);

        return fireStationsRepository.save(fireStations);
    }


    //2021-08-19 update

    /**
     *
     * @param fireStations
     * @return
     */
    public FireStations saveUpdated(FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }

    /**
     * Update a firestation ( Put )by its adress
     *
     * @param fireStationsBody
     * @param firesStationsToUpdate
     * @return
     */
    public FireStations updateFireStationsByAddress(FireStations fireStationsBody, FireStations firesStationsToUpdate) {
                firesStationsToUpdate.setStation(fireStationsBody.getStation());
                firesStationsToUpdate.setAddress(fireStationsBody.getAddress());
                return firesStationsToUpdate;
    }

    /**
     * Find a fireStation Station by its address
     *
     * @param address the first name
     * @return the fireStation
     * @throws NotFoundException if noone was found
     */

        public FireStations findFireStationByAddress( String address ) throws NotFoundException {

            /* LOGGER.debug(
                    "FireStationService -> Searching for fire station at + address: " + address + " ...");*/

            FireStations fireStation = fireStationsRepository.findByAddress(
                    address );

            if (fireStation == null) {
                /*LOGGER.error("FireStationService -> Fire station at address "  + address + " doesn't exist");
                throw new NotFoundException(
                        "FireStationService -> Fire station at address: " + address + " doesn't exist");*/
            }

            LOGGER.info("FireStationService -> Fire station at address: " + address + " was found");
            return fireStation;
        }


//2021-08-25

  /*  *//**
     * Find station by address list.
     *
     * @param address the address
     * @return list of fire station covered by address
     * @throws NotFoundException if no fire station was found
     *//*
    public List<Integer> findStationByAddress(String address) {

        List<Integer> stationIds = (List<Integer>) FireStationsRepository.findByAddress(address);

        *//*LOGGER.debug(
                "FirestationService -> Searching for fire station at address"
                        + address + "...");*//*

        if (stationIds.isEmpty()) {
            *//*LOGGER.error("No station is existing at address: " + address);
            throw new NotFoundException(
                    "No station is existing at address: " + address);*//*

        }
        return stationIds;
    }
*/


} //END