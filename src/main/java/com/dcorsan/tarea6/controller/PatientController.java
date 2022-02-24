package com.dcorsan.tarea6.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping
	private String index(Model model) {
		model.addAttribute("patients", patientService.getAll());
		
		model.addAttribute("insertView", "patient/index");
		return "main";
	}
	
	@GetMapping("/create")
	private String create(Model model) {
		
		model.addAttribute("insertView", "patient/create");
		return "main";
	}
	
	@PostMapping
	private String store(@Valid @ModelAttribute Patient patient, BindingResult result, @RequestParam String bornDate) throws Exception {
		if (result.hasErrors())
			//throw new Exception("Error creating the patient");
		
		patient.setBornDate(new Date(bornDate));
		
		patientService.add(patient);
		
		return "redirect:patients";
	}
	
	@GetMapping("/{patient}/edit")
	private String edit(@PathVariable("patient") String code, Model model) {
		model.addAttribute("patient", patientService.find(code));
		model.addAttribute("insertView", "patient/edit");
		
		return "main";
	}
	
	@PutMapping("/{patient}")
	@Transactional
	private String update(@Valid @ModelAttribute Patient patient, BindingResult result, @PathVariable("patient") String code, Model model) throws Exception {
		if (result.hasErrors())
			throw new Exception("Error updating patient");
		
		if (!patient.getCode().equals(code))
			throw new Exception("Patient code cannot be updated");
		
		patientService.update(patient);
		
		return "redirect:";
	}
	
	@DeleteMapping("/{patient}")
	private String destroy(@PathVariable("patient") String code) {
		patientService.delete(code);
		
		return "redirect:";
	}
}
