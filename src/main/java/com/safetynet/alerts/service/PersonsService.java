package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.PersonCoveredByFireStation;
import com.safetynet.alerts.repository.FireStationsRepository;
import com.safetynet.alerts.repository.PersonsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
     * delete a person by its id
     * @param id to choose a person to delete into the repository
     */
    public void deletePersons(final Long id) {

        personsRepository.deleteById(id);
    }

    // 2021-08-19

    /**
     * delete a person by its firstname and lastname
     *
     * @param firstName
     * @param lastName
     */
    public void deletePersonByFirstNameAndLastName(String firstName, String lastName) {

        personsRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }


    // 2021-08-27
    /**
     * Save a person in the Repository
     *
     * @param personsDetails : all of a person's informations
     * @return all the informations into the Repository
     */

    public Persons savePersons(Persons personsDetails) {

        Persons personsToSave = new Persons();

        personsToSave.setFirstName(personsDetails.getFirstName());
        personsToSave.setLastName(personsDetails.getLastName());
        personsToSave.setAddress(personsDetails.getAddress());
        personsToSave.setCity(personsDetails.getCity());
        personsToSave.setZip(personsDetails.getZip());
        personsToSave.setPhone(personsDetails.getPhone());
        personsToSave.setEmail(personsDetails.getEmail());

            // faire la même chose pour les reste des attributs
            // et vérifier qu'il ne soit pas nuls

            return personsRepository.save(personsToSave);
        }


    //2021-08-26

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

                Persons personToSave = optionalPerson.get();

                personToSave.setLastName(personsDetails.getLastName());
                personToSave.setFirstName(personsDetails.getFirstName());
                personToSave.setAddress(personsDetails.getAddress());
                personToSave.setZip(personsDetails.getZip());
                personToSave.setCity(personsDetails.getCity());
                personToSave.setEmail(personsDetails.getEmail());
                personToSave.setPhone(personsDetails.getPhone());

                // et vérifier qu'il ne soit pas nuls et Try Catch

                return personsRepository.save(personToSave);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }


    //2021-08-26

    /**
     * modify a person by its firstname and lastname
     *
     * @param firstName
     * @param lastName
     */
    public Persons updatePersonsByFirstNameAndLastName(
            String firstName,
            String lastName,
            @Valid Persons personsDetails) {
        try {

            Persons person = personsRepository.findByFirstNameAndLastName(firstName, lastName);

            person.setAddress(personsDetails.getAddress());
            person.setZip(personsDetails.getZip());
            person.setCity(personsDetails.getCity());
            person.setEmail(personsDetails.getEmail());
            person.setPhone(personsDetails.getPhone());

            // et vérifier qu'il ne soit pas nuls

            return personsRepository.save(person);

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


// 2021-08-31


    public List<PersonCoveredByFireStation> getPersonListByStation (String address) {

        List<PersonCoveredByFireStation> personFireList = new ArrayList<>();





        List<Integer> listOfStations =  FireStationsRepository.findStationByAdrress(address);

        List<MedicalRecords> medicalRecords = new ArrayList<>();

        for (Integer numberOfStation : listOfStations) {



            List<Persons> personsCovered = findPersonByStation(numberOfStation);

            for (Persons persons : personsCovered) {
                MedicalRecords medicalRecordsCov = MedicalRecordsService.findByFirstNameAndLastName(
                        persons.getFirstName(),
                        persons.getLastName());
                if (medicalRecords != null)

                    medicalRecords.add(medicalRecordsCov);
            }

        }

        return personFireList;
    }

    }





}


