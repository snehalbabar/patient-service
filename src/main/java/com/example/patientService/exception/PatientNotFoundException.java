package com.example.patientService.exception;

public class PatientNotFoundException extends RuntimeException{

   public PatientNotFoundException(String message){
       super(message);
   }
}
