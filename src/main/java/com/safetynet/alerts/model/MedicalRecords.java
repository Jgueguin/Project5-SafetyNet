package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;


/**
 * Medical Records Model
 */

@Data

@Entity
@Table(name = "medicalrecords")

public class MedicalRecords {

    /**
     * The Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    /**
     * The First name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The Last name
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The Birth date
     */
    @Column(name = "birth_date")
    @JsonProperty("birthdate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Calendar birthDate;

    /**
     * The Medications
     */
    @Column(name = "medications")
    @JsonProperty("medications")
    private String[] medications;


    /**
     * The Allergies
     */
    @Column(name = "allergies")
    @JsonProperty("allergies")
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
    public MedicalRecords(Long id,
                          String firstName,
                          String lastName,
                          Calendar birthDate,
                          String[] medications,
                          String[] allergies) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.medications = medications;
        this.allergies = allergies;

    }

    @ToString.Include
    public String toString() {
        return "[id=" + this.id + " firstname=" + this.firstName + " lastname=" + this.lastName + " " + this.birthDate + " Medications=" + this.medications + " Allergies" + this.allergies
                + "]";
    }
}

