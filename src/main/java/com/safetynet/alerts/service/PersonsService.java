package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.*;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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


    // DTO

    /**
     * Find person by station.
     *
     * @param station the number of the station
     * @return list of persons covered by station number
     */
    public PersonsCoveredByFireStationStationNumberDTO personByStationDTO(Integer station) {

        // pointer sur la caserne qui porte le numéro demandé
        FireStations fireStation1 = dtoFireStationsRepository.findByStation(station).get(0);

        // récupérer l'adresse correspondant au numéro de la caserne.
        String addressFireStation = fireStation1.getAddress();

        // chercher les personnes qui ont cette adresse
        PersonsCoveredByFireStationStationNumberDTO personCovered = new PersonsCoveredByFireStationStationNumberDTO();
        personCovered.setPersons(dtoPersonsRepository.findPersonByAddress(addressFireStation));

        int count_child = 0;
        int count_adult = 0;

        Date date = new Date();
        int actualYear = date.getYear();

        for (Persons p : personCovered.getPersons()) {

            if (
                    actualYear - medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName()).getBirthDate().getYear() <= 18) {
                count_child++;
            } else {
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
     *
     * @param city
     * @return
     */
    public CommunityEmailByCityListDTO extractEmailByCityDTO(String city) {

        // récupérer tous les habitants vivant dans une ville donnée
        ListPersonDTO emailCity = new ListPersonDTO();
        CommunityEmailByCityListDTO emailList = new CommunityEmailByCityListDTO();

        // récupérer tous les emails des personnes vivant dans une ville donnée
        for (Persons p : dtoPersonsRepository.findEmailByCity(city)) {

            ArrayList<String> tmp = emailList.getEmailArray();
            tmp.add("Firstname: " + p.getFirstName() + " -- Lastname: " + p.getLastName() + "-> Email: " + p.getEmail());

            emailList.setEmailArray(tmp);
        }

        return emailList;
    }


    // http://localhost:9090/personInfo?firstName=<firstName>&lastName=<lastName>

    /**
     * Find PersonInfo from given firstName and Lastname
     *
     * @param firstName
     * @param lastName
     * @return personInfo
     */
    public PersonInfoCoveredByFirstNameAndLastNameListDTO firstNameAndLastNameDTO(String firstName, String lastName) {

        PersonInfoCoveredByFirstNameAndLastNameListDTO personInfoArray = new PersonInfoCoveredByFirstNameAndLastNameListDTO();

        // préparation tableau intermédiaire pour récupérer les données
        ArrayList<String> tmp2 = personInfoArray.getPersonInfoArray();

        for (
                Persons p : dtoPersonsRepository.findPersonInfoByFirstNameAndLastName(firstName, lastName)
        ) {
            tmp2.add(p.getFirstName() + " " + p.getLastName());
            tmp2.add(p.getAddress());

            MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());

            Date date = new Date();
            Date birthdate = medicalRecords.getBirthDate();
            tmp2.add("Age: " + (date.getYear() - birthdate.getYear()));

            tmp2.add(p.getEmail());

            String allergies = String.join(",", medicalRecords.getAllergies());
            tmp2.add("Allergies: " + allergies);

            String medications = String.join(",", medicalRecords.getMedications());
            tmp2.add("Medications: " + medications);

        }
        personInfoArray.setPersonInfoArray(tmp2);


        return personInfoArray;
    }



    /*http://localhost:9090/fire?address=<address>
    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.*/

    public PersonsCoveredByFireStationAddressDTO2 personsCoveredByAddress2(String address) {

        // Création objet de type PersonCoveredByFireStationDTO2
        PersonsCoveredByFireStationAddressDTO2 fireStationsArray = new PersonsCoveredByFireStationAddressDTO2();

        // préparation tableau intermédiaire pour récupérer les données
        ArrayList<String> tmp2 = (ArrayList<String>) fireStationsArray.getFireAddressArray();

        tmp2.add(address);

        FireStations fireStations = fireStationsRepository.findByAddress(address);
        tmp2.add("StationNumber :" + fireStations.getStation().toString());
        tmp2.add("   ");

        for (
                Persons p : dtoPersonsRepository.findPersonsCoveredByAddress(address)
        ) {

            tmp2.add(p.getFirstName() + " " + p.getLastName());
            tmp2.add("Phone : " + p.getPhone());

            MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());

            Date date = new Date();
            Date birthdate = medicalRecords.getBirthDate();
            tmp2.add("Age: " + (date.getYear() - birthdate.getYear()));


            String allergies = String.join(",", medicalRecords.getAllergies());
            tmp2.add("Allergies: " + allergies);

            String medications = String.join(",", medicalRecords.getMedications());
            tmp2.add("Medications: " + medications);

            tmp2.add("       ");

            fireStationsArray.setFireAddressArray(tmp2);

        }

        return fireStationsArray;
    }

    /*http://localhost:8080/phoneAlert?firestation=<firestation_number>

    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.*/

    /**
     * phone Alert
     *
     * @param station
     * @return phoneAlertList
     */
    public PhoneAlertListDTO phoneAlertDTO(Integer station) {

        PhoneAlertListDTO phoneAlertList = new PhoneAlertListDTO();

        // récupération de l'addresse à partir du numero de station
        List<FireStations> fireStations = dtoFireStationsRepository.findByStation(station);

        // mise en place du tableau intermédiaire
        ArrayList<String> tmp = phoneAlertList.getPhoneAlertArray();

        tmp.add("Caserne n°: " + station);

        for (FireStations f : dtoFireStationsRepository.findByStation(station)) {
            tmp.add(f.getAddress());
            for (Persons p : dtoPersonsRepository.findPersonByAddress(f.getAddress())) {
                tmp.add("      " + p.getPhone());

            }

            phoneAlertList.setPhoneAlertArray(tmp);

        }

        return phoneAlertList;
    }



/*

    http://localhost:8080/flood/stations?stations=<a list of station_numbers>

    Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
    personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
    faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.

*/

    public FloodListDTO floodDTO(Integer station) {

        FloodListDTO floodListDto = new FloodListDTO();

        // récupération de l'addresse à partir du numero de station
        List<FireStations> fireStations = dtoFireStationsRepository.findByStation(station);

        // mise en place du tableau intermédiaire
        ArrayList<String> tmp = floodListDto.getFloodArray();

        tmp.add("Caserne n°: " + station);

        for (FireStations f : dtoFireStationsRepository.findByStation(station)) {
            tmp.add(f.getAddress());
            for (Persons p : dtoPersonsRepository.findPersonByAddress(f.getAddress())) {

                MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                Date date = new Date();
                Date birthdate = medicalRecords.getBirthDate();
                Integer age = date.getYear()-birthdate.getYear();

                tmp.add("      " + p.getLastName()+" "+p.getFirstName());
                tmp.add("                  "+p.getPhone());
                tmp.add("                   Age: "+age);

                String allergies = String.join(",", medicalRecords.getAllergies());
                tmp.add("                   Allergies: " + allergies);

                String medications = String.join(",", medicalRecords.getMedications());
                tmp.add("                   Medications: " + medications);

            }

        }
        return floodListDto;
    }

}






