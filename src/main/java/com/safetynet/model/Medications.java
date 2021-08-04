package com.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Medications Model
 */

@Data
@AllArgsConstructor
@Embeddable
class Medications {

    private String medic;

    public Medications() {}
}