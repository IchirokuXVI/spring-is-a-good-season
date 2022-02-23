package com.dcorsan.tarea6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dcorsan.tarea6.services.PatientServiceI;

@Controller
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientServiceI patientService;
	
	@GetMapping
	private String index(Model model) {
		model.addAttribute("patients", patientService.getAll());
		
		model.addAttribute("insertView", "patient/index");
		return "main";
	}
	
	@GetMapping("/create")
	private String create() {
		return "";
	}
	
	@PostMapping
	private void store() {
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
