package com.safetynet.controller;

import com.safetynet.model.FireStations;
import com.safetynet.model.Persons;
import com.safetynet.service.FireStationsService;
import com.safetynet.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Firestation Controller
 */


@RestController
public class FireStationsController {

    @Autowired
    private FireStationsService fireStationsService;

    public FireStationsController(FireStationsService fireStationsService){
        this.fireStationsService = fireStationsService;
    }


    /**
     * Read - Get all fire stations
     *
     * @return - An Iterable object of fire station full filled
     */
    @GetMapping("/firestations")
    public Iterable<FireStations> getFireStations() {
        return fireStationsService.getFireStations();
    }

    /**
     * Read - Get a fire station
     * @param id The id of a fire station
     * @return A firestation object full filled
     */
    @GetMapping("/firestations/{id}")
    public FireStations getFireStations(@PathVariable("id") final Long id) {
        Optional<FireStations> fireStations = fireStationsService.getFireStations(id);

        if(fireStations.isPresent()) {
            return fireStations.get();
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a firestation
     * @param id - The id of the fire station to delete
     */
    @DeleteMapping("/firestations/{id}")
    public void deleteFireStations(@PathVariable("id") final Long id) {

        fireStationsService.deleteFirestations(id);

    }

    /**
     * Create - Add a new fire station
     *
     * @param fireStation: An object employee
     * @return The fire station object saved
     */
    @PostMapping("/firestations")
    public FireStations createFireStations(@RequestBody FireStations fireStations) {

        return fireStationsService.saveFirestations(fireStations);
    }
}


