package com.healthapp.HealthApp.services;

import com.healthapp.HealthApp.entities.Patient;
import com.healthapp.HealthApp.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicePatient implements  IServicePatient{

    @Autowired
    IPatientRepository _patientRepository;
    @Override
    public  Patient savePatient(Patient patient) {
        return _patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Patient patient) {
        _patientRepository.delete(patient);

    }

    @Override
    public List<Patient> getAllPatient() {
        return  _patientRepository.findAll();
    }


    @Override
    public Patient getPatientById(int id) {
        return  _patientRepository.findById(id).get();
    }
}
