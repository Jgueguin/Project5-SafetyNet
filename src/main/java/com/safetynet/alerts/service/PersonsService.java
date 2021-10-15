package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.*;
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
    @Autowired
    private FireStationsRepository fireStationsRepository;
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;
    @Autowired
    private DtoMedicalRecordsRepository dtoMedicalRecordsRepository;
    @Autowired
    private DtoFireStationsRepository dtoFireStationsRepository;


    /**
     * Choose a person in the Repository
     *
     * @param id : parameter to choose a person
     * @return the information for a person
     */
    public Optional<Persons> getPersons(final Long id) {
        return personsRepository.findById(id);

    }

    /**
     * Retrieve all the persons in the Repository
     *
     * @return the informations of all the persons
     */
    public Iterable<Persons> getPersons() {
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
        return personsRepository.findByFirstNameAndLastName(firstName, lastName);

    }


    /**
     * delete a person by its id
     *
     * @param id to choose a person to delete into the repository
     */
    public void deletePersons(final Long id) {

        personsRepository.deleteById(id);
    }

    /**
     * delete a person by its firstname and lastname
     *
     * @param firstName
     * @param lastName
     */
    public void deletePersonByFirstNameAndLastName(String firstName, String lastName) {

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
            System.out.println(e);
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
            exception.printStackTrace();
        }
        System.out.println("Return null");
        return null;
    }

    /**
     * Save all persons.
     *
     * @param persons the persons
     * @return list of persons saved
     */
    public Iterable<Persons> saveAll(Iterable<Persons> persons) {

        return personsRepository.saveAll(persons);
    }

}






