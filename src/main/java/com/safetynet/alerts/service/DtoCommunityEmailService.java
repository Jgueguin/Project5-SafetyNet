package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.model.dto.CommunityEmailByCityListDTO;
import com.safetynet.alerts.repository.*;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * Dto Community Email Service
 */

@Data
@Service
public class DtoCommunityEmailService {

    @Autowired
    private DtoPersonsRepository dtoPersonsRepository;



    // logger
    private static final Logger logger = LogManager.getLogger("DtoCommunityEmailService");

    /**
     * find email for persons living in a given city
     *
     * @param city
     * @return
     */
    public CommunityEmailByCityListDTO extractEmailByCityDTO(String city) {

        logger.info("Call of Extract Email By City DTO Service ");

        CommunityEmailByCityListDTO emailArray = new CommunityEmailByCityListDTO();

        for (Persons p : dtoPersonsRepository.findEmailByCity(city)) {

            ArrayList<String> tmp = emailArray.getEmailArray();
            tmp.add("Firstname: " + p.getFirstName() + " -- Lastname: " + p.getLastName() + "-> Email: " + p.getEmail());

            emailArray.setEmailArray(tmp);
        }

        return emailArray;
    }

}
