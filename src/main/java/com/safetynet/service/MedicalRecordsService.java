package com.safetynet.service;

import com.safetynet.model.medicalRecords;
import com.safetynet.repository.MedicalRecordsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class MedicalRecordsService {
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    public Optional<medicalRecords> getMedicalRecords(final Long id) {

        return medicalRecordsRepository.findById(id);
    }

    public Iterable<medicalRecords> getMedicalRecords() {

        return medicalRecordsRepository.findAll();
    }

    public void deleteMedicalRecords(final Long id) {

        medicalRecordsRepository.deleteById(id);
    }

    public medicalRecords saveMedicalRecords(medicalRecords medicalRecords) {

        com.safetynet.model.medicalRecords savedMedicalRecords = medicalRecordsRepository.save(medicalRecords);
        return medicalRecords;
    }
}