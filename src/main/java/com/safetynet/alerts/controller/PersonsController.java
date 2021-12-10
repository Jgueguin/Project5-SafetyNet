package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Person Controller
 */

@RestController
public class PersonsController {

    @Autowired
    private PersonsService personsService;

    //Logger
    private static final Logger logger = LogManager.getLogger("PersonsController");


    public PersonsController (PersonsService personsService){

        logger.info("--> Instantiates a new controller");

        this.personsService = personsService;
    }

    /**
     * Read - Get all persons
     * @return - An Iterable object of persons full filled
     */
    @GetMapping("/persons")
    public ResponseEntity<Iterable<Persons>> getPersons() {

        logger.info("Get Mapping - Get all persons");

       return ResponseEntity.ok(personsService.getPersons());
    }

    /**
     * Read - Get one person
     * @param id The id of the person
     * @return A person object full filled
     */
    @GetMapping("/persons/{id}")
    public ResponseEntity<Persons> getPersons(@PathVariable("id") final Long id) {

        logger.info("Get Mapping - Get one persons with its id");

        Optional<Persons> persons = personsService.getPersons(id);

        if(persons.isPresent()) {
            return ResponseEntity.ok(persons.get());
        } else {
            return null;
        }
    }

    /**
     * Get one persons with its firstname and lastname
     * @param firstname
     * @param lastname
     * @return
     */
    @GetMapping("/pers/{firstname}/{lastname}")
    public ResponseEntity<Persons> getFireStationsById(
            @PathVariable("firstname") final String firstname,
            @PathVariable("lastname") final String lastname) {
        Persons personsFirstLastName = (personsService.getPersonsFirstLastName(firstname, lastname));

        logger.info("Get Mapping - Get one persons with its firstname and lastname");

        return ResponseEntity.ok(personsFirstLastName);
    }

    /**
     * Delete - Delete persons buy its id
     *
     * @param id - The id of the persons to delete
     */
    @DeleteMapping("/persons/{id}")
    public void deletePersons(@PathVariable("id") final Long id) {

        logger.info("DeleteMapping - Delete one persons with its id");

        personsService.deletePersons(id);
    }

    // 2021-08-19

    /**
     * Delete - Delete persons with its firstname and lastname
     *
     * @param
     */
    @DeleteMapping("/persons/{firstName}/{lastName}")
    public void deletePersonsByFirstNameAndLastName(
            @PathVariable("firstName") final String firstName,
            @PathVariable("lastName") final String lastName)
    {
        logger.info("DeleteMapping - Delete a person with its firstname and lastname");

        personsService.deletePersonByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Create - Add a new person
     * @param personsDetails : an abject person
     * @return the person object saved
     */
    @PostMapping("/persons")
    public ResponseEntity<Persons> createPersons(
            @RequestBody Persons personsDetails) {

        logger.info("Create mapping - create a new person");

        personsService.savePersons(personsDetails);

        return ResponseEntity.ok(personsDetails);
    }

    //2021-08-19 update

    /**
     * Modify a person with its id
     *
     * @param id
     * @param personsDetails
     * @return
     */
    @PutMapping("/persons/{id}")
    public ResponseEntity<Persons> updatePersonsById(
            @PathVariable("id") Long id,
            @RequestBody Persons personsDetails) {

            logger.info("PutMapping - modify a person with its id");

            personsService.updatePersonsById(id, personsDetails);

            return ResponseEntity.ok(personsDetails);
    }


/**
     * Modify a person with its firstname and lastname
     * @param firstName
     * @param lastName
     *
     * @throws NotFoundException
     */
    @PutMapping("/persons/{firstName}/{lastName}")
    public ResponseEntity<Persons> modifyPersonsByFirstNameAndLastName(
            @PathVariable(value = "firstName") String firstName,
            @PathVariable(value = "lastName") String lastName,
            @RequestBody Persons personsDetails ){

            logger.info("Put mapping - modify a person with its firstname and lastname");

            personsService.updatePersonsByFirstNameAndLastName(firstName, lastName, personsDetails);

            return ResponseEntity.ok(personsDetails);
    }


} // END
