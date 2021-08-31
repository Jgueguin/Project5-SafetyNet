
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonFire;
import com.safetynet.alerts.service.PersonsService;
import org.apache.logging.log4j.Logger;
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

    /**
     * Fire controller list.
     *
     * @param address the address
     * @return the list
     */
   @GetMapping("/fire")
    public List<PersonFire> fireController(@RequestParam String address) {

        return personsService.getFireDtoListByStation(address);
    }

    */


} //END




