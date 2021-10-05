
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.CommunityEmailByCityDTO;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fire controller.
 */
@RestController
public class DtoCommunityEmailCityController {


    /**
     * @see PersonsService
     */
    PersonsService personsService;


    /**
     * Instantiates a new EmailCity controller.
     *
     * @param personsService the person service
     */
    public DtoCommunityEmailCityController(PersonsService personsService) {

        this.personsService = personsService;
    }


    // http://localhost:8080/communityEmail?city=<city>

    /**
     * EmailCity controller list.
     *
     * @param city :  the city
     * @return the emails
     */
  @GetMapping("/communityEmail")
    public ResponseEntity<CommunityEmailByCityDTO> emailCityControllerDTO (@RequestParam String city) {

        return ResponseEntity.ok(personsService.findEmailByCityDTO(city));
    }


} //END



