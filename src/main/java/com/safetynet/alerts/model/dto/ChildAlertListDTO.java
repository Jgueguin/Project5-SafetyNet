package com.safetynet.alerts.model.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * ChildAlertDTO class.
 */
@Data
public class ChildAlertListDTO {

/*
    http://localhost:8080/childAlert?address=<address>

    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.

 */

ArrayList<String> childAlertArray = new ArrayList<>();


    /**
     *  Instantiates a new Child Alert.
     *
     */
    public ChildAlertListDTO() {

        childAlertArray =new ArrayList<>();




    }


} // END