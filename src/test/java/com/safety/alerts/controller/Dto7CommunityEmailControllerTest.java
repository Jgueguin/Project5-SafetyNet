package com.safety.alerts.controller;

import com.safetynet.alerts.controller.DtoCommunityEmailController;
import com.safetynet.alerts.controller.FireStationsController;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.CommunityEmailByCityListDTO;
import com.safetynet.alerts.repository.DtoPersonsRepository;
import com.safetynet.alerts.service.DtoCommunityEmailService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = DtoCommunityEmailController.class)

public class Dto7CommunityEmailControllerTest {

    // Dto
    @Mock
    private DtoCommunityEmailService dtoCommunityEmailService;

    private DtoCommunityEmailController dtoCommunityEmailController;

    @Before
    public void setUp() {
        //added
        MockitoAnnotations.initMocks(this);
        dtoCommunityEmailController = new DtoCommunityEmailController(dtoCommunityEmailService);
    }

//Get

    @Test
    public void getCommunityEmailTest() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoCommunityEmailController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail?city=Culver ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getCommunityEmail_BadUrl_Test() throws Exception {

        //added
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((dtoCommunityEmailController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }





} //End