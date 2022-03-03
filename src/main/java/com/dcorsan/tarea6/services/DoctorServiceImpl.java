package com.dcorsan.tarea6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorServiceI {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Override
	public List<Doctor> getAll() {
		return doctorRepository.findAll();
	}

	@Override
	public Doctor find(String id) {
		return doctorRepository.findById(id).get();
	}

	@Override
	public List<Doctor> findBySpecialty(String specialty) {
		return doctorRepository.findBySpecialty(specialty);
	}

	@Override
	public Doctor add(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor update(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public void delete(String id) {
		doctorRepository.deleteById(id);
	}

}
