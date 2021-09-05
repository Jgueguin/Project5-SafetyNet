package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.repository.FireStationsRepository;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
     *
     * @param id : parameter to choose the fire station
     * @return : the information for the choosen fire station
     */
    public Optional<FireStations> getFireStationsById(final Long id) {

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

        return fireStationsRepository.findAll();
    }

    /**
     * delete a choosen firestation
     *
     * @param id : parameter to choose the fire station to delete
     */
    public void deleteFireStationsById(final Long id) {

        fireStationsRepository.deleteById(id);
    }


//2021-08-27

    public void deleteFireStationsByAddress(String address) {

        fireStationsRepository.deleteByAddress(address);
    }


    /**
     * Save a new fire station
     *
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


    //2021-08-26

    /**
     * * Update a fire station.( Put ) by its id
     *
     * @param id
     * @param fireStations the firestations
     * @return update the fire stations
     */

    public FireStations updateFireStationsById(
            final Long id,
            FireStations fireStationsDetails) {

        try {
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

            System.out.println(e);
        }
        return null;

        }


    //2021-08-26

    /**
     * Find a fireStation Station by its address
     *
     * @param address the first name
     * @return the fireStation
     */

    public FireStations updateFireStationsByAddress(
            String address,
            @Valid FireStations fireStationsDetails) {

        try {

            FireStations fireStationsNumberUpdate = fireStationsRepository.findByAddress(address);

            Integer stationNumber = fireStationsDetails.getStation();

            if (stationNumber != null) {
                fireStationsNumberUpdate.setStation(stationNumber);
            }

            return fireStationsRepository.save(fireStationsNumberUpdate);

        } catch(Exception e){

        }
        return null;
    }

//2021-08-25


/**
         * Find station by address list.
         *
         * @param address the address
         * @return list of fire station covered by address
         * @throws NotFoundException if no fire station was found
         *//*

    public List<Integer> findStationByAddress(String address) {

        List<Integer> stationIds = (List<Integer>) FireStationsRepository.findByAddress(address);

        *LOGGER.debug(
                "FirestationService -> Searching for fire station at address"
                        + address + "...");

        if (stationIds.isEmpty()) {
            LOGGER.error("No station is existing at address: " + address);
            throw new NotFoundException(
                    "No station is existing at address: " + address);

        }
        return stationIds;
    }
*/



     //END
}

