package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.FireStationByStationNumberDTO;
import com.safetynet.alerts.repository.DtoFireStationsRepository;
import com.safetynet.alerts.repository.DtoPersonsRepository;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class Dto1FireStationByStationNumberTest {

    // Dto
    @InjectMocks
    private FireStationByStationNumberService fireStationByStationNumberService;

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
    List<FireStations> fireStationsList = new ArrayList<>();
    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    //DTO
    FireStationByStationNumberDTO fireStationByStationNumberDTO = new FireStationByStationNumberDTO();

    @Mock
    private DtoPersonsRepository dtoPersonsRepository;
    @Mock
    private MedicalRecordsRepository medicalRecordsRepository;

    //DTO
    @Mock
    private DtoFireStationsRepository dtoFireStationsRepository;


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

        // add FireStations into a list of Firestation
        fireStationsList.add(fireStation1);

        // DTO number 1
        fireStationByStationNumberDTO.setPersons(personsList);
        fireStationByStationNumberDTO.setCount_adult(2);
        fireStationByStationNumberDTO.setCount_child(2);
    }

    //DTO Numéro 1
    @Test
    @DisplayName("Firestation by StationNumber")
    public void firestationByStationNumberTest() {

        when(dtoFireStationsRepository.findByStation(any())).thenReturn(fireStationsList);
        when(dtoPersonsRepository.findPersonByAddress(any())).thenReturn(personsList);

        for (MedicalRecords mr : medicalRecordsList) {
            when(medicalRecordsRepository.findByFirstNameAndLastName(mr.getFirstName(), mr.getLastName())).thenReturn(mr);
        }

        assertEquals(fireStationByStationNumberDTO, fireStationByStationNumberService.personByStationDTO(0));
    }

} //End