package com.safetynet.alerts.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Flood class.
 */
@Data
@ToString
public class FloodListDTO {

/*
http://localhost:8080/flood/stations?stations=<a list of station_numbers>

Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.

 */
    ArrayList<String> floodArray ;

    public FloodListDTO(
    ) {
        floodArray = new ArrayList<>();
    }


} // END