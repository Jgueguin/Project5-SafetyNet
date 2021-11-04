package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.ChildAlertListDTO;
import com.safetynet.alerts.model.dto.FireStationByAddressDTO;
import com.safetynet.alerts.model.dto.FireStationByStationNumberDTO;
import com.safetynet.alerts.model.dto.PhoneAlertListDTO;
import com.safetynet.alerts.repository.*;
import org.apache.commons.lang3.StringUtils;
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

public class PersonsServiceTest {

    private Persons persons;
    public MedicalRecords medicalRecords;

    @InjectMocks
    private PersonsService personsService;
    @InjectMocks
    private FireStationsService fireStationsService;

    // Dto
    @InjectMocks
    private FireStationByStationNumberService fireStationByStationNumberService;
    @InjectMocks
    private DtoChildAlertService dtoChildAlertService;
    @InjectMocks
    private PhoneAlertService phoneAlertService;
    @InjectMocks
    private FireStationByAddressService fireStationByAddressService;

    private static Calendar adultBirth = new GregorianCalendar(2001, 9, 5, 1, 1);
    private static Calendar childBirth = new GregorianCalendar(2019, 9, 5, 1, 1);
    private static Calendar dateNow = new GregorianCalendar();

    public static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    public static Persons person2 = new Persons(2L, "first_2", "Last_2", "Address_1", "City_2", 22222, "222-222-2222", "two@email.com");
    public static Persons person3 = new Persons(3L, "first_3", "Last_3", "Address_1", "City_3", 33333, "333-333-3333", "three@email.com");
    public static Persons person4 = new Persons(4L, "first_4", "Last_4", "Address_1", "City_4", 33333, "444-444-4444", "four@email.com");

    public static MedicalRecords medicalRecordsPerson1;
    public static MedicalRecords medicalRecordsPerson2;
    public static MedicalRecords medicalRecordsPerson3;
    public static MedicalRecords medicalRecordsPerson4;

    public static FireStations fireStation1 = new FireStations(1L, 2, "address1");

    public static String childAlertPerson1 = "Adult :" + person1.getFirstName() + " " + person1.getLastName();
    public static String childAlertPerson2 = "Adult :" + person2.getFirstName() + " " + person2.getLastName();;
    public static String childAlertPerson3 = "Child :" + person3.getFirstName() + " " + person3.getLastName() + " Age: " + (dateNow.getWeekYear() - childBirth.getWeekYear());
    public static String childAlertPerson4 = "Child :" + person4.getFirstName() + " " + person4.getLastName() + " Age: " + (dateNow.getWeekYear() - childBirth.getWeekYear());

    public static String[] medications1 = new String[2];
    public static String[] medications2 = new String[2];
    public static String[] medications3 = new String[2];
    public static String[] medications4 = new String[2];

    public static String[] allergies1 = new String[2];
    public static String[] allergies2 = new String[2];
    public static String[] allergies3 = new String[2];
    public static String[] allergies4 = new String[2];

    List<Persons> personsList = new ArrayList<>();
    List<FireStations> fireStationsList = new ArrayList<>();
    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    //DTO
    FireStationByStationNumberDTO fireStationByStationNumberDTO = new FireStationByStationNumberDTO();
    ChildAlertListDTO childAlertListDTO = new ChildAlertListDTO();
    PhoneAlertListDTO phoneAlertListDTO = new PhoneAlertListDTO();
    FireStationByAddressDTO fireStationByAddressDTO = new FireStationByAddressDTO();


    @Mock
    private PersonsRepository personsRepository;
    @Mock
    private DtoPersonsRepository dtoPersonsRepository;
    @Mock
    private MedicalRecordsRepository medicalRecordsRepository;

    //DTO
    @Mock
    private DtoFireStationsRepository dtoFireStationsRepository;
    @Mock
    private FireStationsRepository fireStationsRepository;


    @Before
    public void setUp() {

        //add persons into list of persons
        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        personsList.add(person4);

        // define medications and allergies
        medications1[0] = "hydrapermazol:300mg";
        medications1[1] = "dodoxadin:30mg";

        allergies1[0] = "arachide";
        allergies1[1] = " ";

        medications2[0] = "hydrapermazol:300mg";
        medications2[1] = " ";
        allergies2[0] = "brocoli";
        allergies2[1] = " ";

        medications3[0] = "doliprane:300mg";
        medications3[1] = " ";
        allergies3[0] = "tomate";
        allergies3[1] = " ";


        //Medical Records
        medicalRecordsPerson1 = new MedicalRecords(1L, person1.getFirstName(), person1.getLastName(), adultBirth, medications1, allergies1);
        medicalRecordsPerson2 = new MedicalRecords(2L, person2.getFirstName(), person2.getLastName(), adultBirth, medications2, allergies2);
        medicalRecordsPerson3 = new MedicalRecords(3L, person3.getFirstName(), person3.getLastName(), childBirth, medications3, allergies3);
        medicalRecordsPerson4 = new MedicalRecords(4L, person4.getFirstName(), person4.getLastName(), childBirth, medications4, allergies4);
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

        // DTO number 2 : Child Alert
        ArrayList<String> childAlertList = new ArrayList<>();

        childAlertList.add(childAlertPerson1);
        childAlertList.add(childAlertPerson2);
        childAlertList.add(childAlertPerson3);
        childAlertList.add(childAlertPerson4);

        childAlertListDTO.setChildAlertArray(childAlertList);

        //DTO3 : Phone Alert
        ArrayList<String> phoneAlertArray = new ArrayList<>();

        phoneAlertArray.add(fireStation1.getAddress());
        phoneAlertArray.add(person1.getPhone());
        phoneAlertArray.add(person2.getPhone());
        phoneAlertArray.add(person3.getPhone());
        phoneAlertArray.add(person4.getPhone());

        phoneAlertListDTO.setPhoneAlertArray(phoneAlertArray);

        //DTO4 : FireStation By Adress
        ArrayList<String> fireStationByAddressArray = new ArrayList<>();

        fireStationByAddressArray.add("Address_1");
        fireStationByAddressArray.add("StationNumber :2");

        for (Persons p : personsList) {
            fireStationByAddressArray.add(p.getFirstName() + " " + p.getLastName());
            fireStationByAddressArray.add("Phone : " + p.getPhone());

            if (p == person1) {
                fireStationByAddressArray.add("Age: " + (dateNow.getWeekYear() - adultBirth.getWeekYear()));
                fireStationByAddressArray.add("Allergies: " + medicalRecordsPerson1.getAllergies());
                fireStationByAddressArray.add("Medications: " + medicalRecordsPerson1.getMedications());
            }
            else if (p == person2) {
                fireStationByAddressArray.add("Age: " + (dateNow.getWeekYear() - adultBirth.getWeekYear()));
                fireStationByAddressArray.add("Allergies: " + medicalRecordsPerson2.getAllergies());
                fireStationByAddressArray.add("Medications: " + medicalRecordsPerson2.getMedications());
            }
             else if (p==person3) {
                fireStationByAddressArray.add("Age: " + (dateNow.getWeekYear() - childBirth.getWeekYear()));
                fireStationByAddressArray.add("Allergies: " + medicalRecordsPerson3.getAllergies());
                fireStationByAddressArray.add("Medications: " + medicalRecordsPerson3.getMedications());
            }
             else {
                fireStationByAddressArray.add("Age: " + (dateNow.getWeekYear() - childBirth.getWeekYear()));
                fireStationByAddressArray.add("Allergies: " + medicalRecordsPerson4.getAllergies());
                fireStationByAddressArray.add("Medications: " + medicalRecordsPerson4.getMedications());
            }
        }
        fireStationByAddressArray.add("       ");

        fireStationByAddressDTO.setFireAddressArray(fireStationByAddressArray);

}

    //DTO Numéro 1
    @Test
    @DisplayName("Firestation by StationNumber")
    public void firestationByStationNumberTest() {
        System.out.println("DTO 1 : Firestation by StationNumber");

        when(dtoFireStationsRepository.findByStation(any())).thenReturn(fireStationsList);
        when(dtoPersonsRepository.findPersonByAddress(any())).thenReturn(personsList);

        for (MedicalRecords mr : medicalRecordsList) {
            when(medicalRecordsRepository.findByFirstNameAndLastName(mr.getFirstName(), mr.getLastName())).thenReturn(mr);
        }

        assertEquals(fireStationByStationNumberDTO, fireStationByStationNumberService.personByStationDTO(0));

    }

    //DTO Numéro 2
    @Test
    @DisplayName("DTO 2 : Child Alert")
    public void childAlertDtoTest() {

        System.out.println("DTO 2 : Child Alert");

        when(dtoPersonsRepository.findPersonByAddress(any())).thenReturn(personsList);

        for (MedicalRecords mr : medicalRecordsList) {
            when(medicalRecordsRepository.findByFirstNameAndLastName(mr.getFirstName(), mr.getLastName())).thenReturn(mr);
        }

        assertEquals(StringUtils.deleteWhitespace(childAlertListDTO.toString()),
                StringUtils.deleteWhitespace(dtoChildAlertService.childAlertDTO("Address_1").toString()));
    }

    //DTO Number 3
    @Test
    @DisplayName("DTO 3 : Phone Alert")
    public void phoneAlertTest() {

        System.out.println("DTO 3 : Phone Alert: ");
        when(dtoFireStationsRepository.findByStation(any())).thenReturn(fireStationsList);
        when(dtoPersonsRepository.findPersonByAddress(any())).thenReturn(personsList);

        assertEquals(StringUtils.deleteWhitespace(phoneAlertListDTO.toString()), StringUtils.deleteWhitespace(phoneAlertService.phoneAlertDTO(2).toString()));
    }

    //DTO Number 4
    @Test
    @DisplayName("FireStation By Address")
    public void fireStationByAddressTest() {

        System.out.println("DTO 4 : FireStation by Address");

        when(fireStationsRepository.findByAddress(any())).thenReturn(fireStationsList.get(0));
        when(dtoPersonsRepository.findPersonsCoveredByAddress(any())).thenReturn(personsList);

        for (MedicalRecords mr : medicalRecordsList) {
            when(medicalRecordsRepository.findByFirstNameAndLastName(mr.getFirstName(), mr.getLastName())).thenReturn(mr);
        }

        //assertEquals(StringUtils.deleteWhitespace(fireStationByAddressDTO.toString()), StringUtils.deleteWhitespace(fireStationByAddressService.personsCoveredByAddress2("Address_1").toString()));

        assertEquals(fireStationByAddressDTO.toString(), fireStationByAddressService.personsCoveredByAddress2("Address_1").toString());

    }


    //DTO number 5
} //End