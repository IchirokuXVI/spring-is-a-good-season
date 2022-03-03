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
	
	/**
	 * Returns a view with all the doctors in the database
	 * @param model
	 * @return
	 */
	@GetMapping
	private String index(Model model) {
		model.addAttribute("doctors", doctorService.getAll());
		
		model.addAttribute("insertView", "doctor/index");
		return "main";
	}
	
	/**
	 * Returns a view for creating a new doctor
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	private String create(Model model) {
		
		model.addAttribute("insertView", "doctor/create");
		return "main";
	}
	
	/**
	 * Creates a new doctor with the given data through request parameters
	 * Redirects to doctors index after creating the model
	 * @param doctor The doctor to be created
	 * @param result
	 * @return
	 * @throws ModelValidationException If the data is invalid
	 */
	@PostMapping
	private String store(@Valid @ModelAttribute Doctor doctor, BindingResult result) throws ModelValidationException {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
				
		doctorService.add(doctor);
		
		return "redirect:doctors";
	}
	
	/**
	 * Returns a view for updating a doctor. The form will be filled with all the data of the model
	 * @param doctor Doctor to be edited. It is used to fill the form data
	 * @param model
	 * @return
	 */
	@GetMapping("/{doctor}/edit")
	private String edit(@ModelAttribute Doctor doctor, Model model) {
		model.addAttribute("doctor", doctor);
		model.addAttribute("insertView", "doctor/edit");
		
		return "main";
	}
	
	/**
	 * Updates a doctor based on the data passed through request parameters and the code in the URL
	 * @param doctor Doctor to be updated (code automatically set with the one in the URL)
	 * @param result
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/{doctor}")
	@Transactional
	private String update(@Valid @ModelAttribute Doctor doctor, BindingResult result, Model model) throws Exception {
		if (result.hasErrors())
			throw new ModelValidationException(result.getFieldErrors());
		
		doctorService.update(doctor);
		
		return "redirect:";
	}
	
	/**
	 * Deletes the specified doctor in the URL
	 * @param code Code of the doctor to be deleted
	 * @return
	 */
	@DeleteMapping("/{doctor}")
	private String destroy(@PathVariable("doctor") String code) {
		doctorService.delete(code);
		
		return "redirect:";
	}
}
