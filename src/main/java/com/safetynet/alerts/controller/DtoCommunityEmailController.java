
package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.CommunityEmailByCityListDTO;
import com.safetynet.alerts.service.DtoCommunityEmailService;
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
public class DtoCommunityEmailController  {

    private static final Logger logger = LogManager.getLogger("DtoCommunityEmailController");

    /**
     * @see DtoCommunityEmailController
     */
    DtoCommunityEmailService dtoCommunityEmailService;
    String cit;

    /**
     * Instantiates a new Community Email controller.
     *
     * @param dtoCommunityEmailService the person service
     */
    public DtoCommunityEmailController(DtoCommunityEmailService dtoCommunityEmailService) {

        logger.info("--> Instantiates a new controller");

        this.dtoCommunityEmailService = dtoCommunityEmailService;
    }


    // http://localhost:8080/communityEmail?city=<city>

    /**
     * EmailCity controller list.
     *
     * @param city :  the city
     * @return the emails
     */
  @GetMapping("/communityEmail")
    public ResponseEntity<CommunityEmailByCityListDTO> emailCityControllerDTO (@RequestParam String city) {

      logger.info("--> Get Mapping EmailCity controller");

      cit = city;

        return ResponseEntity.ok(dtoCommunityEmailService.extractEmailByCityDTO(city));
    }






} //END




