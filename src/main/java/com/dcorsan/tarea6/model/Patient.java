package com.dcorsan.tarea6.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String poblation;
	
	@Column(nullable = false)
	private String zipCode;
	
	// Might be null or even duplicated
	// because the patient could be a child
	private String phoneNumber;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date bornDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patientCode", cascade = CascadeType.ALL)
	private List<Admission> admissions;
}
