package com.safetynet.service;

import com.safetynet.model.fireStations;
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

    public Optional<fireStations> getPersons(final Long id) {

        return fireStationsRepository.findById(id);
    }

    public Iterable<fireStations> getFireStations() {

        return fireStationsRepository.findAll();
    }

    public void deleteFirestations(final Long id) {

        fireStationsRepository.deleteById(id);
    }

    public fireStations saveFirestations(fireStations firestations) {

        fireStations savedFirestations = fireStationsRepository.save(firestations);
        return firestations;
    }
}