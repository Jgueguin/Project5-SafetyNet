package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.PersonInfoByFirstNameAndLastNameListDTO;
import com.safetynet.alerts.repository.*;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Person Info Service
 */
// @Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Service
public class DtoPersonInfoService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;

    //Logger
    private static final Logger logger = LogManager.getLogger("App");

    // http://localhost:9090/personInfo?firstName=<firstName>&lastName=<lastName>

    /**
     * Find PersonInfo from given firstName and Lastname
     *
     * @param firstName
     * @param lastName
     * @return personInfo
     */
    public PersonInfoByFirstNameAndLastNameListDTO firstNameAndLastNameDTO(String firstName, String lastName) {

        logger.info("Call of PersonInfo by firstname and lastname DTO Service");

        PersonInfoByFirstNameAndLastNameListDTO personInfoArray = new PersonInfoByFirstNameAndLastNameListDTO();

        // préparation tableau intermédiaire pour récupérer les données
        ArrayList<String> tmp2 = personInfoArray.getPersonInfoArray();

        for (
                Persons p : dtoPersonsRepository.findPersonInfoByFirstNameAndLastName(firstName, lastName)
        ) {
            tmp2.add(p.getFirstName() + " " + p.getLastName());
            tmp2.add(p.getAddress());

            MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());

            Calendar date = new GregorianCalendar();
            Calendar birthdate = medicalRecords.getBirthDate();
            tmp2.add("Age: " + (date.getWeekYear() - birthdate.getWeekYear()));

            tmp2.add(p.getEmail());

            String allergies = String.join(",", medicalRecords.getAllergies());
            tmp2.add("Allergies: " + allergies);

            String medications = String.join(",", medicalRecords.getMedications());
            tmp2.add("Medications: " + medications);

        }
        personInfoArray.setPersonInfoArray(tmp2);

        return personInfoArray;
    }

}






