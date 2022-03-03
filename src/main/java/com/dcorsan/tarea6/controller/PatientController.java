package com.dcorsan.tarea6.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dcorsan.tarea6.exception.ModelValidationException;
import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.services.PatientServiceI;

@Controller
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientServiceI patientService;
	
	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	//     SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	//     binder.registerCustomEditor(Date.class, new CustomDateEditor(
	//             dateFormat, false));
	// }
	
	/**
	 * Returns a view with all the patients
	 * @param model
	 * @return
	 */
	@GetMapping
	private String index(Model model) {
		model.addAttribute("patients", patientService.getAll());
		
		model.addAttribute("insertView", "patient/index");
		return "main";
	}
	
	/**
	 * Returns a view for creating a new patient.
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	private String create(Model model) {
		
		model.addAttribute("insertView", "patient/create");
		return "main";
	}
	
	/**
	 * Creates a new patient with the data specified in request parameters
	 * Redirects to patients index after creating
	 * @param patient Patient to be inserted
	 * @param result
	 * @return Redirect to patients index
	 * @throws ModelValidationException If the data is invalid
	 */
	@PostMapping
	private String store(@Valid @ModelAttribute Patient patient, BindingResult result) throws ModelValidationException {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		patientService.add(patient);
		
		return "redirect:patients";
	}
	
	/**
	 * Returns a view for updating the patient. The form is filled with the patient data
	 * @param patient Patient to be updated. Data is gathered from this patient
	 * @param model
	 * @return
	 */
	@GetMapping("/{patient}/edit")
	private String edit(@ModelAttribute Patient patient, Model model) {
		model.addAttribute("patient", patient);
		model.addAttribute("insertView", "patient/edit");
		
		return "main";
	}
	
	/**
	 * Updates the given patient with the id set in the URL and the data in request parameters
	 * Redirects to patients index after updating
	 * @param patient Patient to be updated (code is automatically set with the one in the URL)
	 * @param result
	 * @param model
	 * @return
	 * @throws Exception If the data is invalid
	 */
	@PutMapping("/{patient}")
	@Transactional
	private String update(@Valid @ModelAttribute Patient patient, BindingResult result, Model model) throws Exception {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		patientService.update(patient);
		
		return "redirect:";
	}
	
	/**
	 * Deleted the specified patient in the URL
	 * @param code Code of the patient to be deleted
	 * @return
	 */
	@DeleteMapping("/{patient}")
	private String destroy(@PathVariable("patient") String code) {
		patientService.delete(code);
		
		return "redirect:";
	}
}
