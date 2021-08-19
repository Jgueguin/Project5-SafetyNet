package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.repository.MedicalRecordsRepository;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


/**
 * Medical Records Service
 */
@Data
@Service
public class MedicalRecordsService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    /**
     * Select one medical record between all
     * @param id : parameter to choose a medical record
     * @return: the choosen medical records
     */
    public Optional<MedicalRecords> getMedicalRecords(final Long id) {

        return medicalRecordsRepository.findById(id);
    }

    /**
     * select all the medical records
     * @return all the medical records
     */
    public Iterable<MedicalRecords> getMedicalRecords() {

        return medicalRecordsRepository.findAll();
    }

    /** delete a choosen medical records
     * @param id : parameter to choose the medical record to delete
     */
    public void deleteMedicalRecords(final Long id) {

        medicalRecordsRepository.deleteById(id);
    }

    /**
     * Save a new medical record
     * @param medicalRecords
     * @return : save the new medical records into Repository
     */
    public MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) {

        MedicalRecords savedMedicalRecords = medicalRecordsRepository.save(medicalRecords);
        return medicalRecords;
    }


    /**
     * Save all medical records.
     *
     * @param medicalRecords the medical records
     * @return list of medical records saved
     */
    public Iterable<MedicalRecords> saveAll(
            Iterable<MedicalRecords> medicalRecords) {
        return medicalRecordsRepository.saveAll(medicalRecords);

    }



    /**
     * Find  medical record by first name and last name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the medical record
     * @throws NotFoundException if no medical record was found
     */
    public MedicalRecords findByFirstNameAndLastName(String firstName,
                                                    String lastName) {
        try {
             // LOGGER.debug(
               //     "MedicalRecordService -> Searching for person " + firstName
                 //           + " " + lastName + " ...");
            MedicalRecords medicalRecord = medicalRecordsRepository.findByFirstNameAndLastName(
                    firstName, lastName);
            if (medicalRecord == null) {

                // LOGGER.error(
                   //     "MedicalRecordService -> " + firstName + " " + lastName
                     //           + " doesn't exist");
                throw new NotFoundException(
                        "Person " + firstName + " " + lastName
                                + " doesn't exist");
            }
            LOGGER.info(
                    "MedicalRecordService -> Medical record for " + firstName
                            + " " + lastName + " was found");
            return medicalRecord;
        } catch (NotFoundException e) {
            return new MedicalRecords(null, "", "", null, new String[]{""},
                    new String[]{""});
        }

    }

    /**
     * Update medical record.
     *
     * @param medicalRecordsBody    the medical record body
     * @param medicalRecordsUpdated the medical record updated
     * @return the medical record updated
     */
    public MedicalRecords updateMedicalRecord(MedicalRecords medicalRecordsBody,
                                             MedicalRecords medicalRecordsUpdated) {

        medicalRecordsUpdated.setBirthDate(medicalRecordsBody.getBirthDate());
        medicalRecordsUpdated.setMedications(medicalRecordsBody.getMedications());
        medicalRecordsUpdated.setAllergies(medicalRecordsBody.getAllergies());

        return medicalRecordsUpdated;
    }

    /**
     * Save updated medical record.
     *
     * @param medicalRecords the medical record
     * @return the medical record saved
     */
    public MedicalRecords saveUpdated(MedicalRecords medicalRecords) {
        return medicalRecordsRepository.save(medicalRecords);
    }




}