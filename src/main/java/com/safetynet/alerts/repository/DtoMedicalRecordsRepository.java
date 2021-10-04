package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dto Repository
 */

@Repository
public interface DtoMedicalRecordsRepository extends CrudRepository<MedicalRecords, Long> {


    /**
     *
     * @param firstName
     * @param lastName
     * @return
     */
    List<MedicalRecords> findMedicalRecordsByFirstNameAndLastName(String firstName, String lastName);




} //END






