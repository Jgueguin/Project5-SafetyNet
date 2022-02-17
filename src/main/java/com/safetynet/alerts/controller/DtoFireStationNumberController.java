
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FireStationByStationNumberDTO;
import com.safetynet.alerts.service.DtoFireStationByStationNumberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fire controller.
 */

@RestController

public class DtoFireStationNumberController {

    private static final Logger logger = LogManager.getLogger("DtoFireStationNumberController");

    /**
     * @see DtoFireStationByStationNumberService
     */
    DtoFireStationByStationNumberService dtoFireStationByStationNumberService;

    Integer number;

    /**
     * Instantiates a new Fire controller.
     *
     * @param dtoFireStationByStationNumberService the person service
     */
    public DtoFireStationNumberController(DtoFireStationByStationNumberService dtoFireStationByStationNumberService) {

        logger.info("Instantiates a new controller");

        this.dtoFireStationByStationNumberService = dtoFireStationByStationNumberService;
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

      logger.info("Get Mapping FireStationByStationNumberDTO Controller");

          number = stationNumber;

        return ResponseEntity.ok(dtoFireStationByStationNumberService.personByStationDTO(stationNumber));
    }


} //END




