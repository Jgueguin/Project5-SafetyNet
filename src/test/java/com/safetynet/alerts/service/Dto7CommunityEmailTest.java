package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.CommunityEmailByCityListDTO;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class Dto7CommunityEmailTest {

    // Dto
    @InjectMocks
    private DtoCommunityEmailService dtoCommunityEmailService;

    private static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    private static Persons person2 = new Persons(2L, "first_2", "Last_2", "Address_1", "City_2", 22222, "222-222-2222", "two@email.com");
    private static Persons person3 = new Persons(3L, "first_3", "Last_3", "Address_1", "City_3", 33333, "333-333-3333", "three@email.com");
    private static Persons person4 = new Persons(4L, "first_4", "Last_4", "Address_1", "City_4", 33333, "444-444-4444", "four@email.com");

    List<Persons> personsList = new ArrayList<>();

    //DTO
    CommunityEmailByCityListDTO communityEmailByCityListDTO = new CommunityEmailByCityListDTO();

    @Mock
    private DtoPersonsRepository dtoPersonsRepository;

    @Before
    public void setUp() {

        //add persons into list of persons
        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        personsList.add(person4);

          //DTO7 : Community Email
        ArrayList<String> communityEmailArray = new ArrayList<>();

        for (Persons p : personsList) {
            communityEmailArray.add("Firstname: " + p.getFirstName() + " -- Lastname: " + p.getLastName() + "-> Email: " + p.getEmail());
        }
        communityEmailByCityListDTO.setEmailArray(communityEmailArray);
    }

    //DTO number 7
    @Test
    @DisplayName("Community Email")
    public void communityEmailTest() {

        when(dtoPersonsRepository.findEmailByCity(any())).thenReturn(personsList);

        assertEquals(StringUtils.deleteWhitespace(communityEmailByCityListDTO.toString()), StringUtils.deleteWhitespace(dtoCommunityEmailService.extractEmailByCityDTO(any()).toString()));
    }


} //End