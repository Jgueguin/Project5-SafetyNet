package com.safetynet.alerts.model.dto;

import lombok.Data;

/**
 * Phone Alert class.
 */
@Data
public class PhoneAlert {

/*
  http://localhost:8080/phoneAlert?firestation=<firestation_number>

Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.

 */

    /**
     * The fire Station
     */
    private Long fireStation;

    /**
     * The Phone
     */
    private String phone;


    /**
     * Instantiates a new Phone Alert.
     */
    public PhoneAlert() {

    }

    /**
     * Instantiates a new Phone Alert.
     * @param fireStation
     * @param phone
     */
    public PhoneAlert(final Long fireStation,
                      final String phone) {
        this.fireStation = fireStation;
        this.phone = phone;
    }


} // END