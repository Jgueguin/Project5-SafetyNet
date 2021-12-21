package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.FloodListDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Flood Service
 */

@Data
@Service
public class FloodService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;

    @Autowired
    private DtoFireStationsRepository dtoFireStationsRepository;

    //Logger
    private static final Logger logger = LogManager.getLogger("FloodService");


    public FloodListDTO floodDTO(Integer station) {

        logger.info("Call of Flood DTO service");

        FloodListDTO floodArray = new FloodListDTO();

        ArrayList<String> tmp = floodArray.getFloodArray();

        for (FireStations f : dtoFireStationsRepository.findByStation(station)) {
            tmp.add(f.getAddress());

            for (Persons p : dtoPersonsRepository.findPersonByAddress(f.getAddress())) {

                MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                Calendar date = new GregorianCalendar();
                Calendar birthdate = medicalRecords.getBirthDate();
                Integer age = date.getWeekYear()-birthdate.getWeekYear();

                tmp.add("      " + p.getLastName()+" "+p.getFirstName());
                tmp.add("                  "+p.getPhone());
                tmp.add("                   Age: "+age);

                String allergies = String.join(",", medicalRecords.getAllergies());
                tmp.add("                   Allergies: " + allergies);

                String medications = String.join(",", medicalRecords.getMedications());
                tmp.add("                   Medications: " + medications);

                floodArray.setFloodArray(tmp);
            }
        }
        return floodArray;
    }

}






