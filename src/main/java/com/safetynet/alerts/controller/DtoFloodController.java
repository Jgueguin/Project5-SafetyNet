
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FloodListDTO;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Flood controller.
 */
@RestController
public class DtoFloodController {


    /**
     * @see PersonsService
     */
     PersonsService personsService;



    /**
     * Instantiates a new Flood controller.
     *
     * @param personsService the person service
     */
    public DtoFloodController(PersonsService personsService) {

        this.personsService = personsService;

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

        return ResponseEntity.ok(personsService.floodDTO(station));
    }


} //END




