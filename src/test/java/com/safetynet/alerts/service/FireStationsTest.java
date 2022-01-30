package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.repository.FireStationsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest

@ExtendWith(MockitoExtension.class)

//annotation Junit 4
@RunWith(MockitoJUnitRunner.class)

public class FireStationsTest {

    @InjectMocks
    @Autowired
    private FireStationsService fireStationService;

    private static FireStations fireStation1 = new FireStations(1L, 1, "address1");
    private static FireStations fireStation2 = new FireStations(2L, 2, "address2");
    private static FireStations fireStation3 = new FireStations(3L, 3, "address3");

    private static FireStations fireStation4 = new FireStations(4L, 4, "address4");

    private static FireStations fireStation5 = new FireStations(5L, 5, "address5");

    List<FireStations> fireStationsList = new ArrayList<>();
    List<FireStations> fireStationsList2 = new ArrayList<>();
    List<FireStations> fireStationsList3 = new ArrayList<>();

    @Mock
    private FireStationsRepository fireStationsRepository;

    @Before
    public void setUp() {

        // annotation for junit5
        // MockitoAnnotations.initMocks(this);

        // add FireStations into a list of Firestation
        fireStationsList.add(fireStation1);
        fireStationsList.add(fireStation2);
        fireStationsList.add(fireStation3);

        // add FireStations into a list of Firestation
        fireStationsList2.add(fireStation1);
        fireStationsList2.add(fireStation2);

        // add FireStations into a list of Firestation
        fireStationsList3.add(fireStation1);
        fireStationsList3.add(fireStation2);
        fireStationsList3.add(fireStation3);
        fireStationsList3.add(fireStation4);
    }

    @Test
    @DisplayName("Get FireStation  by Id Test")
    public void getFireStations_By_Id_Test() {
        when(fireStationsRepository.findById(any())).thenReturn(Optional.ofNullable(fireStation1));

        assertEquals(Optional.ofNullable(fireStation1), fireStationService.getFireStationsById(1L));
    }

    @Test
    @DisplayName("Get All Firestations ")
    public void getAllFireStations_Test() {
        when(fireStationsRepository.findAll()).thenReturn(fireStationsList);
        assertEquals(fireStationsList, fireStationService.getFireStationsAll());
    }

    @Test
    @DisplayName("Delete a firestation by its id ")
    public void deleteFireStationsById_Test() {
        fireStationService.deleteFireStationsById(3L);

        int newListSize = fireStationsList.size()-1;
        assertEquals(newListSize, fireStationsList2.size());
    }

    @Test
    @DisplayName("Save a fireStation")
    public void saveFireStation_Test() {

        when(fireStationsRepository.save(any())).thenReturn(fireStation4);

        fireStationService.saveFirestations(fireStation4);

        assertEquals(fireStation4,fireStationService.saveFirestations(fireStation4));
    }

    @Test
    @DisplayName("Update a fireStation by its id")
    public void updateFireStationById_Test() {

        when(fireStationsRepository.findById(1L)).thenReturn(Optional.ofNullable(fireStation5));
        when (fireStationsRepository.save(any())).thenReturn(fireStation5);
        assertEquals(fireStation5,fireStationService.updateFireStationsById(1L,fireStation5));
    }

    @Test
    @DisplayName("Update a fireStation by its address")
    public void updateFireStationByAddress_Test() {

        when(fireStationsRepository.findByAddress(fireStation5.getAddress())).thenReturn(fireStation5);
        when (fireStationsRepository.save(any())).thenReturn(fireStation5);

        assertEquals(fireStation5,fireStationService.updateFireStationsByAddress(fireStation5.getAddress(), fireStation5));

    }

    @Test
    @DisplayName("fzfsed")
    public void test() {

        assertEquals(null,
        fireStationService.getFireStationsById(1L)
                );



    }


} //End