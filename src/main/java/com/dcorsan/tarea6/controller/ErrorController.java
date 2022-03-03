package com.dcorsan.tarea6.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dcorsan.tarea6.exception.ModelValidationException;

@ControllerAdvice
public class ErrorController {
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception e, Model model) {

		model.addAttribute("errorMsg", e.getMessage());
		model.addAttribute("insertView", "error/default");
		
		return "main";
	}
	
	@ExceptionHandler(ModelValidationException.class)
	public String handleModelValidationException(HttpServletRequest req, ModelValidationException e, Model model) {

		model.addAttribute("fieldErrors", e.getFieldErorrs());
		model.addAttribute("insertView", "error/modelValidation");
		
		return "main";
	}
	
}
