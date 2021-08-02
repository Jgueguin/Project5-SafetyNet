package com.safetynet.service;

import com.safetynet.model.persons;
import com.safetynet.repository.PersonsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PersonsService {
    @Autowired
    private PersonsRepository personsRepository;

    public Optional<persons> getPersons(final Long id) {
        return personsRepository.findById(id);

    }

    public Iterable<persons> getEmployees() {
        return personsRepository.findAll();
    }

    public void deletePersons(final Long id) {
        personsRepository.deleteById(id);
    }

    public persons savePersons(persons persons) {
        com.safetynet.model.persons savedPersons = personsRepository.save(persons);
        return savedPersons;
    }
}