package com.healthapp.HealthApp.repository;

import com.healthapp.HealthApp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPatientRepository extends JpaRepository<Patient,Integer> {


}
