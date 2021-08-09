package com.safetynet.alerts;

import com.safetynet.alerts.utilities.JsonReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App implements CommandLineRunner{

	/**
	 * @see JsonReader
	 */
	private JsonReader jsonReader;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		jsonReader.readJsonAndSaveToDb();
	}


	/**
	 * Instantiates a new Alerts application.
	 	 * @param jsonReader : the json reader
	 */
	public App(JsonReader jsonReader) {
		this.jsonReader = jsonReader;
	}





}




