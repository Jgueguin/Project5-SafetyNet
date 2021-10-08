package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.FireStations;
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
public class ListFireStationsDTO {

 List<FireStations> fireStations;

    /**
     * Instantiates  a new FireStation object.
     *
      */
    public ListFireStationsDTO(

    ) {
        this.fireStations= fireStations;
    }


} //END
