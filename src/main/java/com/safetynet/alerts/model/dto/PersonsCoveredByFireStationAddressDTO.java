package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import lombok.Data;

import java.util.List;

/**
 * List of Persons living at an address and specific fire station
 */
@Data
public class PersonsCoveredByFireStationAddressDTO {

/*
http://localhost:8080/fire?address=<address>

Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
médicaux (médicaments, posologie et allergies) de chaque personne.

 */

    List<Persons> persons;
    List<MedicalRecords> medicalRecords;
    List<FireStations> fireStations;

    public PersonsCoveredByFireStationAddressDTO(){

        this.persons = persons;
        this.medicalRecords = medicalRecords;
        this.fireStations = fireStations;
    }




} // END