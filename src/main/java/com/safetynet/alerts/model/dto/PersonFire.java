package com.safetynet.alerts.model.dto;

import lombok.Data;

/**
 * Person fire dto
 */

/*
    http://localhost:8080/firestation?stationNumber=<station_number>

    Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.

    Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste doit inclure
    les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus, elle doit fournir un décompte
    du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou moins) dans la zone desservie.

    */

@Data
public class PersonFire {

    /**
     * The Station number.
     */
    private Integer stationNumber;
    /**
     * The First name.
     */
    private String firstName;
    /**
     * The Last name.
     */
    private String lastName;
    /**
     * The Phone.
     */
    private String phone;

    /**
     * The Age.
     */
    private int age;

    /**
     * The address
     */
    private String address;


    /**
     * Instantiates a new Person fire dto.
     */
    public PersonFire() {
    }

    /**
     * Instantiates a new Person fire dto.
     *
     * @param stationNumber the station number
     * @param firstName     the first name
     * @param lastName      the last name
     * @param phone         the phone
     * @param age           the age
     * @param address       the address
     */
    public PersonFire(Integer stationNumber, String firstName,
                      String lastName, String phone, int age, String address
    ) {
        this.stationNumber = stationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.address = address;
    }


} //END
