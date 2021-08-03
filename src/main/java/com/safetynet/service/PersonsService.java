package com.safetynet.service;

import com.safetynet.model.persons;
import com.safetynet.repository.PersonsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Person Service
 */

@Data
@Service
public class PersonsService {
    @Autowired
    private PersonsRepository personsRepository;

    /**
     * Choose a person in the Repository
     * @param id : parameter to choose a person
     * @return the information for a person
     */
    public Optional<persons> getPersons(final Long id) {
        return personsRepository.findById(id);

    }

    /**
     * Retrieve all the persons in the Repository
     * @return the informations of all the persons
     */
    public Iterable<persons> getPersons() {
        return personsRepository.findAll();
    }

    /**
     * delete Service
     * @param id to choose a person to delete into the repository
     */
    public void deletePersons(final Long id) {
        personsRepository.deleteById(id);
    }

    /**
     * Save a person in the Repository
     * @param persons : all of a person's informations
     * @return all the informations into the Repository
     */
    public persons savePersons(persons persons) {
        com.safetynet.model.persons savedPersons = personsRepository.save(persons);
        return savedPersons;
    }
}