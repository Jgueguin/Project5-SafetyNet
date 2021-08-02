package com.safetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.model.persons;

@Repository
public interface PersonsRepository extends CrudRepository<persons, Long> {

}

