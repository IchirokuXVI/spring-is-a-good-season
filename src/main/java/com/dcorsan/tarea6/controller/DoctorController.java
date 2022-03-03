package com.dcorsan.tarea6.controller;

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
import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.services.DoctorServiceI;
import com.dcorsan.tarea6.services.PatientServiceI;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorServiceI doctorService;
	
	@GetMapping
	private String index(Model model) {
		model.addAttribute("doctors", doctorService.getAll());
		
		model.addAttribute("insertView", "doctor/index");
		return "main";
	}
	
	@GetMapping("/create")
	private String create(Model model) {
		
		model.addAttribute("insertView", "doctor/create");
		return "main";
	}
	
	@PostMapping
	private String store(@Valid @ModelAttribute Doctor doctor, BindingResult result) throws Exception {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
				
		doctorService.add(doctor);
		
		return "redirect:doctors";
	}
	
	@GetMapping("/{doctor}/edit")
	private String edit(@ModelAttribute Doctor doctor, Model model) {
		model.addAttribute("doctor", doctor);
		model.addAttribute("insertView", "doctor/edit");
		
		return "main";
	}
	
	@PutMapping("/{doctor}")
	@Transactional
	private String update(@Valid @ModelAttribute Doctor doctor, BindingResult result, Model model) throws Exception {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		doctorService.update(doctor);
		
		return "redirect:";
	}
	
	@DeleteMapping("/{doctor}")
	private String destroy(@PathVariable("doctor") String code) {
		doctorService.delete(code);
		
		return "redirect:";
	}
}
