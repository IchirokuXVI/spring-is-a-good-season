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
import com.dcorsan.tarea6.services.AdmissionServiceI;
import com.dcorsan.tarea6.services.DoctorServiceI;

@RestController
@RequestMapping("/rest/admissions")
public class AdmissionRestController {

	@Autowired
	private AdmissionServiceI admissionService;
	
	/**
	 * Index all the doctor
	 * @return All the doctor in the database
	 */
	@GetMapping
	private List<Admission> index() {
		return admissionService.getAll();
	}
	
	/**
	 * Get data of an admission
	 * @return The admission specified in URL
	 */
	@GetMapping("/{admission}")
	private Admission get(@ModelAttribute Admission admission) {
		return admission;
	}
	
	/**
	 * Creates a new doctor with the given data
	 * @param doctor
	 */
	@PostMapping
	private ResponseEntity<?> store(@RequestBody Admission admission) {
		return ResponseEntity.status(HttpStatus.CREATED).body(admissionService.add(admission));
	}
	
	/**
	 * Updates a doctor
	 * @param doctor
	 * @return
	 */
	@PutMapping
	private Admission update(@RequestBody Admission admission) {
		return admissionService.update(admission);
	}
	
	/**
	 * Deletes a doctor
	 * @param doctor
	 * @return
	 */
	@DeleteMapping("/{admission}")
	private ResponseEntity<?> destroy(@PathVariable long admission) {
		admissionService.delete(admission);
		
		return ResponseEntity.noContent().build();
	}
}
