package com.cts.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class StudentRequestDto 
{
	
	@Column(name = "student_name")
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Column(name = "student_percentage")
	@Min(value = 1, message = "Percentage should not be less than 0")
	@Max(value = 100, message = "Percentage should not be more than 100") 
	private float percentage;
	
	@Column(name = "student_branch")
	@NotBlank(message = "Branch is mandatory") 
	private String branch;
	
	@Column(name = "email")
	@NotBlank(message = "email is mandatory") 	
	private String email;
	
	@Column(name = "password")
	@NotBlank(message = "Password is mandatory") 
	private String password;
	
	}
