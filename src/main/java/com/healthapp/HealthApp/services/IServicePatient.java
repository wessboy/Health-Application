package com.healthapp.HealthApp.services;

import com.healthapp.HealthApp.entities.Patient;

import java.util.List;

public interface IServicePatient {
    public Patient savePatient(Patient patient);
    public void deletePatient(Patient patient);
    public List<Patient> getAllPatient();
    public Patient getPatientById(int id);

}
