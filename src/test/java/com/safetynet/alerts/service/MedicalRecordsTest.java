package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.MedicalRecordsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class MedicalRecordsTest {

    @InjectMocks
    private MedicalRecordsService medicalRecordsService;

    private static Calendar adultBirth = new GregorianCalendar(2001, 9, 5, 1, 1);
    private static Calendar childBirth = new GregorianCalendar(2019, 9, 5, 1, 1);
    private static Calendar dateNow = new GregorianCalendar();

    private static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    private static Persons person2 = new Persons(2L, "first_2", "Last_2", "Address_1", "City_2", 22222, "222-222-2222", "two@email.com");
    private static Persons person3 = new Persons(3L, "first_3", "Last_3", "Address_1", "City_3", 33333, "333-333-3333", "three@email.com");
    private static Persons person4 = new Persons(4L, "first_4", "Last_4", "Address_1", "City_4", 33333, "444-444-4444", "four@email.com");

    private static String[] medications1 = {"hydrapermazol:300mg","dodoxadin:30mg"};
    private static String[] medications2 = {"hydrapermazol:300mg"};
    private static String[] medications3 = {"hydrapermazol:300mg"};
    private static String[] medications4 = {""};

    private static String[] allergies1 = {"arachide"};
    private static String[] allergies2 = {"brocoli"};
    private static String[] allergies3 = {"tomate"};
    private static String[] allergies4 = {""};

    private static MedicalRecords medicalRecordsPerson1 = new MedicalRecords(1L, person1.getFirstName(), person1.getLastName(), adultBirth, medications1, allergies1);
    private static MedicalRecords medicalRecordsPerson2 = new MedicalRecords(2L, person2.getFirstName(), person2.getLastName(), adultBirth, medications2, allergies2);
    private static MedicalRecords medicalRecordsPerson3 = new MedicalRecords(3L, person3.getFirstName(), person3.getLastName(), childBirth, medications3, allergies3);
    private static MedicalRecords medicalRecordsPerson4 = new MedicalRecords(4L, person4.getFirstName(), person4.getLastName(), childBirth, medications4, allergies4);

    private static FireStations fireStation1 = new FireStations(1L, 2, "address1");

    List<Persons> personsList = new ArrayList<>();
    // List<FireStations> fireStationsList = new ArrayList<>();

    List<MedicalRecords> medicalRecordsList = new ArrayList<>();
    List<MedicalRecords> medicalRecordsList2 = new ArrayList<>();

    @Mock
    private MedicalRecordsRepository medicalRecordsRepository;

    @Before
    public void setUp() {

        //add persons into list of persons
        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        personsList.add(person4);

       //Medical Records
        medicalRecordsList.add(medicalRecordsPerson1);
        medicalRecordsList.add(medicalRecordsPerson2);
        medicalRecordsList.add(medicalRecordsPerson3);
        medicalRecordsList.add(medicalRecordsPerson4);

        //Medical Records
        medicalRecordsList2.add(medicalRecordsPerson1);
        medicalRecordsList2.add(medicalRecordsPerson2);
        medicalRecordsList2.add(medicalRecordsPerson3);


        // add FireStations into a list of Firestation
        //fireStationsList.add(fireStation1);

    }

    @Test
    @DisplayName("Get medicalRecord by Id Test")
    public void getMedicalRecords_By_Id_Test() {

        when(medicalRecordsRepository.findById(any())).thenReturn(Optional.ofNullable(medicalRecordsPerson1));
        assertEquals(Optional.ofNullable(medicalRecordsPerson1), medicalRecordsService.getMedicalRecords(1L));
    }

    @Test
    @DisplayName("Get All MedicalRecords")
    public void getAllMedicalRecords_Test() {

        when(medicalRecordsRepository.findAll()).thenReturn(medicalRecordsList);
        assertEquals(medicalRecordsList, medicalRecordsService.getMedicalRecords());
    }

    @Test
    @DisplayName("Delete a medical Record its ID ")
    public void deleteMedicalRecords_Test() {

        medicalRecordsService.deleteMedicalRecordsById(4L);

        int newListSize = medicalRecordsList.size()-1;
        assertEquals(newListSize, medicalRecordsList2.size());
    }

    @Test
    @DisplayName("Delete a medical record with its firstname and lastname")
    public void deleteMedicalRecordWithFirstAndLastName_Test() {

        when(medicalRecordsRepository.findByFirstNameAndLastName(any(),any())).thenReturn(medicalRecordsPerson1);
        medicalRecordsService.deleteMedicalRecordsByFirstNameAndLastName(medicalRecordsPerson1.getFirstName(), medicalRecordsPerson1.getLastName());

        int newListSize = medicalRecordsList.size()-1;
        assertEquals(newListSize, medicalRecordsList2.size());
    }

    @Test
    @DisplayName("Save a medical record")
    public void saveMedicalRecord_Test() {

        when(medicalRecordsRepository.save(any())).thenReturn(medicalRecordsPerson4);
        medicalRecordsService.saveMedicalRecords(medicalRecordsPerson4);

        assertEquals(medicalRecordsPerson4,medicalRecordsService.saveMedicalRecords(medicalRecordsPerson4));
    }

    @Test
    @DisplayName("Update a medical record by its id")
    public void updateMedicalRecordsById_Test() {

        when(medicalRecordsRepository.findById(1L)).thenReturn(Optional.ofNullable(medicalRecordsPerson4));
        when(medicalRecordsRepository.save(any())).thenReturn(medicalRecordsPerson4);

        assertEquals(medicalRecordsPerson4,medicalRecordsService.updateMedicalRecordById(1L,medicalRecordsPerson4));
    }

    @Test
    @DisplayName("Update a medicalRecord by its firstname and lastname")
    public void updateMedicalRecordByFirstAndLastName_Test() {

        when(medicalRecordsRepository.findByFirstNameAndLastName(medicalRecordsPerson1.getFirstName(),medicalRecordsPerson1.getLastName())).thenReturn(medicalRecordsPerson1);
        when (medicalRecordsRepository.save(any())).thenReturn(medicalRecordsPerson1);

        assertEquals(medicalRecordsPerson1,medicalRecordsService.updateMedicalRecordByFirstNameAndLastName(medicalRecordsPerson1.getFirstName(), medicalRecordsPerson1.getLastName(),medicalRecordsPerson1));
    }

    @Test
    @DisplayName("Get a Medical Records with First Name and LastName ")
    public void getMedicalRecordByFirstLastName_Test() {

        when(medicalRecordsRepository.findByFirstNameAndLastName(any(),any())).thenReturn(medicalRecordsPerson1);
        assertEquals(medicalRecordsPerson1, medicalRecordsService.findByFirstNameAndLastName(medicalRecordsPerson1.getFirstName(), medicalRecordsPerson1.getLastName()));
    }

//Added

    @Test
    @DisplayName("Update a medical record by unknown id")
    public void updateMedicalRecordsById_Test2() {

        when(medicalRecordsRepository.findById(any())).thenReturn(null);
        assertEquals(null,medicalRecordsService.updateMedicalRecordById(9L,medicalRecordsPerson4));
    }

    @Test
    @DisplayName("Update a medicalRecord by unknown firstname and lastname")
    public void updateMedicalRecordByFirstAndLastName_Test2() {

        when(medicalRecordsRepository.findByFirstNameAndLastName(any(),any())).thenReturn(null);
        assertEquals(null,
                medicalRecordsService.updateMedicalRecordByFirstNameAndLastName("aa", "bb",medicalRecordsPerson1));
    }






} //End