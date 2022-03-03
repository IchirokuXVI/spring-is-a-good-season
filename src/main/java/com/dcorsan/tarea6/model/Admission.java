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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "admission")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of="code")
public class Admission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private long code;
	
	@Column(nullable = false)
	@Getter @Setter private String roomCode;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	@Getter @Setter private Date date;
	
	@JoinColumn(name = "doctor_code")
	@ManyToOne()
	@NotNull
	@Getter @Setter private Doctor doctor;
	@JoinColumn(name = "patient_code")
	@ManyToOne()
	@NotNull
	@Getter @Setter private Patient patient;
	
	private static final long serialVersionUID = 1L;
}
