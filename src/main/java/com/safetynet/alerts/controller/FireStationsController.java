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
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody FireStations firestationDetails) throws ResourceNotFoundException {

        FireStations fireStationsUpdate = fireStationsService.getFireStations(id).orElseThrow(() -> new ResourceNotFoundException("Firestation not found on :: "+ id));

        fireStationsUpdate.setStation(firestationDetails.getStation());
        fireStationsUpdate.setAddress(firestationDetails.getAddress());

        final FireStations updatedFireStations = fireStationsService.saveFirestations(fireStationsUpdate);

        return ResponseEntity.ok(updatedFireStations);
    }


    @PutMapping("/firestations/{address}")
    public ResponseEntity<FireStations> updateFireStationsByAddress(
            @PathVariable(value = "address") String address,

            @Valid @RequestBody final FireStations fireStations) throws NotFoundException {

        FireStations fireStationsUpdate = fireStationsService.findByAddress(address);
        FireStations fireStationsUpdated = fireStationsService.updateFireStationsByAddress(fireStations,fireStationsUpdate);

        final FireStations fireStationsSaved = fireStationsService.saveUpdated(fireStationsUpdate);

        LOGGER.info("PersonController (PUT) -> Successfully updated person: "
                + fireStationsUpdated.toString());

        return ResponseEntity.ok(fireStationsSaved);
    }



}


