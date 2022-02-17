package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.PhoneAlertListDTO;
import com.safetynet.alerts.repository.DtoFireStationsRepository;
import com.safetynet.alerts.repository.DtoPersonsRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class Dto3PhoneAlertTest {

    // Dto
    @InjectMocks
    private DtoPhoneAlertService dtoPhoneAlertService;

    private static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    private static Persons person2 = new Persons(2L, "first_2", "Last_2", "Address_1", "City_2", 22222, "222-222-2222", "two@email.com");
    private static Persons person3 = new Persons(3L, "first_3", "Last_3", "Address_1", "City_3", 33333, "333-333-3333", "three@email.com");
    private static Persons person4 = new Persons(4L, "first_4", "Last_4", "Address_1", "City_4", 33333, "444-444-4444", "four@email.com");

    private static FireStations fireStation1 = new FireStations(1L, 2, "address1");

    List<Persons> personsList = new ArrayList<>();
    List<FireStations> fireStationsList = new ArrayList<>();
    List<FireStations> fireStationsList2 = new ArrayList<>(); // empty list

    //DTO
    PhoneAlertListDTO phoneAlertListDTO = new PhoneAlertListDTO();
    PhoneAlertListDTO phoneAlertList2DTO = new PhoneAlertListDTO();

    @Mock
    private DtoPersonsRepository dtoPersonsRepository;

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

        // add FireStations into a list of Firestation
        fireStationsList.add(fireStation1);

        //DTO3 : Phone Alert
        ArrayList<String> phoneAlertArray = new ArrayList<>();

        phoneAlertArray.add(fireStation1.getAddress());
        phoneAlertArray.add(person1.getPhone());
        phoneAlertArray.add(person2.getPhone());
        phoneAlertArray.add(person3.getPhone());
        phoneAlertArray.add(person4.getPhone());

        phoneAlertListDTO.setPhoneAlertArray(phoneAlertArray);


//
        ArrayList<String> phoneAlertArray2 = new ArrayList<>();

        phoneAlertArray2.add("");
        phoneAlertList2DTO.setPhoneAlertArray(phoneAlertArray2);



    }


    //DTO Number 3
    @Test
    @DisplayName("DTO 3 : Phone Alert")
    public void phoneAlertTest() {

        when(dtoFireStationsRepository.findByStation(any())).thenReturn(fireStationsList);
        when(dtoPersonsRepository.findPersonByAddress(any())).thenReturn(personsList);

        assertEquals(StringUtils.deleteWhitespace(phoneAlertListDTO.toString()), StringUtils.deleteWhitespace(dtoPhoneAlertService.phoneAlertDTO(2).toString()));
    }

  /*  @Test
    @DisplayName("DTO 3 : Phone Alert")
    public void phoneAlertTest2() {

       // when(dtoFireStationsRepository.findByStation(any())).thenReturn(fireStationsList2);

        assertEquals(phoneAlertList2DTO, phoneAlertService.phoneAlertDTO(5));
    }*/



} //End