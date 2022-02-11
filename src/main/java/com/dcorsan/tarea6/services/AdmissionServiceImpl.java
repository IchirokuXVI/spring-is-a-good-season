package com.dcorsan.tarea6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcorsan.tarea6.model.Admission;
import com.dcorsan.tarea6.repository.AdmissionRepository;

@Service
public class AdmissionServiceImpl implements AdmissionService {

	@Autowired
	AdmissionRepository admissionRepository;
	
	@Override
	public List<Admission> getAll() {
		return admissionRepository.findAll();
	}

	@Override
	public Admission find(long id) {
		return admissionRepository.findById(id).get();
	}

	@Override
	public void add(Admission admission) {
		admissionRepository.save(admission);
	}

	@Override
	public void update(Admission admission) {
		admissionRepository.save(admission);
	}

	@Override
	public void delete(long id) {
		admissionRepository.deleteById(id);
	}

}
