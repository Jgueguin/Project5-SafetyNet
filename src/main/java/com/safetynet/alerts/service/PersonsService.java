package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import com.safetynet.alerts.service.PersonsService;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


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
     *
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


    /**
     * Save a person in the Repository
     *
     * @param persons : all of a person's informations
     * @return all the informations into the Repository
     */
    public Persons savePersons(Persons persons) {

        Persons savedPersons = personsRepository.save(persons);

        return savedPersons;
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


    /**
     *
     * @param persons
     * @return
     */
    public static Persons saveUpdated(Persons persons) {
        return personsRepository.save(persons);
    }
    //2021-08-19 update

    /**
     * Update a person.( Put )
     *
     * @param personsBody
     * @param personsToUpdate
     * @return
     */
    public static Persons updatePersons(Persons personsBody, Persons personsToUpdate) {
        personsToUpdate.setAddress(personsBody.getAddress());
        personsToUpdate.setCity(personsBody.getCity());
        personsToUpdate.setEmail(personsBody.getEmail());
        personsToUpdate.setPhone(personsBody.getPhone());
        personsToUpdate.setZip(personsBody.getZip());

        return personsToUpdate;
    }





    /**
     * Find a person by first name and last name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the person
     * @throws NotFoundException if noone was found
     */
    public Persons findByFirstNameAndLastName(String firstName,
                                              String lastName) throws NotFoundException {
        LOGGER.info("PersonService -> Searching for person " + firstName + " "+ lastName + " ...");

        Persons persons = personsRepository.findByFirstNameAndLastName(firstName, lastName);

        if (persons == null) {
            LOGGER.info("PersonService -> " + firstName + " " + lastName
                    + " doesn't exist");

            throw new NotFoundException(
                    "Person " + firstName + " " + lastName + " doesn't exist");
        }
        LOGGER.info("PersonService -> Person " + firstName + " " + lastName
                + " was found");
        return persons;
    }


    //2021-08-25


    public Persons findById(Long id) throws NotFoundException {
        // LOGGER.info("PersonService -> Searching for person " + firstName + " " + lastName + " ...");

        Persons persons = personsRepository.findById(id);

        Persons personsToUpdate = PersonsService.findById(id);

        Persons personUpdated = PersonsService.updatePersons(persons,personsToUpdate);
        final Persons personsSaved = PersonsService.saveUpdated(personsToUpdate);

        return persons;


      /*  if (persons == null) {
            LOGGER.info("PersonService -> " + firstName + " " + lastName
                    + " doesn't exist");

            throw new NotFoundException(
                    "Person " + firstName + " " + lastName + " doesn't exist");
        }
        LOGGER.info("PersonService -> Person " + firstName + " " + lastName
                + " was found");
        return persons;*/
    }














/*
    *//**
     * Find persons covered by address and convert theme into a PersonFireDTO list.
     *
     * @param address the address
     * @return PersonFireDTO list
     *//*


    public List<PersonFireDTO> getFireDtoListByStation(String address) {
        List<PersonFireDTO> personFireDTOList = new ArrayList<>();
        List<Integer> listOfStations = FireStationsService.  findStationByAddress(address);

        List<MedicalRecord> medicalRecords = new ArrayList<>();


        for (Integer integer : listOfStations) {
            List<Person> personsCovered = findPersonByStation(integer);
            for (Person person : personsCovered) {
                MedicalRecord medicalRecord = medicalRecordService.findByFirstNameAndLastName(
                        person.getFirstName(), person.getLastName());
                if (medicalRecord != null)
                    medicalRecords.add(medicalRecord);
            }
            List<PersonFireDTO> personFireDTOListToAdd = mapping.convertPersonListToPersonFireList(
                    personsCovered, integer, medicalRecords);
            personFireDTOList.addAll(personFireDTOListToAdd);

        }
        return personFireDTOList;
    }*/






}


