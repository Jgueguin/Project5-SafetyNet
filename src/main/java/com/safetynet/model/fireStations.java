package com.safetynet.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Fire Stations Model
 */

@Data
@Entity
@Table(name = "firestations")
//@JsonFilter("monFiltreDynamique")
public class fireStations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String adress;
    private Long station;
}





