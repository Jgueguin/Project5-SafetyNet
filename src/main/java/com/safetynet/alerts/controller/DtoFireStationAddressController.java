
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FireStationByAddressDTO;
import com.safetynet.alerts.service.FireStationByAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fire controller.
 */
@RestController
public class DtoFireStationAddressController {


    /**
     * @see FireStationByAddressService
     */
    FireStationByAddressService fireStationByAddressService;



    /**
     * Instantiates a new Fire controller.
     *
     * @param personsService the person service
     */
    public DtoFireStationAddressController(FireStationByAddressService fireStationByAddressService) {

        this.fireStationByAddressService = fireStationByAddressService;
    }

    /*http://localhost:9090/fire?address=<address>
    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.*/


    /**
     * FireStation By Address controller .
     *
     * @param address:  the station address
     * @return the list
     */
    @GetMapping("/firesta")
    public ResponseEntity<FireStationByAddressDTO> FireStationController2DTO (@RequestParam String address) {

        return ResponseEntity.ok(fireStationByAddressService.personsCoveredByAddress2(address));
    }

} //END




