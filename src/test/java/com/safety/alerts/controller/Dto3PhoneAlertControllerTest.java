package com.safety.alerts.controller;

import com.safetynet.alerts.controller.DtoChildAlertController;
import com.safetynet.alerts.controller.DtoPhoneAlertController;
import com.safetynet.alerts.service.DtoChildAlertService;
import com.safetynet.alerts.service.DtoPhoneAlertService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = DtoPhoneAlertController.class)

public class Dto3PhoneAlertControllerTest {

    @Mock
    private DtoPhoneAlertService dtoPhoneAlertService;

    private DtoPhoneAlertController dtoPhoneAlertController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dtoPhoneAlertController = new DtoPhoneAlertController(dtoPhoneAlertService);
    }

//Get

    @Test
    public void getPhoneAlertTest() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoPhoneAlertController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?station=3 ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getPhoneAlert_BadUrl_Test() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoPhoneAlertController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



} //End