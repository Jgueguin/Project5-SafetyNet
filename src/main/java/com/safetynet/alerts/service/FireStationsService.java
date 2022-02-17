package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.repository.FireStationsRepository;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Fire Stations Service
 */

// @Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@Service
public class FireStationsService {
    @Autowired
    private FireStationsRepository fireStationsRepository;

    //Logger
    private static final Logger logger = LogManager.getLogger("FireStationsService");

    /**
     * Select a choosen Fire Station between all
     *
     * @param id : parameter to choose the fire station
     * @return : the information for the choosen fire station
     */
    public Optional<FireStations> getFireStationsById(final Long id) {

        logger.info("Get FireStation by Id ");

        Optional<FireStations> optionalFireStations = fireStationsRepository.findById(id);

        if (optionalFireStations.isPresent()) {
            optionalFireStations.get();

            return fireStationsRepository.findById(id);
        }
        return null;
    }

    /**
     * Select all the fire stations
     *
     * @return: the informations for all the firestations
     */
    public Iterable<FireStations> getFireStationsAll() {

        logger.info("Get All FireStation");

        return fireStationsRepository.findAll();
    }

    /**
     * delete a choosen firestation
     *
     * @param id : parameter to choose the fire station to delete
     */
    public void deleteFireStationsById(final Long id) {

        logger.info("Delete a Fire Station by Id");

        fireStationsRepository.deleteById(id);
    }


//2021-08-27

/*    public void deleteFireStationsByAddress(String address) {
        FireStations fireStationsToDelete = fireStationsRepository.findByAddress(address);
        fireStationsRepository.deleteById(fireStationsToDelete.getId());
    }*/


    /**
     * Save a new fire station
     *
     * @param firestations
     * @return : save the new fire station into Repository
     */
    public FireStations saveFirestations(FireStations firestations) {

        logger.info("Save a Firestation");

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

        logger.info("Save all FireStations ");

        return fireStationsRepository.saveAll(firestations);
    }


    //2021-08-26

    /**
     * * Update a fire station.( Put ) by its id
     *
     * @param id
     * @param fireStationsDetails the firestations
     * @return update the fire stations
     */

    public FireStations updateFireStationsById(
            final Long id,
            FireStations fireStationsDetails) {

        try {

            logger.info("Update FireStation By Id");

            Optional<FireStations> optionalFireStations = fireStationsRepository.findById(id);

            if (optionalFireStations.isPresent()) {

                FireStations fireStationsToUpdate = optionalFireStations.get();

                Integer stationNumber = fireStationsDetails.getStation();
                if (stationNumber!=null) {
                    fireStationsToUpdate.setStation(stationNumber);
                }

                String address = fireStationsDetails.getAddress();
                if (address!=null) {
                    fireStationsToUpdate.setAddress(address);
                }

                fireStationsToUpdate.setAddress(fireStationsDetails.getAddress());

                return fireStationsRepository.save(fireStationsToUpdate);
            }
        }
        catch (Exception e) {

            logger.error("Fire station doesn't exist");

        }
        return null;

        }


    //2021-09-10
    /**
     * Find a fireStation Station by its address
     * @param address the first name
     * @return the fireStation
     */

    public FireStations updateFireStationsByAddress(
            String address,
            FireStations fireStationsDetails) {

        try {

            logger.info("Update Fire Station by Address Service");

           FireStations fireStationsToUpdate = fireStationsRepository.findByAddress(address);

            Integer stationNumber = fireStationsDetails.getStation();
            if (stationNumber != null) {
                fireStationsToUpdate.setStation(stationNumber);
            }

            return fireStationsRepository.save(fireStationsToUpdate);

        } catch(Exception exception){

            logger.error("Fire station doesn't exist");
        }

        return null;
    }

     //END
}

