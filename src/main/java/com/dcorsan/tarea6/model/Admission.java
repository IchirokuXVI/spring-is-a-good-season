package com.dcorsan.tarea6.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "admission")
public class Admission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long code;
	
	@Column(nullable = false)
	private String roomCode;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String doctorCode;
	private String patientCode;
}
