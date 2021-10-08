package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.MedicalRecords;
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
public class ListMedicalRecordsDTO {

 List<MedicalRecords> medicalRecords;

    /**
     * Instantiates  a new MedicalRecord object.
     *
      */
    public ListMedicalRecordsDTO(

    ) {

        this.medicalRecords= medicalRecords;

    }




} //END
