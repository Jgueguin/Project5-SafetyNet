package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * POJO wrapper.
 */
@Data
public class ImportData {

    /**
     * Persons
     */
    List<Persons> persons;

    /**
     * Fire stations.
     */
    @JsonProperty("firestations")
    private List<FireStations> fireStations;

    /**
     * Medical records.
     */
    @JsonProperty("medicalrecords")
    private List<MedicalRecords> medicalRecords;
}
