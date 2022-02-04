package com.dcorsan.tarea6.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admission")
@NoArgsConstructor
@AllArgsConstructor
public class Admission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter private long code;
	
	@Column(nullable = false)
	@Getter @Setter private String roomCode;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@Getter @Setter private Date date;
	
	@JoinColumn(name = "doctor_code")
	@ManyToOne()
	@Getter @Setter private Doctor doctor;
	@JoinColumn(name = "patient_code")
	@ManyToOne()
	@Getter @Setter private Patient patient;
	
	private static final long serialVersionUID = 1L;
}
