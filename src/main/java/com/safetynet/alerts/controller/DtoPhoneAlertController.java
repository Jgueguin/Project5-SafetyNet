
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PhoneAlertListDTO;
import com.safetynet.alerts.service.DtoPhoneAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Child Alert controller.
 */
@RestController
public class DtoPhoneAlertController  {

    //Logger
    private static final Logger logger = LogManager.getLogger("DtoPhoneAlertController");

    /**
     * @see DtoPhoneAlertService
     */
     DtoPhoneAlertService dtoPhoneAlertService;
     Integer number;

    /**
     * Instantiates a new Phone Alert controller.
     *
     * @param dtoPhoneAlertService the person service
     */
    public DtoPhoneAlertController(DtoPhoneAlertService dtoPhoneAlertService) {

        logger.info("Instantiates a new controller");

        this.dtoPhoneAlertService = dtoPhoneAlertService;

    }

    /*http://localhost:9090/phoneAlert?firestation=<firestation_number>

    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
    */

    /**
     * Phone Alert controller
     *
     * @param station :  the number of  the firestation
     * @return the emails
     */

  @GetMapping("/phoneAlert")
    public ResponseEntity<PhoneAlertListDTO> phoneAlertControllerDTO (@RequestParam Integer station) {

      logger.info("Get Mapping honeAlertListDTO Controller");

      number = station;

        return ResponseEntity.ok(dtoPhoneAlertService.phoneAlertDTO(station));
    }

} //END




