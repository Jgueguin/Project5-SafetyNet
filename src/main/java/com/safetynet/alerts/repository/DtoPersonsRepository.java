package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dto Repository
 */

@Repository
public interface DtoPersonsRepository extends CrudRepository<Persons, Long> {

    /**
     * Find person by station address iterable.
     *
     * @param address of the station
     * @return list of person covered by station number
     */
    List<Persons> findPersonByAddress(String address);


    /**
     * Find email by a given city
     *
     * @param city of the station
     * @return list of person covered by station number
     */
    List<Persons> findEmailByCity(String city);


    /**
     * find person Info by firstname and lastname
     * @param firstName
     * @param lastName
     * @return
     */
    List<Persons> findPersonInfoByFirstNameAndLastName(String firstName, String lastName);


    /**
     *
     * @param firstName
     * @param lastName
     * @return
     */
    List<MedicalRecords> findMedicalRecordsByFirstNameAndLastName(String firstName, String lastName);




} //END






