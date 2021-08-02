package com.safetynet.service;

import com.safetynet.model.MedicalRecords;
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

    public Optional<MedicalRecords> getMedicalRecords(final Long id) {

        return medicalRecordsRepository.findById(id);
    }

    public Iterable<MedicalRecords> getMedicalRecords() {

        return medicalRecordsRepository.findAll();
    }

    public void deleteMedicalRecords(final Long id) {

        medicalRecordsRepository.deleteById(id);
    }

    public MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) {

        MedicalRecords savedMedicalRecords = medicalRecordsRepository.save(medicalRecords);
        return medicalRecords;
    }
}