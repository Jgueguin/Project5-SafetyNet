package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Fire Station Controller
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
        fireStationsService.deleteFireStationsById(id);
    }

    //2021-08-27

    /**
     * delete a person by its first name and last name
     * @param id
     */
    /*@DeleteMapping("/firestations/{firstName}/{lastName}")
    public void deleteFireStationsByFirstNameAndLastName(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
            fireStationsService.deleteFireStationsByFirstNameAndLastName(firstName,lastName);
    }*/



    /**
     * Create - Add a new fire station
     *
     * @param fireStations: An object employee
     * @return The fire station object saved
     */
    @PostMapping("/firestations")
    public FireStations createFireStations(@RequestBody FireStations fireStations) {
        return fireStationsService.saveFirestations(fireStations);
    }



    // update 2021-08-26
     /**
     * Modify  "Put" - a fire station
     * @param id The id of a fire station
     * @return A firestation object full filled
     */

    @PutMapping("/firestations/{id}")
    public FireStations updateFireStationsById(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody FireStations firestationDetails)  {

        return fireStationsService.updateFireStationsById(id, firestationDetails);

    }

    // update 2021-08-26
    /**
     * Update a firestation by its address
     *
     * @param address
     * @param fireStationsDetails
     * @return
     */
    @PutMapping("/firestation/{address}")
    public FireStations updateFireStationByAddress(
            @PathVariable(value = "address") String address,
            @Valid @RequestBody FireStations fireStationsDetails
    ){
            return fireStationsService.updateFireStationsByAddress(address,fireStationsDetails);
    }



    /**
     * Read - Get a fire station
     * @param id The id of a fire station
     * @return A firestation object full filled
     */
    @GetMapping("/firestations/{stationNumber}")
    public FireStations getFireStations(
            @PathVariable("stationNumber") Long stationNumber) {

        Optional<FireStations> fireStations = fireStationsService.getFireStations(id);

        if(fireStations.isPresent()) {
            return fireStations.get();
        } else {
            return null;
        }
    }


}


