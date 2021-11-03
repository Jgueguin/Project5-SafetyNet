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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class PersonsServiceTest {

    @InjectMocks
    private PersonsService personsService;
    @InjectMocks
    private FireStationsService fireStationsService;

    // Dto1
    @InjectMocks
    private FireStationByStationNumberService fireStationByStationNumberService;
    //DTO 2
    @InjectMocks
    private DtoChildAlertService dtoChildAlertService;
    //DTO3
    @InjectMocks
    private PhoneAlertService phoneAlertService;
    //DTO4
    @InjectMocks
    private FireStationByAddressService fireStationByAddressService;


    private Persons persons;

    private static Date adultBirthdate = new Date();
    private static Date childBirthdate = new Date();
    public  Date actualDate = new Date();

    public static Persons person1;
    public static Persons person2;
    public static Persons person3;
    public static Persons person4;

    public static MedicalRecords medicalRecordsPerson1;
    public static MedicalRecords medicalRecordsPerson2;
    public static MedicalRecords medicalRecordsPerson3;
    public static MedicalRecords medicalRecordsPerson4;

    public static FireStations fireStation1;

    public  static String childAlertPerson1;
    public  static String childAlertPerson2;
    public  static String childAlertPerson3;
    public  static String childAlertPerson4;

    public static String[] medications1 = new String[6];
    public static String[] medications2 = new String[6];
    public static String[] medications3 = new String[6];
    public static String[] medications4 = new String[6];

    public static String[] allergies1 = new String[6];
    public static String[] allergies2 = new String[6];
    public static String[] allergies3 = new String[6];
    public static String[] allergies4 = new String[6];

    List<Persons> personsList = new ArrayList<>();
    List<FireStations> fireStationsList = new ArrayList<>();
    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    //DTO1
    FireStationByStationNumberDTO fireStationByStationNumberDTO = new FireStationByStationNumberDTO();

    //DTO2
    ChildAlertListDTO childAlertListDTO = new ChildAlertListDTO();

    //DTO3
    PhoneAlertListDTO phoneAlertListDTO = new PhoneAlertListDTO();

    //DTO4
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

        person1 = new Persons(1L,"first_1","Last_1","Address_1","City_1",11111,"111-111-1111","one@email.com");
        person2 = new Persons(2L,"first_2","Last_2","Address_1","City_2",22222,"222-222-2222","two@email.com");
        person3 = new Persons(3L,"first_3","Last_3","Address_1","City_3",33333,"333-333-3333","three@email.com");
        person4 = new Persons(4L,"first_4","Last_4","Address_1","City_4",33333,"444-444-4444","four@email.com");
        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        personsList.add(person4);

        //Date - Birthdate
        adultBirthdate.setDate(01);
        adultBirthdate.setMonth(01);
        adultBirthdate.setYear(1990);

        childBirthdate.setDate(01);
        childBirthdate.setMonth(01);
        childBirthdate.setYear(2019);

        actualDate.setYear(2021);

        medications1[0] = "hydrapermazol:300mg";
        medications1[1] = "dodoxadin:30mg";
        //allergies1[0] = "arachide";

        medications2[0] = "hydrapermazol:300mg";
        allergies1[0] = "brocoli";

        medications3[0] = "doliprane:300mg";
        allergies3[0] = "tomate";


        //Medical Records
        medicalRecordsPerson1 = new MedicalRecords(1L,person1.getFirstName(),person1.getLastName(),adultBirthdate, medications1,allergies1);
        medicalRecordsPerson2 = new MedicalRecords(2L,person2.getFirstName(),person2.getLastName(),adultBirthdate, medications2,allergies2);
        medicalRecordsPerson3 = new MedicalRecords(3L,person3.getFirstName(),person3.getLastName(),childBirthdate, medications3,allergies3);
        medicalRecordsPerson4 = new MedicalRecords(4L,person4.getFirstName(),person4.getLastName(),childBirthdate, medications4,allergies4);
        medicalRecordsList.add(medicalRecordsPerson1);
        medicalRecordsList.add(medicalRecordsPerson2);
        medicalRecordsList.add(medicalRecordsPerson3);
        medicalRecordsList.add(medicalRecordsPerson4);

        // FireStation
        fireStation1 = new FireStations(1L,2,"address1");
        fireStationsList.add(fireStation1);

        // DTO number 1
        fireStationByStationNumberDTO.setPersons(personsList);
        fireStationByStationNumberDTO.setCount_adult(2);
        fireStationByStationNumberDTO.setCount_child(2);

        // DTO number 2 : Child Alert
        childAlertPerson1 = "Adult :"+person1.getFirstName()+ " " + person1.getLastName() ;
        childAlertPerson2 = "Adult :"+person2.getFirstName()+ " " + person2.getLastName() ;
        childAlertPerson3 = "Child :"+person3.getFirstName()+ " " + person3.getLastName() + " Age: " + (actualDate.getYear()-childBirthdate.getYear());
        childAlertPerson4 = "Child :"+person4.getFirstName()+ " " + person4.getLastName() + " Age: " + (actualDate.getYear()-childBirthdate.getYear());

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
        fireStationByAddressArray.add(person1.getFirstName()+" "+person1.getLastName());
        fireStationByAddressArray.add("Phone : " + person1.getPhone());
        fireStationByAddressArray.add("Age: " + (actualDate.getYear() - adultBirthdate.getYear()));
        fireStationByAddressArray.add("Allergies: " + allergies1);
        fireStationByAddressArray.add("Medications: " + medications1);
        fireStationByAddressArray.add("       ");

        fireStationByAddressDTO.setFireAddressArray(fireStationByAddressArray);

    }

    @Test
    @DisplayName("PersonInfo")
    public void givenPersonforMethodAndEntryFirstAndLastName() {

        when(personsRepository.findByFirstNameAndLastName("first_1","Last_1")).thenReturn(person1);
        assertEquals(person1,personsService.getPersonsFirstLastName("first_1","Last_1"));
    }

    @Test
    @DisplayName("PersonInfo")
    public void givenPersonByID() {
        when(personsRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(person1));
        assertEquals(person1,personsService.getPersons(1L).get());
    }

    //DTO Numéro 1
    @Test
    @DisplayName("Firestation by StationNumber")
    public void firestationByStationNumberTest() {
        System.out.println("DTO 1 : Firestation by StationNumber");

    when (dtoFireStationsRepository.findByStation(any())).thenReturn(fireStationsList);
    when (dtoPersonsRepository.findPersonsCoveredByAddress(any())).thenReturn(personsList);
    when(medicalRecordsRepository.findByFirstNameAndLastName(any(),any())).thenReturn(medicalRecordsList.get(0));

    assertEquals(fireStationByStationNumberDTO,fireStationByStationNumberService.personByStationDTO(3));

    }

    //DTO Numéro 2
    @Test
    @DisplayName("DTO 2 : Child Alert")
    public void childAlertDtoTest() {

        System.out.println("DTO 2 : Child Alert");
        when(dtoPersonsRepository.findPersonsCoveredByAddress(any())).thenReturn(personsList);
        System.out.println(personsList);
        when(medicalRecordsRepository.findByFirstNameAndLastName(any(),any())).thenReturn( medicalRecordsList.get(0));
        System.out.println(medicalRecordsList.get(0));

        assertEquals(childAlertListDTO,dtoChildAlertService.childAlertDTO("Address_1"));
    }

    //DTO Numéro 3
    @Test
    @DisplayName("DTO 3 : Phone Alert")
    public void phoneAlertTest() {

        System.out.println("DTO 3 : Phone Alert: ");
        when(dtoFireStationsRepository.findByStation(any())).thenReturn(fireStationsList);
        when(dtoPersonsRepository.findPersonByAddress(any())).thenReturn(personsList);

        assertEquals(StringUtils.deleteWhitespace(phoneAlertListDTO.toString()), StringUtils.deleteWhitespace(phoneAlertService.phoneAlertDTO(2).toString())   );
    }

    //DTO Numéro 4
    @Test
    @DisplayName("FireStation By Address")
    public void fireStationByAddressTest() {

        System.out.println("DTO 4 : FireStation by Address");

        when(fireStationsRepository.findByAddress(any())).thenReturn(fireStationsList.get(0));
        when (dtoPersonsRepository.findPersonByAddress(any())).thenReturn(personsList);
        System.out.println("personList : "+personsList);
        when(medicalRecordsRepository.findByFirstNameAndLastName(any(),any())).thenReturn(medicalRecordsList.get(0));
        System.out.println("medicalRecordsList : "+medicalRecordsList);

        assertEquals(fireStationByAddressDTO, fireStationByAddressService.personsCoveredByAddress2("Address_1"));
    }






} //End