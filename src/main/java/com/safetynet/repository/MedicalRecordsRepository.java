package com.safetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.model.MedicalRecords;

/**
 * Medical Records Repository
 */

@Repository
public interface MedicalRecordsRepository extends CrudRepository<MedicalRecords, Long> {

}

