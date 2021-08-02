package com.safetynet.project5.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "persons")
//@JsonFilter("monFiltreDynamique")
class Persons {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String adress;
    private String city;
    private Long zip;
    private String phone;
    private String email;

}

@Data
@Entity
@Table(name = "firestations")
//@JsonFilter("monFiltreDynamique")
class Firestations {

    @Id
    @GeneratedValue
    private int id;

    private String adress;
    private Long station;

}

@Data
@Entity
@Table(name = "medical_records")
        //@JsonFilter("monFiltreDynamique")
class Medicalrecords {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String birthdate;

    //ArrayList<String> medications = new ArrayList<String>();

    //ArrayList<String> allergies = new ArrayList<>();


}





