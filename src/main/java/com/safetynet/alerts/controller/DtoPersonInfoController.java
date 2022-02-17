
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonInfoByFirstNameAndLastNameListDTO;
import com.safetynet.alerts.service.DtoPersonInfoService;
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
     * @see DtoPersonInfoService
     */
    DtoPersonInfoService dtoPersonInfoService;

    String first;
    String last;

    /**
     * Instantiates a new PersonInfo controller.
     *
     * @param dtoPersonInfoService the person service
     */
    public DtoPersonInfoController(DtoPersonInfoService dtoPersonInfoService) {

        logger.info("Instantiates a new controller");

        this.dtoPersonInfoService = dtoPersonInfoService;
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

        return ResponseEntity.ok(dtoPersonInfoService.firstNameAndLastNameDTO(firstName,lastName));
    }


} //END




