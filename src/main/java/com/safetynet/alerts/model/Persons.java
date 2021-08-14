package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persons Model
 */

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "persons")

public class Persons {

    /**
     * Instantiates a new Person.
     */
    public Persons() {
    }

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    /**
     * The First name.
     */
    @NotBlank(message = "First name field cannot be empty")
    @Column(name = "first_name")
    @JsonProperty("")
    private String firstName;

    /**
     * The Last name.
     */
    @NotBlank(message = "Last name field cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    /**
     * The Address.
     */
    @NotBlank(message = "Address field cannot be empty")
    @Column(name = "address")
    private String address;

    /**
     * The City.
     */
    @NotBlank(message = "City field cannot be empty")
    @Column(name = "city")
    private String city;

    /**
     * The Zip.
     */
    @NotNull(message = "Zip field cannot be empty")
    @Column(name = "zip")
    private Integer zip;

    /**
     * The Phone.
     */
    @NotBlank(message = "Phone field name cannot be empty")
    @Column(name = "phone")
    private String phone;

    /**
     * The Email.
     */
    @NotBlank(message = "Email field cannot be empty")
    @Column(name = "email")
    private String email;


    /**
     * Instantiates  a new Person.
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
    public Persons(Long id,
                    @NotBlank(message = "First name field cannot be empty") String firstName,
                    @NotBlank(message = "Last name field cannot be empty") String lastName,
                    @NotBlank(message = "Address field cannot be empty") String address,
                    @NotBlank(message = "City field cannot be empty") String city,
                    @NotNull(message = "Zip field cannot be empty") Integer zip,
                    @NotBlank(message = "Phone field name cannot be empty") String phone,
                    @NotBlank(message = "Email field cannot be empty") String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }


}





