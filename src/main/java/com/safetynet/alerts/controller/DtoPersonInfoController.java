
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonInfoByFirstNameAndLastNameDTO;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fire controller.
 */
@RestController
public class DtoPersonInfoController {

    /**
     * @see PersonsService
     */
    PersonsService personsService;




    /**
     * Instantiates a new PersonInfo controller.
     *
     * @param personsService the person service
     */
    public DtoPersonInfoController(PersonsService personsService) {

        this.personsService = personsService;
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
    public ResponseEntity<PersonInfoByFirstNameAndLastNameDTO> findPersonInfoControllerDTO (
            @RequestParam String firstName,
            @RequestParam String lastName
  ) {
        return ResponseEntity.ok(personsService.findfirstNameAndLastNameDTO(firstName,lastName));
    }


} //END




