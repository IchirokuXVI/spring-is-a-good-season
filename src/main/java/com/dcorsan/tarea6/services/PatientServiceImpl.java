package com.dcorsan.tarea6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientServiceI {

	@Autowired
	PatientRepository patientRepository;
	
	@Override
	public List<Patient> getAll() {
		return patientRepository.findAll();
	}

	@Override
	public Patient find(String id) {
		return patientRepository.findById(id).get();
	}

	@Override
	public boolean exists(String id) {
		return patientRepository.existsById(id);
	}
	
	@Override
	public void add(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void update(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void delete(String id) {
		patientRepository.deleteById(id);
	}

}
