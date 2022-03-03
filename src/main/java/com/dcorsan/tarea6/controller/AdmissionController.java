package com.dcorsan.tarea6.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dcorsan.tarea6.exception.ModelValidationException;
import com.dcorsan.tarea6.model.Admission;
import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.services.AdmissionServiceI;
import com.dcorsan.tarea6.services.DoctorServiceI;
import com.dcorsan.tarea6.services.PatientServiceI;

@Controller
@RequestMapping("/admissions")
public class AdmissionController {

	@Autowired
	private AdmissionServiceI admissionService;
	@Autowired
	private DoctorServiceI doctorService;
	@Autowired
	private PatientServiceI patientService;
	
	/**
	 * Binds a list of admissions correctly filtered with the specified patient and doctor
	 * The list will have all the records if no filter is specified
	 * @param patient Filter results based on the specified patient
	 * @param doctor Filter results based on the specified doctor
	 * @param model
	 * @return Main view (with an attribute in model to insert another view within it)
	 */
	@GetMapping
	private String index(@RequestParam(value = "patient", required = false) Patient patient, @RequestParam(value = "doctor", required = false) Doctor doctor, Model model) {
		List<Admission> admissions = null;
		if (patient != null || doctor != null) {
			if (patient != null && doctor != null) {
				admissions = admissionService.getAll(patient, doctor);
			} else if (patient != null) {
				admissions = patient.getAdmissions();
			} else {
				admissions = doctor.getAdmissions();
			}
			
			if (patient != null)
				model.addAttribute("searchPatient", patient);
			
			if (doctor != null)
				model.addAttribute("searchDoctor", doctor);
			
			
		} else {
			admissions = admissionService.getAll();
		}
		
		
		model.addAttribute("doctors", doctorService.getAll());
		model.addAttribute("patients", patientService.getAll());
		
		model.addAttribute("admissions", admissions);
		
		model.addAttribute("insertView", "admission/index");
		return "main";
	}
	
	/**
	 * Returns the view for creating an admission
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	private String create(Model model) {
		
		model.addAttribute("doctors", doctorService.getAll());
		model.addAttribute("patients", patientService.getAll());
		model.addAttribute("insertView", "admission/create");
		
		return "main";
	}
	
	/**
	 * Creates a new admission if the data is valid. Redirects to admissions index after creating the model
	 * @param admission
	 * @param result
	 * @return
	 * @throws ModelValidationException If the data is invalid
	 */
	@PostMapping
	private String store(@Valid @ModelAttribute Admission admission, BindingResult result) throws ModelValidationException {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		admissionService.add(admission);
		
		return "redirect:admissions";
	}
	
	/**
	 * Returns the view for updating an admission with all of its data
	 * @param admission
	 * @param model
	 * @return
	 */
	@GetMapping("/{admission}/edit")
	private String edit(@ModelAttribute Admission admission, Model model) {
		model.addAttribute("admission", admission);
		model.addAttribute("doctors", doctorService.getAll());
		model.addAttribute("patients", patientService.getAll());
		model.addAttribute("insertView", "admission/edit");
		
		return "main";
	}
	
	/**
	 * Updates the specified admission. The admission code must be passed in the URL and the data through request parameters
	 * @param admission
	 * @param result
	 * @param model
	 * @return
	 * @throws ModelValidationException
	 */
	@PutMapping("/{admission}")
	@Transactional
	private String update(@Valid @ModelAttribute Admission admission, BindingResult result, Model model) throws ModelValidationException {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		admissionService.update(admission);
		
		return "redirect:";
	}
	
	/**
	 * Deletes the admission specified in the URL
	 * @param code
	 * @return
	 */
	@DeleteMapping("/{admission}")
	private String destroy(@PathVariable("admission") long code) {
		admissionService.delete(code);
		
		return "redirect:";
	}
}
