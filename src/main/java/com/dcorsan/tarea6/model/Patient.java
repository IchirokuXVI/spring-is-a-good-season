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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of="code")
public class Patient implements Serializable {
	@Id
	@Column(nullable = false)
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
	@Size(min=5, max=5)
	@Getter @Setter private String zipCode;
	
	// Might be null or even duplicated
	// because the patient could be a child
	@Size(min=0, max=9)
	@Getter @Setter private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	@Getter @Setter private Date bornDate;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
	@ToString.Exclude
	@Getter private List<Admission> admissions;
	
	private static final long serialVersionUID = 1L;
}
