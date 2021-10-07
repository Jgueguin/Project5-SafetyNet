package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.Persons;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * List of Email/persons covered by a specific city
 */

/*
http://localhost:9090/communityEmail?city=<city>

        Cette url doit retourner les adresses mail de tous les habitants de la ville.
*/

@Data
public class CommunityEmailByCityDTO {

 List<Persons> persons;
 ArrayList<String> email ;

    /**
     * Instantiates  a new EmailCoveredByCity object.
     *
      */
    public CommunityEmailByCityDTO(

    ) {
        email =new ArrayList<>();

        this.persons= persons;

    }




} //END
