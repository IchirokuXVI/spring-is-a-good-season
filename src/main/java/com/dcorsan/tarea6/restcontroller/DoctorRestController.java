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
import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.services.DoctorServiceI;

@RestController
@RequestMapping("/rest/doctors")
public class DoctorRestController {

	@Autowired
	private DoctorServiceI doctorService;
	
	/**
	 * Index all the doctor
	 * @return All the doctor in the database
	 */
	@GetMapping
	private List<Doctor> index() {
		return doctorService.getAll();
	}
	
	/**
	 * Get data of a doctor
	 * @return The doctor specified in URL
	 */
	@GetMapping("/{doctor}")
	private Doctor get(@ModelAttribute Doctor doctor) {
		return doctor;
	}
	
	/**
	 * Get admissions of a patient
	 * @return The admissions of the patient specified in URL
	 */
	@GetMapping("/{doctor}/admissions")
	private List<Admission> getAdmissions(@ModelAttribute Doctor doctor) {
		return doctor.getAdmissions();
	}
	
	/**
	 * Creates a new doctor with the given data
	 * @param doctor
	 */
	@PostMapping
	private ResponseEntity<?> store(@RequestBody Doctor doctor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.add(doctor));
	}
	
	/**
	 * Updates a doctor
	 * @param doctor
	 * @return
	 */
	@PutMapping
	private Doctor update(@RequestBody Doctor doctor) {
		return doctorService.update(doctor);
	}
	
	/**
	 * Deletes a doctor
	 * @param doctor
	 * @return
	 */
	@DeleteMapping("/{doctor}")
	private ResponseEntity<?> destroy(@PathVariable String doctor) {
		doctorService.delete(doctor);
		
		return ResponseEntity.noContent().build();
	}
}
