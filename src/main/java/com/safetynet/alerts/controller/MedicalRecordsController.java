package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
     * @return - An Iterable object of medical records full filled
     */
    @GetMapping("/medicalrecords")
    public ResponseEntity<Iterable<MedicalRecords>> getMedicalRecords() {

        return ResponseEntity.ok(medicalRecordsService.getMedicalRecords());
    }

    /**
     * Read - Get one medicalRecord
     * @param id The id of the medical record
     * @return A medical record object full filled
     */
    @GetMapping("/medicalrecords/{id}")
    public ResponseEntity<MedicalRecords> getMedicalRecords(@PathVariable("id") final Long id) {

        Optional<MedicalRecords> medicalRecords = (medicalRecordsService.getMedicalRecords(id));

        if(medicalRecords.isPresent()) {

            return ResponseEntity.ok(medicalRecords.get());

        } else {
            return null;
        }
    }

    @GetMapping("/medicalrecords/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecords> getMedicalRecords(
            @PathVariable("firstName") final String firstName,
            @PathVariable("lastName") final String lastName)
    {

        return ResponseEntity.ok(medicalRecordsService.findByFirstNameAndLastName(firstName, lastName));
    }

    /**
     * Delete - Delete a medical record
     * @param id - The id of the medical record to delete
     */
    @DeleteMapping("/medicalrecords/{id}")
    public void deleteMedicalRecords(
            @PathVariable("id") final Long id) {

        medicalRecordsService.deleteMedicalRecordsById(id);
    }

    //2021-08-27

    /**
     * Delete - Delete a medical record with firstname and lastname
     * @param firstName
     * @param lastName
     */

    @DeleteMapping("/medicalrecords/{firstName}/{lastName}")
    public void deleteMedicalRecords(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName    ) {

        medicalRecordsService.deleteMedicalRecordsByFirstNameAndLastName(firstName, lastName);
    }

//2021-09-10
    /**
     * Create - Add a new medical record
     *
     * @param medicalRecordsDetails: An object medical record
     * @return The medical record object saved
     */
    @PostMapping("/medicalrecords")
    public ResponseEntity<MedicalRecords> createMedicalRecords(
            @RequestBody MedicalRecords medicalRecordsDetails) {

        medicalRecordsService.saveMedicalRecords(medicalRecordsDetails);

        return ResponseEntity.ok(medicalRecordsDetails);
    }

    //2021-09-10
    /**
     * modify a medical record by its id
     * @param id
     * @param medicalRecordsDetails
     * @return
     */
    @PutMapping("/medicalrecords/{id}")
    public ResponseEntity<MedicalRecords> updateMedicalRecordsById(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody MedicalRecords medicalRecordsDetails)  {

        medicalRecordsService.updateMedicalRecordById(id, medicalRecordsDetails);

        return ResponseEntity.ok(medicalRecordsDetails);
    }


    //2021-09-10
    /**
     * Modify a medical Records with its firstname and lastname
     * @param firstName
     * @param lastName
     * @return medicalRecords
     */
   @PutMapping("/medicalrecords/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecords> updateMedicalRecordsByFirstNameAndLastName(
           @PathVariable(value = "firstName") String firstName,
           @PathVariable(value = "lastName") String lastName,
           @Valid @RequestBody MedicalRecords medicalRecordsDetails)  {

           medicalRecordsService.updateMedicalRecordByFirstNameAndLastName(firstName,lastName,medicalRecordsDetails);

           return  ResponseEntity.ok(medicalRecordsDetails);
    }

} //END
