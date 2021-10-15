package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.FireStationByAddressDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;


/**
 * Fire Station by Address Service
 */

@Data
@Service
public class FireStationByAddressService {
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


    /*http://localhost:9090/fire?address=<address>
    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.*/

    public FireStationByAddressDTO personsCoveredByAddress2(String address) {

        // Création objet de type PersonCoveredByFireStationDTO2
        FireStationByAddressDTO fireStationsArray = new FireStationByAddressDTO();

        // préparation tableau intermédiaire pour récupérer les données
        ArrayList<String> tmp2 = (ArrayList<String>) fireStationsArray.getFireAddressArray();

        tmp2.add(address);

        FireStations fireStations = fireStationsRepository.findByAddress(address);
        tmp2.add("StationNumber :" + fireStations.getStation().toString());
        tmp2.add("   ");

        for (
                Persons p : dtoPersonsRepository.findPersonsCoveredByAddress(address)
        ) {

            tmp2.add(p.getFirstName() + " " + p.getLastName());
            tmp2.add("Phone : " + p.getPhone());

            MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());

            Date date = new Date();
            Date birthdate = medicalRecords.getBirthDate();
            tmp2.add("Age: " + (date.getYear() - birthdate.getYear()));


            String allergies = String.join(",", medicalRecords.getAllergies());
            tmp2.add("Allergies: " + allergies);

            String medications = String.join(",", medicalRecords.getMedications());
            tmp2.add("Medications: " + medications);

            tmp2.add("       ");

            fireStationsArray.setFireAddressArray(tmp2);

        }

        return fireStationsArray;
    }

}






