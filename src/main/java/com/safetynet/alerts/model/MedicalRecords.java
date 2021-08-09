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
     * instance of a new Medical record
     */
    public MedicalRecords() {
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
    @Column(name = "first_name")
    private String firstName;

    /**
     * The Last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The Birth date.
     */
    @Column(name = "birth_date")
    @JsonProperty("birthdate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    /**
     * The Medications.
     */
    @Column(name = "medications")
    private String[] medications;


    /**
     * The Allergies.
     */
    @Column(name = "allergies")
    private String[] allergies;

}

