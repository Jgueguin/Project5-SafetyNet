package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.PhoneAlertListDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Person Service
 */

@Data
@Service
public class PhoneAlertService {
    @Autowired
    private PersonsRepository personsRepository;
    @Autowired
    private FireStationsRepository fireStationsRepository;
    // @Autowired
    // private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;
   // @Autowired
    // private DtoMedicalRecordsRepository dtoMedicalRecordsRepository;
    @Autowired
    private DtoFireStationsRepository dtoFireStationsRepository;

    // Logger
    private static final Logger logger = LogManager.getLogger("App");


    /*http://localhost:8080/phoneAlert?firestation=<firestation_number>

    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.*/

    /**
     * phone Alert
     *
     * @param station
     * @return phoneAlertList
     */
    public PhoneAlertListDTO phoneAlertDTO(Integer station) {

        logger.info("Call of Person Alert Service");

        PhoneAlertListDTO phoneAlertArray = new PhoneAlertListDTO();

        List<FireStations> fireStations = dtoFireStationsRepository.findByStation(station);

        ArrayList<String> tmp = phoneAlertArray.getPhoneAlertArray();

        for (FireStations f : dtoFireStationsRepository.findByStation(station)) {
            tmp.add(f.getAddress());
            for (Persons p : dtoPersonsRepository.findPersonByAddress(f.getAddress())) {
                tmp.add("      " + p.getPhone());

            }

            phoneAlertArray.setPhoneAlertArray(tmp);

        }

        return phoneAlertArray;
    }
}






