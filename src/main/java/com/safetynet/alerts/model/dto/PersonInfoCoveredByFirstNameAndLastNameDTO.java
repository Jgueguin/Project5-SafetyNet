package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import lombok.Data;

import java.util.List;

/**
 * List of person info with first name and lastname parameters
 */

/*
http://localhost:9090/personInfo?firstName=<firstName>&lastName=<lastName>

Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
toutes apparaître
*/

@Data
public class PersonInfoByFirstNameAndLastNameDTO {

 List<Persons> persons;
 List<MedicalRecords> medicalRecords;

    /**
     * Instantiates  a new PersonInfoByFirstNameAndLastName object
     *
     */
    public PersonInfoByFirstNameAndLastNameDTO(

    ) {


        this.persons= persons;
        this.medicalRecords = getMedicalRecords();

    }




} //END
