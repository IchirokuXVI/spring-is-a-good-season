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
	
	@GetMapping
	private String index(@RequestParam(value = "patient", required = false) Patient patient, @RequestParam(value = "doctor", required = false) Doctor doctor, Model model) {
		List<Admission> admissions = null;
		if (patient != null || doctor != null) {
			if (patient != null && doctor != null) {
				
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
	
	@GetMapping("/create")
	private String create(Model model) {
		
		model.addAttribute("doctors", doctorService.getAll());
		model.addAttribute("patients", patientService.getAll());
		model.addAttribute("insertView", "admission/create");
		
		return "main";
	}
	
	@PostMapping
	private String store(@Valid @ModelAttribute Admission admission, BindingResult result) throws Exception {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		admissionService.add(admission);
		
		return "redirect:admissions";
	}
	
	@GetMapping("/{admission}/edit")
	private String edit(@ModelAttribute Admission admission, Model model) {
		model.addAttribute("admission", admission);
		model.addAttribute("doctors", doctorService.getAll());
		model.addAttribute("patients", patientService.getAll());
		model.addAttribute("insertView", "admission/edit");
		
		return "main";
	}
	
	@PutMapping("/{admission}")
	@Transactional
	private String update(@Valid @ModelAttribute Admission admission, BindingResult result, Model model) throws Exception {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		admissionService.update(admission);
		
		return "redirect:";
	}
	
	@DeleteMapping("/{admission}")
	private String destroy(@PathVariable("admission") long code) {
		admissionService.delete(code);
		
		return "redirect:";
	}
}
