package com.example.demo.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployeeEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Double empId;
	private String namePrefix;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String gender;
	private String email;
	private String fatherName;
	private String motherName;
	private String motherMaidenName;
	private Double ageInCompany;
	private Double salary;
	private String lastHike;
	
	@javax.persistence.Transient
	private MultipartFile file;
	
	
	public EmployeeEntity() {
		
	}
	
	
	public EmployeeEntity(Double empId, String namePrefix, String firstName, String middleInitial, String lastName,
			String gender, String email, String fatherName, String motherName, String motherMaidenName,
			Double ageInCompany, Double salary, String lastHike) {
		super();
		this.empId = empId;
		this.namePrefix = namePrefix;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.motherMaidenName = motherMaidenName;
		this.ageInCompany = ageInCompany;
		this.salary = salary;
		this.lastHike = lastHike;
	}
	
	
	
}
