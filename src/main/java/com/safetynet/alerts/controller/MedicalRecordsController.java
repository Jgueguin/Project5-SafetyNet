package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/medicalrecords/{firstName}/{lastName}")
    public MedicalRecords getMedicalRecords(
            @PathVariable("firstName") final String firstName,
            @PathVariable("lastName") final String lastName)

    {

        return medicalRecordsService.findByFirstNameAndLastName(firstName, lastName);
    }





    /**
     * Delete - Delete a medical record
     *
     * @param id - The id of the medical record to delete
     */
    @DeleteMapping("/medicalrecords/{id}")
    public void deleteMedicalRecords(@PathVariable("id") final Long id) {

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


    //2021-08-26
    /**
     * modify a medical recoird by its id
     * @param id
     * @param medicalRecordsDetails
     * @return
     */
    @PutMapping("/medicalrecords/{id}")
    public MedicalRecords updateMedicalRecordsById(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody MedicalRecords medicalRecordsDetails)  {

        return medicalRecordsService.updateMedicalRecordById(id, medicalRecordsDetails);
    }


    //2021-08-19
    /**
     * Modify a medical Records with its firstname and lastname
     * @param firstName
     * @param lastName
     * @return medicalRecords
     */
   @PutMapping("/medicalrecords/{firstName}/{lastName}")
    public MedicalRecords updateMedicalRecordsByFirstNameAndLastName(

           @PathVariable(value = "firstName") String firstName,
           @PathVariable(value = "lastName") String lastName,
           @Valid @RequestBody MedicalRecords medicalRecordsDetails)  {

           return medicalRecordsService.updateMedicalRecordByFirstNameAndLastName(firstName,lastName,medicalRecordsDetails);

    }




} //END
