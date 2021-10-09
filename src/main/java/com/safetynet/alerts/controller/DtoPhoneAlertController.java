
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PhoneAlertListDTO;
import com.safetynet.alerts.service.PersonsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Child Alert controller.
 */
@RestController
public class DtoPhoneAlertController {


    /**
     * @see PersonsService
     */
     PersonsService personsService;



    /**
     * Instantiates a new Child Alert controller.
     *
     * @param personsService the person service
     */
    public DtoPhoneAlertController(PersonsService personsService) {

        this.personsService = personsService;

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

        return ResponseEntity.ok(personsService.phoneAlertDTO(station));
    }

} //END




