package com.safetynet.alerts;

import com.safetynet.alerts.utilities.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class App implements CommandLineRunner{

	/**
	 * @see JsonReader
	 */
	private JsonReader jsonReader;

	private static final Logger logger = LogManager.getLogger("App");

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}



	 // logger.info("Initializing Parking System");

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

	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}


}




