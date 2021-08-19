package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Persons Repository
 */

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Long> {

    /**
     * Find by first name and last name a person
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the person
     */
    Persons findByFirstNameAndLastName(String firstName, String lastName);


}

