package com.dcorsan.tarea6.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcorsan.tarea6.model.Admission;
import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.services.PatientServiceI;

@RestController
@RequestMapping("/rest/patients")
public class PatientRestController {

	@Autowired
	private PatientServiceI patientService;
	
	/**
	 * Index all the patients
	 * @return All the patients in the database
	 */
	@GetMapping
	private List<Patient> index() {
		return patientService.getAll();
	}
	
	/**
	 * Get data of a patient
	 * @return The patient specified in URL
	 */
	@GetMapping("/{patient}")
	private Patient get(@ModelAttribute Patient patient) {
		return patient;
	}
	
	/**
	 * Get admissions of a patient
	 * @return The admissions of the patient specified in URL
	 */
	@GetMapping("/{patient}/admissions")
	private List<Admission> getAdmissions(@ModelAttribute Patient patient) {
		return patient.getAdmissions();
	}
	
	/**
	 * Creates a new patient with the given data
	 * @param patient
	 */
	@PostMapping
	private ResponseEntity<?> store(@RequestBody Patient patient) {
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.add(patient));
	}
	
	/**
	 * Updates a patient
	 * @param patient
	 * @return
	 */
	@PutMapping
	private Patient update(@RequestBody Patient patient) {
		return patientService.update(patient);
	}
	
	/**
	 * Deletes a patient
	 * @param patient
	 * @return
	 */
	@DeleteMapping("/{patient}")
	private ResponseEntity<?> destroy(@PathVariable String patient) {
		patientService.delete(patient);
		
		return ResponseEntity.noContent().build();
	}
}
