package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.CommunityEmailByCityDTO;
import com.safetynet.alerts.model.dto.PersonInfoCoveredByFirstNameAndLastNameDTO;
import com.safetynet.alerts.model.dto.PersonsCoveredByFireStationAddressDTO;
import com.safetynet.alerts.model.dto.PersonsCoveredByFireStationStationNumberDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     *                  @param lastName : parameter to choose a person
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

    // 2021-09-04

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


    // 2021-08-27

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

    // DTO

    /**
     * Find person by station.
     *
     * @param station the number of the station
     * @return list of persons covered by station number
     */
    public PersonsCoveredByFireStationStationNumberDTO findPersonByStationDTO(Integer station) {

        // pointer sur la caserne qui porte le numéro demandé
        FireStations fireStation1 = dtoFireStationsRepository.findByStation(station).get(0);

        // récupérer l'adresse correspondant au numéro de la caserne.
        String addressFireStation= fireStation1.getAddress();

        // chercher les personnes qui ont cette adresse
        PersonsCoveredByFireStationStationNumberDTO personCovered = new PersonsCoveredByFireStationStationNumberDTO();
        personCovered.setPersons(dtoPersonsRepository.findPersonByAddress(addressFireStation));

        int count_child=0;
        int count_adult=0;

        Date date = new Date();
        int actualYear = date.getYear();

        for (Persons p : personCovered.getPersons() ) {

            if (
                    actualYear - medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(),p.getLastName()).getBirthDate().getYear() <= 18)

            {
                count_child++;
            }
            else {
                count_adult++;
            }
        }

        personCovered.setCount_adult(count_adult);
        personCovered.setCount_child(count_child);

        // renvoyer la liste personCovered avec le nombre d'enfants et d'adultes

        return personCovered;

    }


    /**
     * find email for persons living in a given city
     * @param city
     * @return
     */
    public CommunityEmailByCityDTO findEmailByCityDTO(String city) {

        // récupérer tous les emails des personnes vivant dans une ville donnée
        CommunityEmailByCityDTO emailCity = new CommunityEmailByCityDTO();
        emailCity.setPersons(dtoPersonsRepository.findEmailByCity(city));

        return emailCity;
    }

    /**
     * Find PersonInfo from given firstName and Lastname
     * @param firstName
     * @param lastName
     * @return personInfo
     */
    public PersonInfoCoveredByFirstNameAndLastNameDTO findFirstNameAndLastNameDTO(String firstName, String lastName) {

        // récupérer les données personnelles des personnes ainsi que les données médicales à partir du firstName et du lastName
        PersonInfoCoveredByFirstNameAndLastNameDTO personInfo = new PersonInfoCoveredByFirstNameAndLastNameDTO();

        personInfo.setPersons(dtoPersonsRepository.findPersonInfoByFirstNameAndLastName(firstName, lastName));
        personInfo.setMedicalRecords(dtoMedicalRecordsRepository.findMedicalRecordsByFirstNameAndLastName(firstName, lastName));

        return personInfo;
    }



    /*http://localhost:9090/fire?address=<address>
    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.*/

    /**
     *
     * @param address
     * @return
     */
    public PersonsCoveredByFireStationAddressDTO findPersonsCoveredByAddress(String address) {

       // récupérer la liste des habitants vivant à une addresse donnée
        PersonsCoveredByFireStationAddressDTO personsList = new PersonsCoveredByFireStationAddressDTO();

        // remplir la liste avec les habitants vivant à l'addresse
        personsList.setPersons(dtoPersonsRepository.findPersonsCoveredByAddress(address));

        //récupérer le numéro de la caserne associée à l'addresse
        personsList.setFireStations(dtoFireStationsRepository.findFireStationByAddress(address));

        //récupérer les antécedents médicaux

        for (Persons persons : personsList.getPersons()
             ) {

            String firstName = persons.getFirstName();
            String lastName = persons.getLastName();

            personsList.setMedicalRecords(dtoMedicalRecordsRepository.findMedicalRecordsByFirstNameAndLastName(firstName,lastName));

        }

        return personsList;
     }


}


