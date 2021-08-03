package com.safetynet.service;

import com.safetynet.model.fireStations;
import com.safetynet.repository.FireStationsRepository;
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
    public Optional<fireStations> getPersons(final Long id) {

        return fireStationsRepository.findById(id);
    }

    /**
     * Select all the fire stations
     * @return: the informations for all the firestations
     */
    public Iterable<fireStations> getFireStations() {

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
    public fireStations saveFirestations(fireStations firestations) {

        fireStations savedFirestations = fireStationsRepository.save(firestations);
        return firestations;
    }
}