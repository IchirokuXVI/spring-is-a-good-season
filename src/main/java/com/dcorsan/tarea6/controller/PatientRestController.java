package com.dcorsan.tarea6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.services.PatientServiceI;

@RestController
@RequestMapping("/rest/patients")
public class PatientRestController {

	@Autowired
	private PatientServiceI patientService;
	
	@GetMapping
	private List<Patient> index() {
		return patientService.getAll();
	}
	
	@PostMapping
	private void store(@RequestBody Patient patient) {
		patientService.add(patient);
	}
	
	@PutMapping("/{patient}")
	private void update(@PathVariable("patient") String code) {
	}
	
	@DeleteMapping("/{patient}")
	private String destroy(@PathVariable("patient") String code) {
		patientService.delete(code);
		
		return "redirect:";
	}
}
