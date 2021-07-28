package com.safetynet.alerts;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Main program
 * @author jgueguin
 * @version 1.0
 */


public class App {
    public static void main(String args[]) throws IOException {

        // path and file name defined as constants
        String json2 = "../Project5-SafetyNet/Resources/Data.json";

        BufferedReader in = new BufferedReader(new FileReader(json2));
        String line;

        String fileJson = "";

        while ((line = in.readLine()) != null)
        {
            // Afficher le contenu du fichier
            //System.out.println (line);
            fileJson=fileJson+line;
        }
        in.close();
        System.out.println(fileJson);

//exemple

        String userJson = "{'age':26,'email':'norman@futurestud.io','isDeveloper':true,'name':'Norman'}";

        Gson gson = new Gson();
        UserSimple userObject = gson.fromJson(userJson, UserSimple.class);

        System.out.println(userObject.tostring());

        // fin exemple


        /*** ********/


        MyModel model = new MyModel();

        try {
            System.out.println(model.persons.contains(1));

        model.firestations.setid(1);
            System.out.println(model.firestations.getAdress() );

        }
        catch (Exception e) {
            System.out.println(e);
        }



        Gson gson2 = new Gson();
        Persons userObject2 = gson2.fromJson(fileJson,Persons.class);
        System.out.println(userObject2.tostring());
       // userObject2.setid(0);
        System.out.println(userObject2.tostring());
        //userObject2.setid(1);
        System.out.println(userObject2.tostring());




        Gson gson3 = new Gson();
        Persons2 userObject3 = gson3.fromJson(fileJson,Persons2.class);
        userObject3.setid(0);
        System.out.println(userObject3.tostring());
        userObject3.setid(1);
        System.out.println(userObject3.tostring());




    }


    // Models


    public class UserSimple {
        String name;
        String email;
        int age;
        boolean isDeveloper;

        //@Override
        public String tostring() {
            return "[name=" + name
                    +", email="+email
                    + "]";
        }

    }




    static class MyModel {

        private ArrayList<Persons> persons;
        private FireStations firestations;
        private MedicalRecords medicalrecords;


    }


    class Persons {

        //private Long id;
        private String firstName;
        private String lastName;
        private String adress;
        private String city;
        private Long zip;
        private String phone;
        private String email;

        /* public Long getid() {
            return id;
        }
        public void setid(long id) {
            this.id = id;
        }
*/

        public String getFirstName() {
            return firstName;
        }
        public void setfirstname(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastname) {
            this.lastName = lastname;
        }

        public String getAdress() {
            return adress;
        }
        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }

        public Long getZip() {
            return zip;
        }
        public void setZip(Long zip) {
            this.zip = zip;
        }

        public String phone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String email() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        //@Override
        public String tostring() {
            return "persons [id=" + "id"
                    + ", firstname=" + firstName
                    + ", lastname=" + lastName
                    + ", adress="+ adress
                    + ", city="+ city
                    + ", zip=" + zip
                    + ", phone=" + phone
                    + ", email=" + email + "]";
        }



    }


    class Persons2 {

        private Long id;
        private HashMap<Long,String> data = new HashMap<Long, String>();


        public Long getid() {
            return id;
        }
        public void setid(long id) {
            this.id = id;
        }

        //@Override
        public String tostring() {
            return "persons [id=" + id
                    +", HashMap="+data

                    + "]";
        }







        }



    class FireStations {

        private Long id;
        private String adress;
        private String station;


        public Long getid() {
            return id;
        }
        public void setid(long id) {
            this.id = id;
        }


        public String getAdress() {
            return adress;
        }
        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getStation() {
            return station;
        }
        public void setStation(String station) {
            this.station = station;
        }


    }

    class MedicalRecords {

        private Long id;
        private String firstName;
        private String lastName;
        private String birthdate;
        private ArrayList<String> medications = new ArrayList<>();
        private ArrayList<String> allergies = new ArrayList<>();

    }


 }