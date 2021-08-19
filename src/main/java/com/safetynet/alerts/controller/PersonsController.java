package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.PersonsService;
import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
     * Delete - Delete persons
     *
     * @param id - The id of the persons to delete
     */
    @DeleteMapping("/persons/{id}")
    public void deletePersons(@PathVariable("id") final Long id) {

        personsService.deletePersons(id);
    }

    /**
     * Create - Add a new person
     *
     * @param persons: An object employee
     * @return The person object saved
     */
    @PostMapping("/persons")
    public Persons createPersons(@RequestBody Persons persons) {

        return personsService.savePersons(persons);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Persons> updatePersons(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody Persons personsDetails) throws ResourceNotFoundException {

        Persons personsUpdate = personsService.getPersons(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ id));

        personsUpdate.setFirstName(personsDetails.getFirstName());
        personsUpdate.setLastName(personsDetails.getLastName());
        personsUpdate.setAddress(personsDetails.getAddress());
        personsUpdate.setCity(personsDetails.getCity());
        personsUpdate.setZip(personsDetails.getZip());
        personsUpdate.setPhone(personsDetails.getPhone());
        personsUpdate.setEmail(personsDetails.getEmail());

        final Persons updatedPersons = personsService.savePersons(personsUpdate);

        return ResponseEntity.ok(updatedPersons);
    }








}

