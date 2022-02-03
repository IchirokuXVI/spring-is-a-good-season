package com.dcorsan.tarea6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dcorsan.tarea6.model.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Long>{
	
}
