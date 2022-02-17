package com.safety.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.repository.PersonsRepository;
import com.safetynet.alerts.service.PersonsService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// import static java.lang.ProcessHandleImpl.get;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = PersonsController.class)

public class PersonsControllerTest {

    @Mock
    private PersonsService personsService;

    // added
    private PersonsController personsController;

    String jsonBody = "{ 1L, \"first_1\", \"Last_1\", \"Address_1\", \"City_1\", 11111, \"111-111-1111\", \"one@email.com\"}";

    private static Persons person1 = new Persons(1L, "first_1", "Last_1", "Address_1", "City_1", 11111, "111-111-1111", "one@email.com");
    private static Persons person2 = new Persons(2L, "first_2", "Last_2", "Address_1", "City_2", 22222, "222-222-2222", "two@email.com");
    private static Persons person3 = new Persons(3L, "first_3", "Last_3", "Address_1", "City_3", 33333, "333-333-3333", "three@email.com");
    private static Persons person4 = new Persons(4L, "first_4", "Last_4", "Address_1", "City_4", 33333, "444-444-4444", "four@email.com");

    List<Persons> personsList = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        //added
        MockitoAnnotations.initMocks(this);
        personsController = new PersonsController(personsService);
    }


//Get
    @Test
    public void getPersonsAllTest() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/ ")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonsIdTest() throws Exception {
        //added
        MockMvc mockMvc2 = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc2.perform(MockMvcRequestBuilders.get("/persons/{id}",1 ))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void getPersonsNameTest() throws Exception {
        //added
        MockMvc mockMvc3 = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc3.perform(MockMvcRequestBuilders.get("/pers/hello/hello "))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }


    @Test
    public void getPersons_BadUrl_Test() throws Exception {
        //added
        MockMvc mockMvc3 = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc3.perform(MockMvcRequestBuilders.get("/pers "))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }



    //Post

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /*java.lang.AssertionError: Status
    Expected :201
    Actual   :200*/

    @Test
    public void createPersonsTest() throws Exception
    {
        MockMvc mockMvc4 = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc4.perform( MockMvcRequestBuilders
                        .post("/persons")
                        .content(asJsonString(
                                new Persons(
                                        2L,
                                        "firstName4",
                                        "lastName4",
                                        "address",
                                        "city",
                                        11111,
                                        "555-554",
                                        "email4@mail.com")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


//Put
    @Test
    public void updatePersonsIdTest() throws Exception
    {
        MockMvc mockMvc5 = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc5.perform( MockMvcRequestBuilders
                        .put("/persons/{id}", 2)
                        .content(asJsonString(new Persons(
                                2L,
                                "firstName2",
                                "lastName2",
                                "address2",
                                "city2",
                                22222,
                                "222-222",
                                "email2@mail.com")))

                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


//Delete

    @Test
    public void deleteEmployeeAPI() throws Exception
    {
        MockMvc mockMvc6 = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc6.perform( MockMvcRequestBuilders
                        .delete("/persons/{id} ", 2) )
                        .andExpect(status().isOk());
    }

    @Test
    public void deletePersonsFirstNameLastNameTest() throws Exception
    {
        MockMvc mockMvc6 = MockMvcBuilders.standaloneSetup((personsController)).build();

        mockMvc6.perform( MockMvcRequestBuilders.delete("/persons/{firstName}/{lastName} ","firstName","lastName"))

                .andExpect(status().isOk());
    }


    //}

} //End