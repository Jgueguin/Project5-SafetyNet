package com.safetynet.service;

import com.safetynet.model.MedicalRecords;
import com.safetynet.repository.MedicalRecordsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
}