package com.safetynet.Project5.Project5SafetyNet;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		System.out.println("Hello World");

	}

	//@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World, hey, hey!");
	}




}
