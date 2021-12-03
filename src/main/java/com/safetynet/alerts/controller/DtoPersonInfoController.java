
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonInfoByFirstNameAndLastNameListDTO;
import com.safetynet.alerts.service.PersonInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fire controller.
 */
@RestController

public class DtoPersonInfoController {
// public class DtoPersonInfoController implements HealthIndicator {



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
      first = firstName;
      last = lastName;

        return ResponseEntity.ok(personInfoService.firstNameAndLastNameDTO(firstName,lastName));
    }

    /*@Override
    public Health health() {

        PersonInfoByFirstNameAndLastNameListDTO list = personInfoService.firstNameAndLastNameDTO(first,last);

        if(list.getPersonInfoArray().isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }*/





} //END




