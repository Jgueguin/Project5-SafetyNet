package com.safetynet.alerts.model.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * ChildAlertDTO class.
 */
@Data
public class PhoneAlertListDTO {


    /*http://localhost:8080/phoneAlert?firestation=<firestation_number>

    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.*/

ArrayList<String> phoneAlertArray = new ArrayList<>();


    /**
     *  Instantiates a new Phone Alert list.
     *
     */
    public PhoneAlertListDTO() {

        phoneAlertArray =new ArrayList<>();
    }


} // END