
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FireStationByStationNumberDTO;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fire controller.
 */
@RestController
public class DtoFireStationNumberController {


    /**
     * @see PersonsService
     */
    PersonsService personsService;


    /**
     * Instantiates a new Fire controller.
     *
     * @param personsService the person service
     */
    public DtoFireStationNumberController(PersonsService personsService) {

        this.personsService = personsService;
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

        return ResponseEntity.ok(personsService.personByStationDTO(stationNumber));
    }

} //END




