package com.safetynet.alerts.model.dto;

import lombok.Data;

/**
 * Phone Alert class.
 */
@Data
public class Flood {

/*
http://localhost:8080/flood/stations?stations=<a list of station_numbers>

Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.

 */

    /**
     * The fire Station
     */
    private Long fireStation;

    /**
     * The Adress
     */
    private String address;

    /**
     * The First name.
     */
    private String firstName;

    /**
     * The Last name.
     */

    private String lastName;

    /**
     * The Phone
     */
    private String phone;

    /**
     * The Age
     */
    private int age;

    /**
     * The Medications
     */
    private String[] medications;

    /**
     * The Allergies
     */
    private String[] allergies;


    /**
     * Instantiates a new Flood.
     */
    public Flood() {

    }


    public Flood(final Long fireStation,
                 final String firstName,
                 final String lastName,
                 final String phone,
                 final int age,
                 final String[] medications,
                 final String[] allergies
    ) {
        this.fireStation = fireStation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }


} // END