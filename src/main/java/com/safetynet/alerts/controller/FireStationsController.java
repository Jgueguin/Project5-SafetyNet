package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
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

    public FireStationsController(FireStationsService fireStationsService){
        this.fireStationsService = fireStationsService;
    }

    /**
     * Read - Get all fire stations
     *
     * @return - An Iterable object of fire station full filled
     */
    @GetMapping("/firestations")
    public ResponseEntity<Iterable<FireStations>> getFireStationsAll() {

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
        fireStationsService.deleteFireStationsById(id);
    }

    //2021-08-27

    /**
     * delete a person by its address
     * @param address
     */
    @DeleteMapping("/firestations/fire/{address}")
    public void deleteFireStationsByAddress(
            @PathVariable("address") String address)
    {
            fireStationsService.deleteFireStationsByAddress(address);
    }


    /**
     * Create - Add a new fire station
     *
     * @param fireStationsDetails: An object employee
     * @return The fire station object saved
     */
    @PostMapping("/firestations")
    public ResponseEntity<FireStations> createFireStations(
            @RequestBody FireStations fireStationsDetails) {
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

        fireStationsService.updateFireStationsById(id, firestationDetails);

        return ResponseEntity.ok(firestationDetails);
    }


    // update 2021-08-26
    /**
     * Update a firestation by its address
     *
     * @param address
     * @param fireStationsDetails
     * @return
     */
/*    @PutMapping("/firestations/fire/{address}")
    public ResponseEntity<FireStations> updateFireStationByAddress(
            @PathVariable (value = "address") String address,
            @RequestBody FireStations fireStationsDetails)
    {
        fireStationsService.updateFireStationsByAddress(address,fireStationsDetails);

        return ResponseEntity.ok(fireStationsDetails);
    }*/

    @PutMapping("/firestations")
    public ResponseEntity<FireStations> updateFireStationByAddress(
            @PathVariable ("address") String address,
            @RequestBody FireStations fireStationsDetails)
    {
        fireStationsService.updateFireStationsByAddress(address,fireStationsDetails);

        return ResponseEntity.ok(fireStationsDetails);
    }






    // URLS
    //http://localhost:8080/firestation?stationNumber=<station_number>

    /**
     * Read - Get a fire station
     * @param id The id of a fire station
     * @return A firestation object full filled
     */
/*
    @GetMapping("/firestations")
    public FireStations getFireStations(
            @PathVariable("stationNumber") Long stationNumber) {

        Optional<FireStations> fireStations = fireStationsService.getFireStationsById(id);

        if(fireStations.isPresent()) {
            return fireStations.get();
        } else {
            return null;
        }
    }
*/


}


