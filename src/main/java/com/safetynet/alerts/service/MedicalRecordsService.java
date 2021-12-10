package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.repository.MedicalRecordsRepository;
import javassist.NotFoundException;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

/**
 * Medical Records Service
 */
@Data
@Service
public class MedicalRecordsService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    private static final Logger logger = LogManager.getLogger("App");

    /**
     * Select one medical record between all
     *
     * @param id : parameter to choose a medical record
     * @return: the choosen medical records
     */
    public Optional<MedicalRecords> getMedicalRecords(final Long id) {

        logger.info("Select one medical record ");

        return medicalRecordsRepository.findById(id);
    }

    /**
     * select all the medical records
     *
     * @return all the medical records
     */
    public Iterable<MedicalRecords> getMedicalRecords() {

        logger.info("Select all the Medical Records");

        return medicalRecordsRepository.findAll();
    }

    /**
     * delete a choosen medical records with an id
     *
     * @param id : parameter to choose the medical record to delete
     */
    public void deleteMedicalRecordsById(final Long id) {

        logger.info("Delete a choosen medical record");

        medicalRecordsRepository.deleteById(id);
    }


    //2021-09-04

    /**
     * Delete a medical record by a firstname and a lastname
     *
     * @param firstName
     * @param lastName
     */
    public void deleteMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {

        logger.info("Delete of a medical records by firstname and lastname");

        MedicalRecords medicalRecordsToDelete = medicalRecordsRepository.findByFirstNameAndLastName(firstName, lastName);
        medicalRecordsRepository.deleteById(medicalRecordsToDelete.getId());
    }


    /**
     * Save a new medical record
     *
     * @param medicalRecords
     * @return : save the new medical records into Repository
     */
    public MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) {

        logger.info("Save a new medical Record");

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

        logger.info("Save all medical Records");

        return medicalRecordsRepository.saveAll(medicalRecords);
    }

//2021-08-26

    /**
     * modify a Medical record by its id
     * @param id
     * @param medicalRecordsDetails
     * @return
     */
    public MedicalRecords updateMedicalRecordById(Long id,
                                                  MedicalRecords medicalRecordsDetails) {
        try {

            logger.info("Update a medical records by its id");

            Optional<MedicalRecords> optionalMedicalRecords = medicalRecordsRepository.findById(id);

            if (optionalMedicalRecords.isPresent()) {

                MedicalRecords medicalRecordsToUpdate = optionalMedicalRecords.get();

                String firstName = medicalRecordsDetails.getFirstName();
                if (firstName != null) {
                    medicalRecordsToUpdate.setFirstName(firstName);
                }

                String lastName = medicalRecordsDetails.getLastName();
                if (lastName != null) {
                    medicalRecordsToUpdate.setLastName(lastName);
                }

                Calendar birthDate = medicalRecordsDetails.getBirthDate();
                if (birthDate != null) {
                    medicalRecordsToUpdate.setBirthDate(birthDate);
                }

                String[] medications = medicalRecordsDetails.getMedications();
                if (medications != null) {
                    medicalRecordsToUpdate.setMedications(medications);
                }

                String[] allergies = medicalRecordsDetails.getAllergies();
                if (allergies != null) {
                    medicalRecordsToUpdate.setAllergies(allergies);
                }

                return medicalRecordsRepository.save(medicalRecordsToUpdate);
            }

        } catch (Exception e) {

            logger.error("Medical Record doesn't exist");

        }
        return null;
    }


    /**
     * modify a medical Record by firstname and LastName
     * @param firstName
     * @param lastName
     * @param medicalRecordsDetails
     * @return
     */
    public MedicalRecords updateMedicalRecordByFirstNameAndLastName(String firstName, String lastName,
                                                                    @Valid MedicalRecords medicalRecordsDetails) {
        try {

            logger.info("Update a medical records by firstname and lastname");

            MedicalRecords medicalRecordsToUpdate = medicalRecordsRepository.findByFirstNameAndLastName(firstName, lastName);

            Calendar birthDate = medicalRecordsDetails.getBirthDate();
            if (birthDate != null) {
                medicalRecordsToUpdate.setBirthDate(birthDate);
            }

            String[] medications = medicalRecordsDetails.getMedications();
            if (medications != null) {
                medicalRecordsToUpdate.setMedications(medications);
            }

            String[] allergies = medicalRecordsDetails.getAllergies();
            if (allergies != null) {
                medicalRecordsToUpdate.setAllergies(allergies);
            }
            return medicalRecordsRepository.save(medicalRecordsToUpdate);

        } catch (Exception e) {

            logger.error("Medical Record doesn't exist");

        }
        return null;
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

        logger.info("Find medical record by firstname and lastname");

        MedicalRecords medicalRecordToFind = medicalRecordsRepository.findByFirstNameAndLastName(firstName, lastName);

        return medicalRecordToFind;


    }


} //End