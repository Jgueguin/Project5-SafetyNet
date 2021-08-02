package com.safetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.model.medicalRecords;

@Repository
public interface MedicalRecordsRepository extends CrudRepository<medicalRecords, Long> {

}

