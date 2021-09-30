package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Persons Repository
 */

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Long> {

    /**
     * Find a person by its id
     *
     * @param id
     * @return the person
     */
    Optional<Persons> findById(Long id);


// 2021-08-26
    /**
     * Find by first name and last name a person
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the person
     */
    Persons findByFirstNameAndLastName(String firstName, String lastName);


    //2021-08-19

    /**
     * Delete a person by its first name and last name
     * @param firstName
     * @param lastName
     * @return
     */
    Persons deleteByFirstNameAndLastName(String firstName, String lastName);




//2021-09-16
    // DTO

    /**
     * Find person by station address iterable.
     *
     * @param address of the station
     * @return list of person covered by station number
     */
    List<Persons> findPersonByAddress(String address);

} //END





