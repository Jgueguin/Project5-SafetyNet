package com.safetynet.model;

import lombok.Data;

import javax.persistence.*;


/**
 * Medical Records Model
 */

@Data
@Entity
@Table(name = "medical_records")
        //@JsonFilter("monFiltreDynamique")
public class MedicalRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String birthdate;

    @Embedded
    private Medications medications;

    @Embedded
    private Allergies allergies;

    public MedicalRecords() {}

}

