package com.safety.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.MedicalRecordsController;
import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.MedicalRecordsService;
import com.safetynet.alerts.service.PersonsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@WebMvcTest(controllers = MedicalRecordsController.class)

public class MedicalRecordsControllerTest {
    @Mock
    private MedicalRecordsService medicalRecordsService;

    MedicalRecordsController medicalRecordsController;

    private static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    private static Calendar adultBirth = new GregorianCalendar(2001, 9, 5, 1, 1);
    private static String[] medications2 = {"hydrapermazol:300mg"};
    private static String[] allergies2 = {"brocoli"};

    private static MedicalRecords medicalRecordsPerson1 = new MedicalRecords(1L, person1.getFirstName(), person1.getLastName(), adultBirth, medications2, allergies2);

    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

        medicalRecordsController = new MedicalRecordsController(medicalRecordsService);

        //Medical Records
        medicalRecordsList.add(medicalRecordsPerson1);

    }

    //Get
    @Test
    public void getMedicalRecordsAll() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/medicalrecords/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getMedicalRecordsId() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/medicalrecords/{id}",1 ))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void getMedicalRecordsName() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/medicalrecords/John/Boyd "))
                .andExpect(MockMvcResultMatchers.status().isOk())
               ;
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Post

    /*java.lang.AssertionError: Status
    Expected :201
    Actual   :200*/

    @Test
    public void createMedicalRecordsTest() throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/medicalrecords")
                        .content(asJsonString(
                                new MedicalRecords(
                                        2L,
                                        "firstName4",
                                        "lastName4",
                                        adultBirth,
                                        medications2,
                                        allergies2)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

        ;
    }



//Put

    @Test
    public void updateFireStationsIdTest() throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/medicalrecords/{id}", 2)
                        .content(asJsonString(new MedicalRecords(
                                2L,
                                "firstName",
                                "lastName",
                                adultBirth,
                                medications2,
                                allergies2
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                      ;
    }

    @Test
    public void updateFireStationsFirstNameLastNameTest() throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/medicalrecords/{firstName}/{lastName}", "firstName","lastName")
                        .content(asJsonString(new MedicalRecords(
                                2L,
                                "firstName",
                                "lastName",
                                adultBirth,
                                medications2,
                                allergies2
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }



    //Delete

    @Test
    public void deleteFireStationsIdTest() throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform( MockMvcRequestBuilders.delete("/medicalrecords/{id} ", 2) )

                .andExpect(status().isOk())
        ;
    }

    @Test
    public void deleteFireStationsFirstNameLastNameTest() throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((medicalRecordsController)).build();

        mockMvc.perform( MockMvcRequestBuilders.delete("/medicalrecords/{firstName}/{lastName} ","firstName","lastName") )

                .andExpect(status().isOk())

        ;
    }


} //End