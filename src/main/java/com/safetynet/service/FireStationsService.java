package com.safetynet.service;

import com.safetynet.model.FireStations;
import com.safetynet.repository.FireStationsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class FireStationsService {
    @Autowired
    private FireStationsRepository fireStationsRepository;

    public Optional<FireStations> getPersons(final Long id) {

        return fireStationsRepository.findById(id);
    }

    public Iterable<FireStations> getFireStations() {

        return fireStationsRepository.findAll();
    }

    public void deleteFirestations(final Long id) {

        fireStationsRepository.deleteById(id);
    }

    public FireStations saveFirestations(FireStations firestations) {

        FireStations savedFirestations = fireStationsRepository.save(firestations);
        return firestations;
    }
}