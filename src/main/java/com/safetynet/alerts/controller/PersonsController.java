package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
import javassist.NotFoundException;
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

    public PersonsController (PersonsService personsService){

        this.personsService = personsService;
    }

    /**
     * Read - Get all persons
     * @return - An Iterable object of persons full filled
     */
    @GetMapping("/persons")
    public ResponseEntity<Iterable<Persons>> getPersons() {

       return ResponseEntity.ok(personsService.getPersons());
    }

    /**
     * Read - Get one person
     * @param id The id of the person
     * @return A person object full filled
     */
    @GetMapping("/persons/{id}")
    public ResponseEntity<Persons> getPersons(@PathVariable("id") final Long id) {
        Optional<Persons> persons = personsService.getPersons(id);

        if(persons.isPresent()) {
            return ResponseEntity.ok(persons.get());
        } else {
            return null;
        }
    }


    /**
     * Delete - Delete persons buy its id
     *
     * @param id - The id of the persons to delete
     */
    @DeleteMapping("/persons/{id}")
    public void deletePersons(@PathVariable("id") final Long id) {

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
     * @throws ResourceNotFoundException
     */
    @PutMapping("/persons/{id}")
    public ResponseEntity<Persons> updatePersonsById(
            @PathVariable("id") Long id,
            @RequestBody Persons personsDetails) {

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

            personsService.updatePersonsByFirstNameAndLastName(firstName, lastName, personsDetails);

            return ResponseEntity.ok(personsDetails);
    }

} // END
