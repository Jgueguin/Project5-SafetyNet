package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //Logger
    private static final Logger logger = LogManager.getLogger("FireStationsController");

    public FireStationsController(FireStationsService fireStationsService){

        logger.info("--> Instantiates a new controller");

        this.fireStationsService = fireStationsService;
    }

    /**
     * Read - Get all fire stations
     *
     * @return - An Iterable object of fire station full filled
     */
    @GetMapping("/firestations")
    public ResponseEntity<Iterable<FireStations>> getFireStationsAll() {

        logger.info("Get Mapping all fire stations");

        return ResponseEntity.ok(fireStationsService.getFireStationsAll());
    }


    /**
     * Read - Get a fire station
     * @param id The id of a fire station
     * @return A firestation object full filled
     */
    @GetMapping("/firestations/{id}")
    public ResponseEntity<FireStations> getFireStationsById(
            @PathVariable("id") final Long id)
    {
        logger.info("Get Mapping a fire station");

        Optional<FireStations> fireStationsDetails = (fireStationsService.getFireStationsById(id));

        if(fireStationsDetails.isPresent()) {

            return ResponseEntity.ok(fireStationsDetails.get());

        } else {
            return null;
        }
    }


    /**
     * Delete - Delete a firestation
     * @param id - The id of the fire station to delete
     */
    @DeleteMapping("/firestations/{id}")
    public void deleteFireStations(@PathVariable("id") final Long id)
    {
        logger.info("Delete a fire stations");

        fireStationsService.deleteFireStationsById(id);
    }

    //2021-08-27

    /**
     * delete a person by its address
     * @param address
     */
   /* @DeleteMapping("/firestations/fire/{address}")
    public void deleteFireStationsByAddress(
            @PathVariable("address") String address)
    {
        logger.info("Delete a person by its address");

        fireStationsService.deleteFireStationsByAddress(address);
    }*/


    /**
     * Create - Add a new fire station
     *
     * @param fireStationsDetails: An object employee
     * @return The fire station object saved
     */
    @PostMapping("/firestations")
    public ResponseEntity<FireStations> createFireStations(
            @RequestBody FireStations fireStationsDetails) {

        logger.info("Create a new fire Station");

        fireStationsService.saveFirestations(fireStationsDetails);

        return ResponseEntity.ok(fireStationsDetails);

    }


    // update 2021-08-26
     /**
     * Modify  "Put" - a fire station
     * @param id The id of a fire station
     * @return A firestation object full filled
     */

    @PutMapping("/firestations/{id}")
    public ResponseEntity<FireStations> updateFireStationsById(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody FireStations firestationDetails)  {

        logger.info("Modify a fire Station by its Id");

        fireStationsService.updateFireStationsById(id, firestationDetails);

        return ResponseEntity.ok(firestationDetails);
    }

    // update 2021-08-26

    /**
     *
     * @param address
     * @param fireStationsDetails
     * @return
     */
    @PutMapping("/firestations")
    public ResponseEntity<FireStations> updateFireStationByAddress(
            @PathVariable ("address") String address,
            @RequestBody FireStations fireStationsDetails)
    {
        logger.info("update a FireStation by its address");

        fireStationsService.updateFireStationsByAddress(address,fireStationsDetails);

        return ResponseEntity.ok(fireStationsDetails);
    }

}


