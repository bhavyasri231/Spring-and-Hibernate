package com.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        try{

            ObjectMapper mapper = new ObjectMapper();

           // Student theStudent =
                    mapper.readValue(new File("data/sample-lite.json"),Student.class);
            Student theStudent =
                    mapper.readValue(new File("data/sample-full.json"),Student.class);

            System.out.println("First Name ="+theStudent.getFirstName());

            System.out.println("Last Name ="+theStudent.getLastName());

            Address myAddress=theStudent.getAddress();

            System.out.println("Street = "+myAddress.getStreet());
            System.out.println("City = "+myAddress.getCity());
            System.out.println("State = "+myAddress.getState());

            for(String tempLang : theStudent.getLanguages()){

                System.out.println(tempLang);
            }



        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
