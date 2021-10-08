package com.safetynet.alerts.model.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * List of Email/persons covered by a specific city
 */

/*
http://localhost:9090/communityEmail?city=<city>

        Cette url doit retourner les adresses mail de tous les habitants de la ville.
*/

@Data
public class PersonInfoByFirstNameAndLastNameDTO {

    ArrayList<String> email ;

    /**
     * Instantiates  a new ExtractEmail object.
     *
     */
    public PersonInfoByFirstNameAndLastNameDTO(

    ) {
        email =new ArrayList<>();

    }


} //END
