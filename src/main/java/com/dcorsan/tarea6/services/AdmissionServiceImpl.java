package com.dcorsan.tarea6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcorsan.tarea6.model.Admission;
import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.repository.AdmissionRepository;

@Service
public class AdmissionServiceImpl implements AdmissionServiceI {

	@Autowired
	AdmissionRepository admissionRepository;
	
	@Override
	public List<Admission> getAll() {
		return admissionRepository.findAll();
	}
	
	@Override
	public List<Admission> getAll(Patient patient, Doctor doctor) {
		return admissionRepository.findByPatientAndDoctor(patient, doctor);
	}

	@Override
	public Admission find(long id) {
		return admissionRepository.findById(id).get();
	}

	@Override
	public Admission add(Admission admission) {
		return admissionRepository.save(admission);
	}

	@Override
	public Admission update(Admission admission) {
		return admissionRepository.save(admission);
	}

	@Override
	public void delete(long id) {
		admissionRepository.deleteById(id);
	}

}
