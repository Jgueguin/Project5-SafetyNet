package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Medical Records Controller
 */

@RestController
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    public MedicalRecordsController(MedicalRecordsService medicalRecordsService){
        this.medicalRecordsService = medicalRecordsService;
    }

    /**
     * Read - Get all medical Records
     *
     * @return - An Iterable object of medical records full filled
     */
    @GetMapping("/medicalrecords")
    public Iterable<MedicalRecords> getMedicalRecords() {
        return medicalRecordsService.getMedicalRecords();
    }

    /**
     * Read - Get one medicalRecord
     * @param id The id of the medical record
     * @return A medical record object full filled
     */
    @GetMapping("/medicalrecords/{id}")
    public MedicalRecords getMedicalRecords(@PathVariable("id") final Long id) {
        Optional<MedicalRecords> medicalRecords = (medicalRecordsService.getMedicalRecords(id));
        if(medicalRecords.isPresent()) {
            return medicalRecords.get();
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a medical record
     *
     * @param id - The id of the medical record to delete
     */
    @DeleteMapping("/medicalrecords/{id}")
    public void deleteMedicalRecords(@PathVariable("id") final Long id) {

        medicalRecordsService.deleteMedicalRecords(id);
    }


    /**
     * Create - Add a new medical record
     *
     * @param medicalRecords: An object medical record
     * @return The medical record object saved
     */
    @PostMapping("/medicalrecords")
    public MedicalRecords createMedicalRecords(@RequestBody MedicalRecords medicalRecords) {

        return medicalRecordsService.saveMedicalRecords(medicalRecords);
    }

    /**
     * Modify - a medical records
     * @param firstName
     * @param lastName
     * @param medicalRecords
     * @return
     */
    @PutMapping("/medicalrecords/}")
    public ResponseEntity<MedicalRecords> updateMedicalRecords(
            //@PathVariable(value = "id") final Long id,
            @RequestParam ("firstName") String firstName,
            @RequestParam ("lastName") String lastName,
            @Valid @RequestBody final MedicalRecords medicalRecords)
    {
        MedicalRecords medicalRecordToUpdate =  medicalRecordsService.findByFirstNameAndLastName(
                firstName, lastName);

        MedicalRecords medicalRecordUpdated = medicalRecordsService.updateMedicalRecord(
                medicalRecords, medicalRecordToUpdate);

        MedicalRecords medicalRecordSaved = medicalRecordsService.saveUpdated(
                medicalRecordUpdated);

        LOGGER.info("MedicalRecordController (PUT) -> Medical record "
                + "successfully " + "updated: "
                + medicalRecordUpdated.toString());
        return ResponseEntity.ok(medicalRecordSaved);
    }









}


