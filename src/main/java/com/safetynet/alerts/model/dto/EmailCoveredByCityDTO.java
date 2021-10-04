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
public class EmailCoveredByCityDTO {

 List<Persons> persons;


 public EmailCoveredByCityDTO()
                       {
  this.persons = persons;

 }


} //END
