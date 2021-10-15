package com.safetynet.alerts.model.dto;

import lombok.Data;

import java.util.ArrayList;

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
public class PersonInfoByFirstNameAndLastNameListDTO {

    ArrayList<String> personInfoArray = new ArrayList<>();

    /**
     * Instantiates  a new PersonInfoByFirstNameAndLastName object
     *
     */
    public PersonInfoByFirstNameAndLastNameListDTO(

    ) {

      personInfoArray = new ArrayList<>();

    }




} //END
