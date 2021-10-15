package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.CommunityEmailByCityListDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * Dto Community Email Service
 */

@Data
@Service
public class DtoCommunityEmailService {
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
     * find email for persons living in a given city
     *
     * @param city
     * @return
     */
    public CommunityEmailByCityListDTO extractEmailByCityDTO(String city) {

        // récupérer tous les habitants vivant dans une ville donnée

        CommunityEmailByCityListDTO emailArray = new CommunityEmailByCityListDTO();

        // récupérer tous les emails des personnes vivant dans une ville donnée
        for (Persons p : dtoPersonsRepository.findEmailByCity(city)) {

            ArrayList<String> tmp = emailArray.getEmailArray();
            tmp.add("Firstname: " + p.getFirstName() + " -- Lastname: " + p.getLastName() + "-> Email: " + p.getEmail());

            emailArray.setEmailArray(tmp);
        }

        return emailArray;
    }

}






