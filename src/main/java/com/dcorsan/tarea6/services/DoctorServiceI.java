package com.dcorsan.tarea6.services;

import java.util.List;

import com.dcorsan.tarea6.model.Doctor;

public interface DoctorServiceI {
	public List<Doctor> getAll();
	
	public Doctor find(final String id);
	
	public List<Doctor> findBySpecialty(final String specialty);
	
	public void add(final Doctor doctor);
	
	public void update(final Doctor doctor);
	
	public Doctor delete(final String id);
}
