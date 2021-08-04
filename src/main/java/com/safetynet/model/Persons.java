package com.safetynet.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Persons Model
 */

@Data
@Entity
@Table(name = "persons")
//@JsonFilter("monFiltreDynamique")
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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





