package com.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.repository.PersonsRepository;
import com.safetynet.service.PersonsService;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.safetynet.model.*;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

	/*
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World, hey, hey!");
	}*/

	@Bean
	CommandLineRunner runner(PersonsService personService) {
		return args -> {

			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();

			// TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};

			TypeReference<List<Persons>> typeReference = new TypeReference<List<Persons>>(){};

			InputStream inputStream = TypeReference.class.getResourceAsStream("Data.json");


			try {
				List<PersonsRepository> persons = mapper.readValue(inputStream,typeReference);
				personService.savePersons(persons);
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save persons: " + e.getMessage());
			}
		};
	}



}
