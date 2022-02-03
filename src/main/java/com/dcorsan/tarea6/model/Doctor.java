package com.dcorsan.tarea6.model;

import java.io.Serializable;
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
@Table(name = "doctor")
public class Doctor implements Serializable {
	private String code;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	@Column(nullable = false)
	private String specialty;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorCode", cascade = CascadeType.ALL)
	private List<Admission> admissions;
}
