package com.safety.alerts.controller;

import com.safetynet.alerts.controller.DtoChildAlertController;
import com.safetynet.alerts.repository.DtoPersonsRepository;
import com.safetynet.alerts.repository.MedicalRecordsRepository;
import com.safetynet.alerts.service.DtoChildAlertService;
import com.safetynet.alerts.service.FireStationsService;
import com.safetynet.alerts.service.PersonsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = DtoChildAlertController.class)

public class Dto2ChildAlertControllerTest {

    @Mock
    private DtoChildAlertService dtoChildAlertService;

    private DtoChildAlertController dtoChildAlertController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dtoChildAlertController = new DtoChildAlertController(dtoChildAlertService);
    }

//Get

    @Test
    public void getDtoChildAlertTest() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoChildAlertController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/childAlert?address=1509 Culver St ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getDtoChildAlert_BadUrl_Test() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoChildAlertController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/childAlert ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



} //End