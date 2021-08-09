package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Medical Records Repository
 */

@Repository
public interface MedicalRecordsRepository extends CrudRepository<MedicalRecords, Long> {

}

