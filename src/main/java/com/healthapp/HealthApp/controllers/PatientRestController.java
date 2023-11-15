package com.healthapp.HealthApp.controllers;

import com.healthapp.HealthApp.entities.Patient;
import com.healthapp.HealthApp.services.IServicePatient;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientRestController {

    @Autowired
    IServicePatient _iservicePatient;

    @GetMapping("/list")
    @ApiResponse(responseCode = "200",description = "OK")
    public ResponseEntity<List<Patient>> getAllPatients(){
            List<Patient> patientList =  _iservicePatient.getAllPatient().stream().toList();
        return new ResponseEntity<List<Patient>>(patientList,HttpStatus.OK);
    }


    @GetMapping("/patient/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "200", description = "Patient found")
    })
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") int id){
        if(id == 0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Patient patient = _iservicePatient.getPatientById(id);
        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",description = "Invalid Inputs"),
            @ApiResponse(responseCode = "201",description = "Patient Created Successfully")
    })
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Patient> SavePatient(@RequestBody Patient patient){
       if(patient == null){

           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }
        return new  ResponseEntity<Patient>(_iservicePatient.savePatient(patient),HttpStatus.CREATED);

    }

    @DeleteMapping("delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "204", description = "Patient deleted successfully")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deletePatient(@PathVariable("id") int id){
        if(id == 0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Patient patient = _iservicePatient.getPatientById(id);
        if(patient == null){

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
         _iservicePatient.deletePatient(patient);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }



}
