package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * Medical Records Model
 */

@Data
@Entity
@Table(name = "medicalrecords")

public class MedicalRecords {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    /**
     * First name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Birth date
     */
    @Column(name = "birth_date")
    @JsonProperty("birthdate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    /**
     * Medications
     */
    @Column(name = "medications")
    private String[] medications;


    /**
     * Allergies
     */
    @Column(name = "allergies")
    private String[] allergies;


    /**
     * instance of a new Medical record
     */
    public MedicalRecords() {
    }

    /**
     * Instantiates a new Medical record.
     *
     * @param id          the id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param birthDate   the birth date
     * @param medications the medications
     * @param allergies   the allergies
     */
    public MedicalRecords(Long id, String firstName, String lastName,
                         Date birthDate, String[] medications, String[] allergies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.medications = medications;
        this.allergies = allergies;
    }

}

