package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.FireStationsRepository;
import com.safetynet.alerts.model.FireStations;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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




}