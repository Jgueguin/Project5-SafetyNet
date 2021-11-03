package com.safetynet.alerts.utilities;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStations;
import com.safetynet.alerts.model.ImportData;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Persons;
import com.safetynet.alerts.service.FireStationsService;
import com.safetynet.alerts.service.MedicalRecordsService;
import com.safetynet.alerts.service.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


/**
 * Json reader.
 */
@Component
public class JsonReader {

    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(JsonReader.class);


    /**
     * @see com.safetynet.alerts.service.PersonsService
     */
    private PersonsService personsService;

    /**
     * @see FireStationsService
     */
    private FireStationsService fireStationsService;

    /**
     * @see MedicalRecordsService
     */
    private MedicalRecordsService medicalRecordsService;


    /**
     * Instantiation of Json reader.
     *
     * @param personsService         the person service
     * @param firestationsService    the fireStations service
     * @param medicalRecordsService  the medical record service
     */
    public JsonReader(PersonsService personsService,
                      FireStationsService firestationsService,
                      MedicalRecordsService medicalRecordsService) {
        this.personsService = personsService;
        this.fireStationsService = firestationsService;
        this.medicalRecordsService = medicalRecordsService;
    }

    /**
     * Read of json file and save it to db.
     */
    public void readJsonAndSaveToDb() {
        ObjectMapper mapper = null;
        InputStream is = null;
        File f = null;

        try {
            mapper = new ObjectMapper();
            f = new File("src/main/resources/data.json");
            is = new FileInputStream(f);

            ImportData lists = mapper.readValue(is, ImportData.class);

            List<Persons> persons = lists.getPersons();
            List<FireStations> fireStations = lists.getFireStations();

            List<MedicalRecords> medicalRecords = lists.getMedicalRecords();

            // test
                System.out.println();
                System.out.println(medicalRecords);
                System.out.println();
            //test

            personsService.saveAll(persons);
            fireStationsService.saveAll(fireStations);
            medicalRecordsService.saveAll(medicalRecords);

             LOGGER.debug("Data.json successfully read and saved in db");

        } catch (FileNotFoundException e) {
             LOGGER.error("File Data.json not found");
        } catch (JsonParseException e) {
             LOGGER.error("Error while parsing json");
        } catch (JsonMappingException e) {
             LOGGER.error("Error while mapping json");
        } catch (IOException e) {
            LOGGER.error("I/O error");
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                 LOGGER.error("Error while closing the InputStream");
            }
        }
    }

}

