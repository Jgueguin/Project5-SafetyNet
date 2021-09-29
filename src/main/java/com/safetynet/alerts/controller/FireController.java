
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonCoveredByFireStationDTO2;
import com.safetynet.alerts.service.PersonsService;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Fire controller.
 */
@RestController
public class FireController {

    /**
     * @see Logger
     */
    /*private static final Logger LOGGER = LogManager.getLogger(
            FloodController.class);*/

    /**
     * @see PersonsService
     */
    PersonsService personsService;


    /**
     * Instantiates a new Fire controller.
     *
     * @param personsService the person service
     */
    public FireController(PersonsService personsService) {

        this.personsService = personsService;
    }



    // http://localhost:9090/fire?stationNumber=<station_number>

    /**
     * Fire controller list.
     *
     * @param stationNumber the address
     * @return the list
     */
  @GetMapping("/fire")
    public ResponseEntity<List<PersonCoveredByFireStationDTO2>> fireControllerDTO (@RequestParam Long stationNumber) {

        return ResponseEntity.ok(personsService.findPersonByStationDTO(stationNumber));
    }

} //END




