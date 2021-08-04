package com.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Allergies Model
 */

@Data
@AllArgsConstructor
@Embeddable
class Allergies {

    private String allergy;

    public Allergies() {}
}