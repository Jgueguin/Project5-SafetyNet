package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.Persons;
import lombok.Data;

import java.util.List;

/**
 * List of Email/persons covered by a specific city
 */

/*
http://localhost:9090/communityEmail?city=<city>

        Cette url doit retourner les adresses mail de tous les habitants de la ville.
*/

@Data
public class EmailCoveredByCityDTO {

 List<Persons> persons;

    /**
     * The Id.
     */

    private Long id;

    /**
     * The First name.
     */

    private String firstName;

    /**
     * The Last name.
     */

    private String lastName;

    /**
     * The Address.
     */

    private String address;

    /**
     * The City.
     */

    private String city;

    /**
     * The Zip.
     */

    private Integer zip;

    /**
     * The Phone.
     */

    private String phone;

    /**
     * The Email.
     */

    private String email;







    /**
     * Instantiates  a new EmailCoveredByCity object.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     * @param address   the address
     * @param city      the city
     * @param zip       the zip
     * @param phone     the phone
     * @param email     the email
     */
    public EmailCoveredByCityDTO(

    ) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.persons= persons;

    }




} //END
