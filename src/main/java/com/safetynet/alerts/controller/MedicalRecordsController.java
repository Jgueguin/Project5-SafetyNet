package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.service.MedicalRecordsService;
import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
import javassist.NotFoundException;
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

    @PutMapping("/medicalrecords/{id}")
    public ResponseEntity<MedicalRecords> updateMedicalRecords(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody MedicalRecords medicalRecordsDetails) throws ResourceNotFoundException {
        MedicalRecords medicalrecordsUpdate = medicalRecordsService.getMedicalRecords(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ id));

        medicalrecordsUpdate.setFirstName(medicalRecordsDetails.getFirstName());
        medicalrecordsUpdate.setLastName(medicalRecordsDetails.getLastName());
        medicalrecordsUpdate.setBirthDate(medicalRecordsDetails.getBirthDate());
        medicalrecordsUpdate.setMedications(medicalRecordsDetails.getMedications());
        medicalrecordsUpdate.setAllergies(medicalRecordsDetails.getAllergies());

        final MedicalRecords updatedMedicalRecords = medicalRecordsService.saveMedicalRecords(medicalrecordsUpdate);

        return ResponseEntity.ok(updatedMedicalRecords);
    }



    //2021-08-19
    /**
     * Modify a medical Records with its firstname and lastname
     * @param firstName
     * @param lastName
     * @param medicalRecords
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/medicalrecords/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecords> updateMedicalRecordsByFirstNameLastName(
            @PathVariable(value = "firstName") String firstName,
            @PathVariable(value = "lastName") String lastName,
            @Valid @RequestBody final MedicalRecords medicalRecords) throws NotFoundException {

        MedicalRecords medicalRecordsUpdate = medicalRecordsService.findByFirstNameAndLastName(firstName,lastName);
        MedicalRecords medicalRecordsUpdated = medicalRecordsService.updateMedicalRecord(medicalRecords, medicalRecordsUpdate);

        final MedicalRecords medicalRecordsSaved = medicalRecordsService.saveUpdated(medicalRecordsUpdate);

        LOGGER.info("PersonController (PUT) -> Successfully updated person: "
                + medicalRecordsUpdated.toString());

        return ResponseEntity.ok(medicalRecordsSaved);
    }




} //END
