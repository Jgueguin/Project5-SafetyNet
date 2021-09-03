package com.safetynet.alerts.model.dto;

import lombok.Data;

/**
 * List of Persons living at an address and specific fire station
 */
@Data
public class ListPersonsFireStationAddress {

/*
http://localhost:8080/fire?address=<address>

Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
médicaux (médicaments, posologie et allergies) de chaque personne.

 */

    /**
     * The fire Station
     */
    private Long fireStation;

    /**
     * The FirstName
     */
    private String firstName;

    /**
     * The LastName
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
     * Instantiates a new Fire Address.
     */
    public ListPersonsFireStationAddress() {

    }

    public ListPersonsFireStationAddress(final Long fireStation,
                                         final String firstName,
                                         final String lastName,
                                         final String phone,
                                         final int age,
                                         String[] medications,
                                         String[] allergies
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