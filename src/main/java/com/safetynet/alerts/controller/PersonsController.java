package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     *
     * @return - An Iterable object of persons full filled
     */
    @GetMapping("/persons")
    public Iterable<Persons> getPersons() {
        return personsService.getPersons();
    }

    /**
     * Read - Get one person
     * @param id The id of the person
     * @return A person object full filled
     */
    @GetMapping("/persons/{id}")
    public Persons getPersons(@PathVariable("id") final Long id) {
        Optional<Persons> persons = personsService.getPersons(id);
        if(persons.isPresent()) {
            return persons.get();
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
// --> A revoir
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

/*
    *//**
     * Create - Add a new person
     *
     * @param persons: An object employee
     * @return The person object saved
     */
    @PostMapping("/persons")
    public Persons createPersons(@RequestBody Persons personsDetails) {
        return personsService.savePersons(personsDetails);
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
    public Persons updatePersonsById(
            @PathVariable("id") Long id,
            @Valid @RequestBody Persons personsDetails) {

        return personsService.updatePersonsById(id, personsDetails);
    }


/**
     * Modify a person with its firstname and lastname
     * @param firstName
     * @param lastName
     *
     * @throws NotFoundException
     */

    @PutMapping("/persons/{firstName}/{lastName}")

    public Persons modifyPersonsByFirstNameAndLastName(
            @PathVariable(value = "firstName") String firstName,
            @PathVariable(value = "lastName") String lastName,
            @Valid @RequestBody Persons personsDetails ){

        return personsService.updatePersonsByFirstNameAndLastName(firstName, lastName,personsDetails);
    }





    /*public ResponseEntity<Persons> updatePersonsByFirstNameLastName(
            @PathVariable(value = "firstName") String firstName,
            @PathVariable(value = "lastName") String lastName,
            @Valid @RequestBody final Persons persons) throws NotFoundException {

        Persons personsUpdate = personsService.findByFirstNameAndLastName(firstName,lastName);
        Persons personUpdated = personsService.updatePersons(persons, personsUpdate);
        final Persons personSaved = personsService.saveUpdated(personsUpdate);

        LOGGER.info("PersonController (PUT) -> Successfully updated person: "
                + personUpdated.toString());

        return ResponseEntity.ok(personSaved);

    }

*/




} // END






