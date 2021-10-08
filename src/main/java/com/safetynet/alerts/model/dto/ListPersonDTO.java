package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.Persons;
import lombok.Data;

import java.util.List;

/**
 * List of Email/persons covered by a specific city
 */

/*
http://localhost:9090/communityEmail?city=<city>

        Cette url doit retourner les adresses mail de tous les habitants de la ville.
*/

@Data
public class ListPersonDTO {

 List<Persons> persons;

    /**
     * Instantiates  a new EmailCoveredByCity object.
     *
      */
    public ListPersonDTO(

    ) {

        this.persons= persons;

    }




} //END
