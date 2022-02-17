package com.safety.alerts.controller;

import com.safetynet.alerts.controller.DtoFloodController;
import com.safetynet.alerts.controller.DtoPhoneAlertController;
import com.safetynet.alerts.service.DtoFloodService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = DtoFloodController.class)

public class Dto5FloodControllerTest {

    @Mock
    private DtoFloodService dtoFloodService;

    private DtoFloodController dtoFloodController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dtoFloodController = new DtoFloodController(dtoFloodService);
    }

//Get

    @Test
    public void getFloodTest() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoFloodController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations?station=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getPhoneAlert_BadUrl_Test() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoFloodController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/flood/station")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



} //End