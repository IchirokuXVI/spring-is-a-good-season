package com.dcorsan.tarea6.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of="code")
public class Doctor implements Serializable {
	@Id
	@Getter @Setter private String code;
	@Column(nullable = false)
	@Getter @Setter private String name;
	@Column(nullable = false)
	@Getter @Setter private String surname;
	@Column(nullable = false, unique = true)
	@Getter @Setter private String phoneNumber;
	@Column(nullable = false)
	@Getter @Setter private String specialty;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL)
	@ToString.Exclude
	@Getter private List<Admission> admissions;

	private static final long serialVersionUID = 1L;
}
