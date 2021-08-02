package com.safetynet.model;

import lombok.Data;

import javax.persistence.*;


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

    //ArrayList<String> medications = new ArrayList<String>();

    //ArrayList<String> allergies = new ArrayList<>();

}





