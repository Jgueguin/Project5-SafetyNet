package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.PersonInfoByFirstNameAndLastNameListDTO;
import com.safetynet.alerts.repository.DtoPersonsRepository;
import com.safetynet.alerts.repository.MedicalRecordsRepository;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class Dto6PersonInfoTest {

    // Dto
    @InjectMocks
    private PersonInfoService personInfoService;

    private static Calendar adultBirth = new GregorianCalendar(2001, 9, 5, 1, 1);
    private static Calendar childBirth = new GregorianCalendar(2019, 9, 5, 1, 1);
    private static Calendar dateNow = new GregorianCalendar();

    public static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    public static Persons person2 = new Persons(2L, "first_2", "Last_2", "Address_1", "City_2", 22222, "222-222-2222", "two@email.com");
    public static Persons person3 = new Persons(3L, "first_3", "Last_3", "Address_1", "City_3", 33333, "333-333-3333", "three@email.com");
    public static Persons person4 = new Persons(4L, "first_4", "Last_4", "Address_1", "City_4", 33333, "444-444-4444", "four@email.com");

    public static String[] medications1 = {"hydrapermazol:300mg","dodoxadin:30mg"};
    public static String[] medications2 = {"hydrapermazol:300mg"};
    public static String[] medications3 = {"hydrapermazol:300mg"};
    public static String[] medications4 = {""};

    public static String[] allergies1 = {"arachide"};
    public static String[] allergies2 = {"brocoli"};
    public static String[] allergies3 = {"tomate"};
    public static String[] allergies4 = {""};

    public static MedicalRecords medicalRecordsPerson1 = new MedicalRecords(1L, person1.getFirstName(), person1.getLastName(), adultBirth, medications1, allergies1);
    public static MedicalRecords medicalRecordsPerson2 = new MedicalRecords(2L, person2.getFirstName(), person2.getLastName(), adultBirth, medications2, allergies2);
    public static MedicalRecords medicalRecordsPerson3 = new MedicalRecords(3L, person3.getFirstName(), person3.getLastName(), childBirth, medications3, allergies3);
    public static MedicalRecords medicalRecordsPerson4 = new MedicalRecords(4L, person4.getFirstName(), person4.getLastName(), childBirth, medications4, allergies4);

    String medicationsDelimiter1 = String.join(",", medicalRecordsPerson1.getMedications());
    String medicationsDelimiter2 = String.join(",", medicalRecordsPerson2.getMedications());
    String medicationsDelimiter3 = String.join(",", medicalRecordsPerson3.getMedications());
    String medicationsDelimiter4 = String.join(",", medicalRecordsPerson4.getMedications());

    String allergiesDelimiter1 = String.join(",", medicalRecordsPerson1.getAllergies());
    String allergiesDelimiter2 = String.join(",", medicalRecordsPerson2.getAllergies());
    String allergiesDelimiter3 = String.join(",", medicalRecordsPerson3.getAllergies());
    String allergiesDelimiter4 = String.join(",", medicalRecordsPerson4.getAllergies());


    List<Persons> personsList = new ArrayList<>();
    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    //DTO
    PersonInfoByFirstNameAndLastNameListDTO personInfoByFirstNameAndLastNameListDTO = new PersonInfoByFirstNameAndLastNameListDTO();

    @Mock
    private DtoPersonsRepository dtoPersonsRepository;
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




    //DTO6 : Person Info
        ArrayList<String> personInfo = new ArrayList<>();

        for (Persons p : personsList) {

            personInfo.add(p.getFirstName() + " " + p.getLastName());
            personInfo.add(p.getAddress());

            if (p == person1) {
                personInfo.add("Age: " + (dateNow.getWeekYear() - adultBirth.getWeekYear()));
                personInfo.add(p.getEmail());
                personInfo.add("Allergies: " + allergiesDelimiter1);
                personInfo.add("Medications: " + medicationsDelimiter1);
            }

            else if ( p == person2) {
                personInfo.add("Age: " + (dateNow.getWeekYear() - adultBirth.getWeekYear()));
                personInfo.add(p.getEmail());
                personInfo.add("Allergies: " + allergiesDelimiter2);
                personInfo.add("Medications: " + medicationsDelimiter2);
            }

            else if ( p == person3) {
                personInfo.add("Age: " + (dateNow.getWeekYear() - childBirth.getWeekYear()));
                personInfo.add(p.getEmail());
                personInfo.add("Allergies: " + allergiesDelimiter3);
                personInfo.add("Medications: " + medicationsDelimiter3);
            }
            else {
                personInfo.add("Age: " + (dateNow.getWeekYear() - childBirth.getWeekYear()));
                personInfo.add(p.getEmail());
                personInfo.add("Allergies: " + allergiesDelimiter4);
                personInfo.add("Medications: " + medicationsDelimiter4);
            }
        }
        personInfoByFirstNameAndLastNameListDTO.setPersonInfoArray(personInfo);

    }


    //DTO number 6
    @Test
    @DisplayName("Person Info")
    public void personInfoTest() {

        when(dtoPersonsRepository.findPersonInfoByFirstNameAndLastName(any(),any())).thenReturn(personsList);

        for (MedicalRecords mr : medicalRecordsList) {

            when(medicalRecordsRepository.findByFirstNameAndLastName(mr.getFirstName(), mr.getLastName())).thenReturn(mr);

        }

        assertEquals(StringUtils.deleteWhitespace(personInfoByFirstNameAndLastNameListDTO.toString()), StringUtils.deleteWhitespace(personInfoService.firstNameAndLastNameDTO(any(),any()).toString()));
    }



} //End