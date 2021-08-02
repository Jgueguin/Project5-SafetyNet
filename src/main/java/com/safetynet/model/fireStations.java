package com.safetynet.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "firestations")
//@JsonFilter("monFiltreDynamique")
public class FireStations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String adress;
    private Long station;
}





