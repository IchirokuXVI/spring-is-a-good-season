package com.dcorsan.tarea6.services;

import java.util.List;

import com.dcorsan.tarea6.model.Admission;

public interface AdmissionService {
	public List<Admission> getAll();
	
	public Admission find(final long id);
		
	public void add(final Admission admission);
	
	public void update(final Admission admission);
	
	public Admission delete(final long id);
}
