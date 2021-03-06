package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Fire Stations Model
 */

@Data
@Entity
@Table(name = "firestations")
public class FireStations {

    /**
     * The Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    /**
     * The Station
     */
    @NotNull(message = "Station number cannot be empty")
    @Column(name = "station")
    private Integer station;

    /**
     * The Address
     */
    @NotBlank(message = "Address field cannot be empty")
    @Column(name = "address")
    private String address;

    /**
     * Instantiates a new Fire Station.
     */
    public FireStations() {
    }

    /**
     * Instantiates a new Firestation.
     *
     * @param id      the id
     * @param station the station
     * @param address the address
     */
    public FireStations(Long id,
                        @NotNull(message = "Station number cannot be empty") Integer station,
                        @NotBlank(message = "Address field cannot be empty") String address) {
        this.id = id;
        this.station = station;
        this.address = address;
    }


}





