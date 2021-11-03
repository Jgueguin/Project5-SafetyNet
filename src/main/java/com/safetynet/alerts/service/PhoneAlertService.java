package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.PhoneAlertListDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
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
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;
    @Autowired
    private DtoMedicalRecordsRepository dtoMedicalRecordsRepository;
    @Autowired
    private DtoFireStationsRepository dtoFireStationsRepository;


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

        PhoneAlertListDTO phoneAlertArray = new PhoneAlertListDTO();

        // récupération de l'addresse à partir du numero de station
        List<FireStations> fireStations = dtoFireStationsRepository.findByStation(station);

        // mise en place du tableau intermédiaire
        ArrayList<String> tmp = phoneAlertArray.getPhoneAlertArray();

        // tmp.add("Caserne n°: " + station);

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






