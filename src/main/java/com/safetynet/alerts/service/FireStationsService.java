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
    public FireStations updateFirestationsById (final Long id, FireStations fireStations) {

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
                return firesStationsToUpdate;
    }

    /**
     * Find a firestation by address
     *
     * @param address the first name
     * @return the firestation
     * @throws NotFoundException if noone was found
     */
    public FireStations findByAddress(String address) throws NotFoundException {
        LOGGER.info("FireStationsService -> Searching for Firestation @" + address +  " ...");
        FireStations fireStations = fireStationsRepository.findByAddress(address);

        if (fireStations == null) {
            LOGGER.info("FiresStationsService -> @" + address    + " doesn't exist");

            throw new NotFoundException(
                    "FireStation " + address + " doesn't exist");
        }
        LOGGER.info("FiresStationService -> FireStation " + address + " was found");

        return fireStations;
    }















//END
}