package com.dcorsan.tarea6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dcorsan.tarea6.model.Admission;
import com.dcorsan.tarea6.model.Doctor;
import com.dcorsan.tarea6.model.Patient;
import com.dcorsan.tarea6.services.AdmissionServiceI;
import com.dcorsan.tarea6.services.DoctorServiceI;
import com.dcorsan.tarea6.services.PatientServiceI;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class CorralesSanchezDanielPspHlcUt6Tarea6Application implements CommandLineRunner {

	@Autowired
	private PatientServiceI patientService;
	
	@Autowired
	private DoctorServiceI doctorService;
	
	@Autowired
	private AdmissionServiceI admissionService;
	
	public static void main(String[] args) {
		SpringApplication.run(CorralesSanchezDanielPspHlcUt6Tarea6Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		for (Patient p : patientService.getAll()) {
			System.out.println(p);
		}
		
		for (Doctor d : doctorService.getAll()) {
			System.out.println(d);
		}
		
		for (Admission a : admissionService.getAll()) {
			System.out.println(a);
		}
	}

}
