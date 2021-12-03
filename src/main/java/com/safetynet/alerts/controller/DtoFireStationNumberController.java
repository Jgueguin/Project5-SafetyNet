
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FireStationByStationNumberDTO;
import com.safetynet.alerts.service.FireStationByStationNumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fire controller.
 */
@RestController

public class DtoFireStationNumberController {

// public class DtoFireStationNumberController implements HealthIndicator {



    /**
     * @see FireStationByStationNumberService
     */
    FireStationByStationNumberService fireStationByStationNumberService;

    Integer number;

    /**
     * Instantiates a new Fire controller.
     *
     * @param fireStationByStationNumberService the person service
     */
    public DtoFireStationNumberController(FireStationByStationNumberService fireStationByStationNumberService) {

        this.fireStationByStationNumberService = fireStationByStationNumberService;
    }


    // http://localhost:9090/fire?stationNumber=<station_number>

    /**
     * Fire controller list.
     *
     * @param stationNumber:  the station number
     * @return the list
     */
  @GetMapping("/fire")
    public ResponseEntity<FireStationByStationNumberDTO> fireControllerDTO (@RequestParam Integer stationNumber) {

      number = stationNumber;

        return ResponseEntity.ok(fireStationByStationNumberService.personByStationDTO(stationNumber));
    }

   /* @Override
    public Health health() {

        FireStationByStationNumberDTO list = fireStationByStationNumberService.personByStationDTO(number);

        if(list.getPersons().isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }
*/



} //END




