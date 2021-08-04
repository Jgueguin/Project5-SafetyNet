package com.safetynet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
/*
	@Bean
	CommandLineRunner runner(PersonsService personService) {
		return args -> {

			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();

			// TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};

			// ?????????
			TypeReference<Persons> typeReference = new TypeReference<List<Persons>>(){};
			// ?????????

			InputStream inputStream = TypeReference.class.getResourceAsStream("Data.json");

			try {
				List<Persons> persons = mapper.readValue(inputStream,typeReference);

				personService.savePersons((Persons) persons);

				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save persons: " + e.getMessage());
			}
		};*/
	}




