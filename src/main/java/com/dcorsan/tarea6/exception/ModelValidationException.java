package com.dcorsan.tarea6.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ModelValidationException extends Exception {

	private static final long serialVersionUID = -2944582971074158562L;
	
	private List<FieldError> fieldErrors;
	
	public ModelValidationException(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	public Map<String, List<String>> getFieldErorrs() {
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		
		for (FieldError fieldError : fieldErrors) {
			
			if (result.get(fieldError.getField()) == null) {
				result.put(fieldError.getField(), new ArrayList<String>());
			}
			
			result.get(fieldError.getField()).add(fieldError.getDefaultMessage());
		}
		
		return result;
	}
}
