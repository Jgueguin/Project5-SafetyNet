
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonsCoveredByFireStationAddressDTO2;
import com.safetynet.alerts.service.PersonsService;
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
     * @see PersonsService
     */
    PersonsService personsService;



    /**
     * Instantiates a new Fire controller.
     *
     * @param personsService the person service
     */
    public DtoFireStationAddressController(PersonsService personsService) {

        this.personsService = personsService;
    }

    /*http://localhost:9090/fire?address=<address>
    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.*/



    /**
     * Fire controller list.
     *
     * @param address:  the station address
     * @return the list
     */
    @GetMapping("/firesta")
    public ResponseEntity<PersonsCoveredByFireStationAddressDTO2> FireStationController2DTO (@RequestParam String address) {

        return ResponseEntity.ok(personsService.personsCoveredByAddress2(address));
    }

} //END




