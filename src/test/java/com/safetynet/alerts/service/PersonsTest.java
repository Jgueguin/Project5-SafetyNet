package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)


public class PersonsTest {

    // Dto
    @InjectMocks
    @Autowired
    private  PersonsService personsService;

    @Mock
    private PersonsRepository personsRepository;

    private static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    private static Persons person2 = new Persons(2L, "first_2", "Last_2", "Address_1", "City_2", 22222, "222-222-2222", "two@email.com");
    private static Persons person3 = new Persons(3L, "first_3", "Last_3", "Address_1", "City_3", 33333, "333-333-3333", "three@email.com");
    private static Persons person4 = new Persons(4L, "first_4", "Last_4", "Address_1", "City_4", 33333, "444-444-4444", "four@email.com");

    private static Persons person5 = new Persons(5L, "first_5", "Last_5", "Address_5", "City_5", 55555, "555-555-55555", "five@email.com");

    private static Persons person6 = new Persons(6L, "first_6", "Last_6", "Address_6", "City_6", 66666, "666-666-66666", "six@email.com");

    List<Persons> personsList = new ArrayList<>();
    List<Persons> personsList2 = new ArrayList<>();
    List<Persons> personsList3 = new ArrayList<>();

    @Before
    public void setUp() {

        //add persons into list of persons
        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        personsList.add(person4);

        //add persons into list2 of persons
        personsList2.add(person1);
        personsList2.add(person2);
        personsList2.add(person3);

        //add persons into list3 of persons
        personsList3.add(person1);
        personsList3.add(person2);
        personsList3.add(person3);
        personsList3.add(person4);
        personsList3.add(person5);
    }

    @Test
    @DisplayName("Get Person by Id Test")
    public void getPersons_By_Id_Test() {

        when(personsRepository.findById(any())).thenReturn(Optional.ofNullable(person1));

        assertEquals(Optional.ofNullable(person1), personsService.getPersons(1L));
    }

    @Test
    @DisplayName("Get Person by Id Test")
    public void getPersons_By_Id_Test2() {

        when(personsRepository.findById(any())).thenReturn(Optional.ofNullable(person1));

        personsService.getPersons(1L);

        assertEquals(Optional.ofNullable(person1), personsService.getPersons(1L));
    }

    @Test
    @DisplayName("Get All Persons ")
    public void getAllPersons_Test() {

        when(personsRepository.findAll()).thenReturn(personsList);
        assertEquals(personsList, personsService.getPersons());
    }

    @Test
    @DisplayName("Get All Persons ")
    public void getAllPersons_Test2() {

        when(personsRepository.findAll()).thenReturn(personsList);
        assertEquals(personsList, personsService.getPersons());
    }


    @Test
    @DisplayName("Get a Person with First Name and LastName ")
    public void getPersonsByFirstLastName_Test() {

        when(personsRepository.findByFirstNameAndLastName(any(),any())).thenReturn(person1);
        assertEquals(person1, personsService.getPersonsFirstLastName(person1.getFirstName(), person1.getLastName()));
    }

    @Test
    @DisplayName("Delete a Person with its ID ")
    public void deletePersons_Test() {

        personsService.deletePersons(4L);

        int newListSize = personsList.size()-1;
        assertEquals(newListSize, personsList2.size());
    }

    @Test
    @DisplayName("Delete a Person with its firstname and lastname")
    public void deletePersonsWithFirstAndLastName_Test() {

        when(personsRepository.findByFirstNameAndLastName(any(),any())).thenReturn(person4);

        personsService.deletePersonByFirstNameAndLastName(person4.getFirstName(), person4.getLastName());

        int newListSize = personsList.size()-1;
        assertEquals(newListSize, personsList2.size());
    }

    @Test
    @DisplayName("Save a person")
    public void savePersons_Test() {

        when(personsRepository.save(any())).thenReturn(person5);

        personsService.savePersons(person5);

        assertEquals(person5,personsService.savePersons(person5));
    }

    @Test
    @DisplayName("Update a person by its id")
    public void updatePersonById_Test() {

        when(personsRepository.findById(1L)).thenReturn(Optional.ofNullable(person6));
        when (personsRepository.save(any())).thenReturn(person6);

        assertEquals(person6,personsService.updatePersonsById(1L,person6));
    }





    //@Test
    @DisplayName("Update a person by its id")
    public void updatePersonById_Test2() {

        when(personsRepository.findById(1L)).thenReturn(Optional.ofNullable(person6));

        personsService.updatePersonsById(1L,person6);

        // when (personsRepository.save(any())).thenReturn(person6);

        assertEquals(person6,personsService.updatePersonsById(1L,person6));


    }

    @Test
    @DisplayName("Update a person by its firstname and lastname")
    public void updatePersonByFirstAndLastName_Test() {

        when(personsRepository.findByFirstNameAndLastName(person6.getFirstName(), person6.getLastName())).thenReturn(person6);
        when (personsRepository.save(any())).thenReturn(person6);

        assertEquals(person6,personsService.updatePersonsByFirstNameAndLastName(person6.getFirstName(), person6.getLastName(),person6));
    }


    // added
    @Test
    @DisplayName("Update a person with an unknown id")
    public void updatePersonById_Test3() {

        when(personsRepository.findById(any())).thenReturn(null  );
        assertEquals(null,personsService.updatePersonsById(6L,person6));
    }

    @Test
    @DisplayName("Update a person with unknown firstname and lastname")
    public void updatePersonByFirstAndLastName_Test2() {

        when(personsRepository.findByFirstNameAndLastName("bb","cc")).thenReturn(null);
        assertEquals(null,personsService.updatePersonsByFirstNameAndLastName("bb","cc",person6));
    }








} //End