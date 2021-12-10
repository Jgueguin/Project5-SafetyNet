
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FloodListDTO;
import com.safetynet.alerts.service.FloodService;
import com.safetynet.alerts.service.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Flood controller.
 */
@RestController

public class DtoFloodController {

    private static final Logger logger = LogManager.getLogger("DtoFloodController");

    /**
     * @see PersonsService
     */
     FloodService floodService;
     Integer number;

    /**
     * Instantiates a new Flood controller.
     *
     * @param floodService the person service
     */
    public DtoFloodController(FloodService floodService) {

        logger.info("Instantiates a new controller");

        this.floodService = floodService;

    }

    // http://localhost:9090/childAlert?address=<address>

    /**
     * Flood controller
     *
     * @param station :  the number of a firestations
     * @return the emails
     */

  @GetMapping("/flood/stations")
    public ResponseEntity<FloodListDTO> floodControllerDTO (@RequestParam Integer station) {

      logger.info("Get Mapping FloodList Dto Controller");

      number = station;
        return ResponseEntity.ok(floodService.floodDTO(station));
    }


} //END




