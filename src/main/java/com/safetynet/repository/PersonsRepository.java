package com.safetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.model.Persons;

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Long> {

}

