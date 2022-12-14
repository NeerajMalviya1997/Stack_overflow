package com.masai.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "Name is mandatory")
	private String name;
	
	//@Size(max = 10,min = 10)
	private String mobileNo;
	
	@NotNull(message = "Password is mandatory")
	private String password;
	
	
	@NotNull(message = "Email is mandatory")
	private String email;


	
	
}