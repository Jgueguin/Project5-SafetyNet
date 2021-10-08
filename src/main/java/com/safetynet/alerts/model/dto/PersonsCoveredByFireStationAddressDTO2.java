package com.safetynet.alerts.model.dto;

import lombok.Data;

import java.util.*;

/**
 * List of Persons living at an address and specific fire station
 */
@Data
public class PersonsCoveredByFireStationAddressDTO2 {

/*
http://localhost:8080/fire?address=<address>

Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
médicaux (médicaments, posologie et allergies) de chaque personne.

 */

    List<String> fireAddressArray ;

    /**
     * Instantiates a new fire Address Array
     *
     */


    public PersonsCoveredByFireStationAddressDTO2(){

        fireAddressArray =new ArrayList<>();
    }


} // END