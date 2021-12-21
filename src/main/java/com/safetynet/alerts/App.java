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
		logger.info("Initializing Application");

		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Read Json and Save To DataBase H2");
		jsonReader.readJsonAndSaveToDb();
	}


	/**
	 * Instantiates a new Alerts application.
	 	 * @param jsonReader : the json reader
	 */

	public App(JsonReader jsonReader) {
		logger.info("Initializing Json Reader");
		this.jsonReader = jsonReader;
	}

	@Bean
	public HttpTraceRepository httpTraceRepository() {
		logger.info("HttpTraceRepository");
		return new InMemoryHttpTraceRepository();
	}

}




