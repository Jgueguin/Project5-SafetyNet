package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
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



    /**
     * Modify - "Put" a fire station
     * @param id The id of a fire station
     * @return A firestation object full filled
     */

    @PutMapping("/firestations/{id}")
   public ResponseEntity<FireStations> updateFireStations(
           @PathVariable(value = "id") final Long id,
           @Valid @RequestBody FireStations fireStationDetails) throws ResourceNotFoundException
    {

         FireStations fireStations =fireStationsService.updateFirestations(id, fireStationDetails);

         fireStations.setStation(fireStations.getStation());
         fireStations.setAddress(fireStations.getAddress());

        final FireStations updateFireStation = fireStationsService.saveFirestations(fireStations);

        return ResponseEntity.ok(updateFireStation);



    }





   /* public ResponseEntity<FireStations> updateFireStations(
            @RequestParam(value = "station") final Integer station,
            @RequestParam(value = "adress") final String adress,
            @Valid @RequestBody final FireStations fireStations) {

        FireStations fireStationsToUpdate = fireStationsService.updateFirestations(fireStations);
        LOGGER.info("PersonController (PUT) -> Successfully updated person: "
                + fireStationsToUpdate.toString());
        return ResponseEntity.ok(fireStationsToUpdate);

    }
*/


}


