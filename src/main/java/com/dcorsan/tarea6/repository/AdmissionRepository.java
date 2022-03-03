package com.dcorsan.tarea6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dcorsan.tarea6.model.Admission;
import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.model.Patient;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
	List<Admission> findByPatientAndDoctor(Patient patient, Doctor doctor);
}
