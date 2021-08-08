package com.safetynet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;









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



	//@Bean
	public class JsonToDatabase {

		/*public static Connection ConnectToDB() throws Exception {

			//Registering the Driver
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			//Getting the connection
			String mysqlUrl = "jdbc:h2:mem:testdb";

			Connection con = DriverManager.getConnection(mysqlUrl, "sa", "password");
			System.out.println("Connection established......");
			return con;
		}*/

		private final Logger logger = LogManager.getLogger("DataBaseConfig");

		public Connection ConnectToDB() throws ClassNotFoundException, SQLException {
			logger.info("Create DB connection");

			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection(
					"jdbc:h2:mem:testdb","sa","password");
		}




		public static void main(String args[]) {

			//Creating a JSONParser object

			JSONParser jsonParser = new JSONParser();
			try {

				//Parsing the contents of the JSON file
				JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/home/jgueguin/git/Project5-SafetyNet/src/main/resources/Data.json"));

				//Retrieving the array
				JSONArray jsonArray = (JSONArray) jsonObject.get("persons");
				Connection con = ConnectToDB();

				//Insert a row into the MyPlayers table
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO persons values (?, ?, ?, ?, ?, ?,?,? )");

				for(Object object : jsonArray) {

					JSONObject record = (JSONObject) object;

					int id = Integer.parseInt((String) record.get("ID"));
					String first_name = (String) record.get("firstname");
					String last_name = (String) record.get("lastname");
					String adress = (String) record.get("adress");
					String city = (String) record.get("city");
					Integer zip = Integer.parseInt((String) record.get("zip"));
					String phone = (String) record.get("phone");
					String email = (String) record.get("email");

					pstmt.setInt(1, id);
					pstmt.setString(2, first_name);
					pstmt.setString(3, last_name);
					pstmt.setString(4, adress);
					pstmt.setString(5, city);
					pstmt.setInt(6, zip);
					pstmt.setString(7, phone);
					pstmt.setString(8, email);

					pstmt.executeUpdate();
				}
				System.out.println("Records inserted.....");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

















	}




