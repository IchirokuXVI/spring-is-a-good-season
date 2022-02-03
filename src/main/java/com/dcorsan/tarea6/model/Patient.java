package com.dcorsan.tarea6.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {
	@Id
	@Getter @Setter private String code;
	
	@Column(nullable = false)
	@Getter @Setter private String name;
	
	@Column(nullable = false)
	@Getter @Setter private String surname;
	
	@Column(nullable = false)
	@Getter @Setter private String address;
	
	@Column(nullable = false)
	@Getter @Setter private String poblation;
	
	@Column(nullable = false)
	@Getter @Setter private String zipCode;
	
	// Might be null or even duplicated
	// because the patient could be a child
	@Getter @Setter private String phoneNumber;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@Getter @Setter private Date bornDate;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patientCode", cascade = CascadeType.ALL)
	@Getter private List<Admission> admissions;
}
