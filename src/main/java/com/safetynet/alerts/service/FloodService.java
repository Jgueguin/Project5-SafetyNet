package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.FloodListDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Flood Service
 */

@Data
@Service
public class FloodService {
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

    public FloodListDTO floodDTO(Integer station) {

        FloodListDTO floodArray = new FloodListDTO();

        List<FireStations> fireStations = dtoFireStationsRepository.findByStation(station);

        ArrayList<String> tmp = floodArray.getFloodArray();

        tmp.add("Caserne n°: " + station);

        for (FireStations f : dtoFireStationsRepository.findByStation(station)) {
            tmp.add(f.getAddress());

            for (Persons p : dtoPersonsRepository.findPersonByAddress(f.getAddress())) {

                MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                Date date = new Date();
                Date birthdate = medicalRecords.getBirthDate();
                Integer age = date.getYear()-birthdate.getYear();

                tmp.add("      " + p.getLastName()+" "+p.getFirstName());
                tmp.add("                  "+p.getPhone());
                tmp.add("                   Age: "+age);

                String allergies = String.join(",", medicalRecords.getAllergies());
                tmp.add("                   Allergies: " + allergies);

                String medications = String.join(",", medicalRecords.getMedications());
                tmp.add("                   Medications: " + medications);

            }
        }
        return floodArray;
    }

}






