package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Medical Records Repository
 */

@Repository
public interface MedicalRecordsRepository extends CrudRepository<MedicalRecords, Long> {

    /**
     * Find by first name and last name medical record.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the medical record
     */
    MedicalRecords findByFirstNameAndLastName(String firstName, String lastName);


   /* *//**
     * find by id medical record
     *
     * @param id
     * @return
     *//*
    Optional<MedicalRecords> findById(Long id);
*/


}

