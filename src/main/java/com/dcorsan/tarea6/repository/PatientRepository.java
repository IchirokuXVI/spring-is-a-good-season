package com.dcorsan.tarea6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dcorsan.tarea6.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {
	
}
