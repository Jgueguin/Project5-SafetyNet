
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.ChildAlertListDTO;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Child Alert controller.
 */
@RestController
public class DtoChildAlertController {


    /**
     * @see PersonsService
     */
     PersonsService personsService;



    /**
     * Instantiates a new Child Alert controller.
     *
     * @param personsService the person service
     */
    public DtoChildAlertController(PersonsService personsService) {

        this.personsService = personsService;

    }


    // http://localhost:9090/childAlert?address=<address>

    /**
     * Child Alert controller
     *
     * @param address :  address of the city
     * @return the emails
     */

  @GetMapping("/childAlert")
    public ResponseEntity<ChildAlertListDTO> childAlertControllerDTO (@RequestParam String address) {

        return ResponseEntity.ok(personsService.findChildAlertDTO(address));
    }


} //END




