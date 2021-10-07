package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Medical Records Repository
 */

@Repository
public interface MedicalRecordsRepository extends CrudRepository<MedicalRecords, Long> {



//2021-08-26

    /**
     * Find a medical records by its id
     * @param id
     * @return
     */
    Optional<MedicalRecords> findById(Long id);


    /**
     * Find by first name and last name a medical record
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the medical record
     */
    MedicalRecords findByFirstNameAndLastName(String firstName, String lastName);


    //2021-08-19
    /**
     * Delete a medical record by its first name and last name

     * @param firstName
     * @param lastName
     * @return
     */

    Persons deleteByFirstNameAndLastName(String firstName, String lastName);

    /**
     *
     * @param firstName
     * @param lastName
     * @return
     */
    List<MedicalRecords> findMedicalRecordsByFirstNameAndLastName(String firstName, String lastName);




// End
}

