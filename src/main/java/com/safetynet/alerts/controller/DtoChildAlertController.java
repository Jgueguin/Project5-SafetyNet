
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.ChildAlertListDTO;
import com.safetynet.alerts.service.DtoChildAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Child Alert controller.
 */
@RestController

 public class DtoChildAlertController  {


    /**
     * @see DtoChildAlertService
     */
    DtoChildAlertService dtoChildAlertService;

    String add;


    /**
     * Instantiates a new Child Alert controller.
     *
     * @param dtoChildAlertService the person service
     */
    public DtoChildAlertController(DtoChildAlertService dtoChildAlertService) {

        this.dtoChildAlertService = dtoChildAlertService;

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

      add = address;

        return ResponseEntity.ok(dtoChildAlertService.childAlertDTO(address));
    }


} //END




