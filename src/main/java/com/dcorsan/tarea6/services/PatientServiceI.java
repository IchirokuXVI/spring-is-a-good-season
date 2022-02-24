package com.dcorsan.tarea6.services;

import java.util.List;

import com.dcorsan.tarea6.model.Patient;

public interface PatientServiceI {
	public List<Patient> getAll();
	
	public Patient find(final String id);
	
	public boolean exists(String id);
		
	public void add(final Patient patient);
	
	public void update(final Patient patient);
	
	public void delete(final String id);
}
