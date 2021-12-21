package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


   // @Autowired
    // private FireStationsRepository fireStationsRepository;


    // @Autowired
    // private MedicalRecordsRepository medicalRecordsRepository;

    // @Autowired
    // private DtoPersonsRepository dtoPersonsRepository;

    // @Autowired
    // private DtoMedicalRecordsRepository dtoMedicalRecordsRepository;
    // @Autowired
    // private DtoFireStationsRepository dtoFireStationsRepository;

    //Logger
    private static final Logger logger = LogManager.getLogger("PersonsService");

    /**
     * Choose a person in the Repository
     *
     * @param id : parameter to choose a person
     * @return the information for a person
     */
    public Optional<Persons> getPersons(final Long id) {

        logger.info("Choose a person in the repository");

        return personsRepository.findById(id);

    }

    /**
     * Retrieve all the persons in the Repository
     *
     * @return the informations of all the persons
     */
    public Iterable<Persons> getPersons() {

        logger.info("Retrieve all the persons in the repository");

        return personsRepository.findAll();
    }

    /**
     * Choose a person in the Repository by its first and lastname
     *
     * @param firstName : parameter to choose a person
     * @param lastName  : parameter to choose a person
     * @return the information for a person
     */
    public Persons getPersonsFirstLastName(final String firstName, final String lastName) {

        logger.info("Choose a person in the repository by its first and lastname");

        return personsRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * delete a person by its id
     *
     * @param id to choose a person to delete into the repository
     */
    public boolean deletePersons(final Long id) {

        logger.info("Delete a person by its Id");

        personsRepository.deleteById(id);

        return true;
    }

    /**
     * delete a person by its firstname and lastname
     *
     * @param firstName
     * @param lastName
     */
    public void deletePersonByFirstNameAndLastName(String firstName, String lastName) {

        logger.info("Delete a person by its firstname and lastname");

        Persons personToDelete = personsRepository.findByFirstNameAndLastName(firstName, lastName);
        personsRepository.deleteById(personToDelete.getId());
    }

    /**
     * Save a person in the Repository
     *
     * @param personsDetails : all of a person's informations
     * @return all the informations into the Repository
     */

    public Persons savePersons(Persons personsDetails) {

        logger.info("Save a person in the repository");

        Persons personToSave = new Persons();

        String firstName = personsDetails.getFirstName();
        if (firstName != null) {
            personToSave.setFirstName(firstName);
        }

        String lastName = personsDetails.getLastName();
        if (lastName != null) {
            personToSave.setLastName(lastName);
        }

        String address = personsDetails.getAddress();
        if (address != null) {
            personToSave.setAddress(address);
        }

        Integer zip = personsDetails.getZip();
        if (zip != null) {
            personToSave.setZip(zip);
        }

        String city = personsDetails.getCity();
        if (city != null) {
            personToSave.setCity(city);
        }

        String email = personsDetails.getEmail();
        if (email != null) {
            personToSave.setEmail(email);
        }

        String phone = personsDetails.getPhone();
        if (phone != null) {
            personToSave.setPhone(phone);
        }

        return personsRepository.save(personToSave);
    }


    /**
     * update a person by its id
     *
     * @param id
     * @param personsDetails
     * @return persons
     */
    public Persons updatePersonsById(Long id, Persons personsDetails) {
        try {

            logger.info("Update a person by its Id");

            Optional<Persons> optionalPerson = personsRepository.findById(id);

            if (optionalPerson.isPresent()) {

                Persons personToUpdate = optionalPerson.get();

                String firstName = personsDetails.getFirstName();
                if (firstName != null) {
                    personToUpdate.setFirstName(firstName);
                }

                String lastName = personsDetails.getLastName();
                if (lastName != null) {
                    personToUpdate.setLastName(lastName);
                }

                String address = personsDetails.getAddress();
                if (address != null) {
                    personToUpdate.setAddress(address);
                }

                Integer zip = personsDetails.getZip();
                if (zip != null) {
                    personToUpdate.setZip(zip);
                }

                String city = personsDetails.getCity();
                if (city != null) {
                    personToUpdate.setCity(city);
                }

                String email = personsDetails.getEmail();
                if (email != null) {
                    personToUpdate.setEmail(email);
                }

                String phone = personsDetails.getPhone();
                if (phone != null) {
                    personToUpdate.setPhone(phone);
                }

                return personsRepository.save(personToUpdate);
            }

        } catch (Exception e) {

            logger.error("Person doesn't exist");

        }

        return null;
    }

    /**
     * modify a person by its firstname and lastname
     *
     * @param firstName
     * @param lastName
     */
    public Persons updatePersonsByFirstNameAndLastName(
            String firstName,
            String lastName,
            Persons personsDetails) {

        try {

            logger.info("Modify a person by its first and lastname");

            Persons personToUpdate = personsRepository.findByFirstNameAndLastName(firstName, lastName);

            String address = personsDetails.getAddress();
            if (address != null) {
                personToUpdate.setAddress(address);
            }

            Integer zip = personsDetails.getZip();
            if (zip != null) {
                personToUpdate.setZip(zip);
            }

            String city = personsDetails.getCity();
            if (city != null) {
                personToUpdate.setCity(city);
            }

            String email = personsDetails.getEmail();
            if (email != null) {
                personToUpdate.setEmail(email);
            }

            String phone = personsDetails.getPhone();
            if (phone != null) {
                personToUpdate.setPhone(phone);
            }

            return personsRepository.save(personToUpdate);

        } catch (Exception exception) {

            logger.error("Person doesn't exist");
        }

        return null;
    }

    /**
     * Save all persons.
     *
     * @param persons the persons
     * @return list of persons saved
     */
    public Iterable<Persons> saveAll(Iterable<Persons> persons) {

        logger.info("Save all persons");

        return personsRepository.saveAll(persons);
    }

}






