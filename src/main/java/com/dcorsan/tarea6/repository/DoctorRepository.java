package com.dcorsan.tarea6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dcorsan.tarea6.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
	List<Doctor> findBySpecialty(String specialty);
}
