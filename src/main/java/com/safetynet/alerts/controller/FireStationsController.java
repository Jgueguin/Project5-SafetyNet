package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.service.FireStationsService;
import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

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
     * @param fireStations: An object employee
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
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody FireStations firestationDetails) throws ResourceNotFoundException {

        FireStations fireStationsUpdate = fireStationsService.getFireStations(id).orElseThrow(() -> new ResourceNotFoundException("Firestation not found on :: "+ id));

        fireStationsUpdate.setStation(firestationDetails.getStation());
        fireStationsUpdate.setAddress(firestationDetails.getAddress());

        final FireStations updatedFireStations = fireStationsService.saveFirestations(fireStationsUpdate);

        return ResponseEntity.ok(updatedFireStations);
    }


    /**
     * Update firestation.
     *
     * @param address     the address
     * @param fireStations the firestation
     * @return the response entity
     */
    @PutMapping("/firestation/{address}")
    public ResponseEntity<FireStations> updateFireStation(
            @RequestParam final String address,
            //, @RequestParam final int station,
            @Valid @RequestBody final FireStations fireStations) throws NotFoundException {

        FireStations fireStationsToUpdate = fireStationsService.findStationByAddress(address);

        FireStations fireStationsUpdated = fireStationsService.updateFireStationsByAddress(
                fireStations, fireStationsToUpdate);

        FireStations fireStationsSaved = fireStationsService.saveUpdated(fireStationsUpdated);

        LOGGER.info("FirestationController (PUT) -> Successfully updated fire "
                + "station: " + fireStationsUpdated.toString());
        return ResponseEntity.ok(fireStationsSaved);

    }





}


