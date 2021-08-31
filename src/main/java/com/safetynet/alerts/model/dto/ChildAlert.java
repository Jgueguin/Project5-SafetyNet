package com.safetynet.alerts.model.dto;

import lombok.Data;

/**
 * ChildAlertDTO class.
 */
@Data
public class ChildAlert {

/*
    http://localhost:8080/childAlert?address=<address>

    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.

 */

    /**
     * The Address
     */
    private String address;


    /**
     * The FirstName
     */
    private String firstName;

    /**
     * The LastName
     */
    private String lastName;

    /**
     * The Age
     */
    private int age;


    /**
     * Instantiates a new Child Alert.
     */
    public ChildAlert() {

    }

    /**
     *  Instantiates a new Child Alert.
     *
     * @param personsAddress
     * @param personsAge
     * @param personFirstName
     * @param personLastName
     */
    public ChildAlert(final String personsAddress,
                      final int personsAge,
                      final String personFirstName,
                      final String personLastName) {
        this.address = personsAddress;
        this.age = personsAge;
        this.firstName = personFirstName;
        this.lastName = personLastName;
    }


} // END