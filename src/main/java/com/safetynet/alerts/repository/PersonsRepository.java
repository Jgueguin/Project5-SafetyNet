package com.safetynet.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Persons;

/**
 * Persons Repository
 */

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Long> {

}

