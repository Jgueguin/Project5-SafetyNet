package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.ChildAlertListDTO;
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
 * Dto Child Alert Service
 */

@Data
@Service
public class DtoChildAlertService {
 /*   @Autowired
    private PersonsRepository personsRepository;*/
    //@Autowired
    //private FireStationsRepository fireStationsRepository;
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;

   /* @Autowired
    private DtoMedicalRecordsRepository dtoMedicalRecordsRepository;*/

    //@Autowired
    //private DtoFireStationsRepository dtoFireStationsRepository;

    // logger
    private static final Logger logger = LogManager.getLogger("DtoChildAlertService");

/*
    http://localhost:9090/childAlert?address=<address>

    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
*/

    /**
     * Child Alert
     *
     * @param address
     * @return childAlertList
     */
    public ChildAlertListDTO childAlertDTO(String address) {

        logger.info("Call of Dto Child Alert Service ");

        ChildAlertListDTO childAlertArray = new ChildAlertListDTO();

        ArrayList<String> tmp = childAlertArray.getChildAlertArray();
        //         tmp.add(address);

        for (Persons p : dtoPersonsRepository.findPersonByAddress(address)) {

            MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
            Calendar date = new GregorianCalendar();
            Calendar birthdate = medicalRecords.getBirthDate();

            if (date.getWeekYear() - birthdate.getWeekYear() <= 18) {

                tmp.add("Child : " + p.getFirstName() + " " + p.getLastName() + " Age: " + (date.getWeekYear() - birthdate.getWeekYear()));

            } else {

                tmp.add("Adult :" + p.getFirstName() + " " + p.getLastName());

            }

            childAlertArray.setChildAlertArray(tmp);
        }

        return childAlertArray;
    }

}






