package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.FireStationByStationNumberDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * FireStation By StationNumber Service
 */

@Data
@Service
public class FireStationByStationNumberService {
    @Autowired
    private PersonsRepository personsRepository;
    @Autowired
    private FireStationsRepository fireStationsRepository;
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;
    @Autowired
    private DtoMedicalRecordsRepository dtoMedicalRecordsRepository;
    @Autowired
    private DtoFireStationsRepository dtoFireStationsRepository;


    /**
     * FireStation By StationNumber Service
     *
     * @param station the number of the station
     * @return list of persons covered by station number
     */
    public FireStationByStationNumberDTO personByStationDTO(Integer station) {

        FireStations fireStation1 = dtoFireStationsRepository.findByStation(station).get(0);

        String addressFireStation = fireStation1.getAddress();

        FireStationByStationNumberDTO fireStationByStationNumberArray = new FireStationByStationNumberDTO();
        fireStationByStationNumberArray.setPersons(dtoPersonsRepository.findPersonByAddress(addressFireStation));

        int count_child = 0;
        int count_adult = 0;

        Date date = new Date();
        int actualYear = date.getYear();

        for (Persons p : fireStationByStationNumberArray.getPersons()) {

            if (
                    actualYear - medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName()).getBirthDate().getYear() <= 18) {
                count_child++;
            } else {
                count_adult++;
            }
        }

        fireStationByStationNumberArray.setCount_adult(count_adult);
        fireStationByStationNumberArray.setCount_child(count_child);

        return fireStationByStationNumberArray;

    }

}






