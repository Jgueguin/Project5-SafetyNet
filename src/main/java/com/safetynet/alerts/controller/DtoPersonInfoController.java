
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonInfoByFirstNameAndLastNameListDTO;
import com.safetynet.alerts.service.PersonInfoService;
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

public class DtoPersonInfoController {

    private static final Logger logger = LogManager.getLogger("DtoPersonInfoController");

    /**
     * @see PersonInfoService
     */
    PersonInfoService personInfoService;

    String first;
    String last;

    /**
     * Instantiates a new PersonInfo controller.
     *
     * @param personInfoService the person service
     */
    public DtoPersonInfoController(PersonInfoService personInfoService) {

        logger.info("Instantiates a new controller");

        this.personInfoService = personInfoService;
    }

    // http://localhost:9090/personInfo?firstName=<firstName>&lastName=<lastName>

    /**
     * PersonInfo controller list.
     *
     * @param firstName
     * @param lastName
     * @return
     */
  @GetMapping("/personInfo")
    public ResponseEntity<PersonInfoByFirstNameAndLastNameListDTO> findPersonInfoControllerDTO (
            @RequestParam String firstName,
            @RequestParam String lastName
  ) {

      logger.info("Get Mapping PersonInfoByFirstNameAndLastNameListDTO Controller");

      first = firstName;
      last = lastName;

        return ResponseEntity.ok(personInfoService.firstNameAndLastNameDTO(firstName,lastName));
    }


} //END




