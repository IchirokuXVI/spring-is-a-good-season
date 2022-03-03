package com.dcorsan.tarea6.services;

import java.util.List;

import com.dcorsan.tarea6.model.Admission;
import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.model.Patient;

public interface AdmissionServiceI {
	public List<Admission> getAll();
	
	public List<Admission> getAll(Patient patient, Doctor doctor);
	
	public Admission find(final long id);
		
	public Admission add(final Admission admission);
	
	public Admission update(final Admission admission);
	
	public void delete(final long id);
}
