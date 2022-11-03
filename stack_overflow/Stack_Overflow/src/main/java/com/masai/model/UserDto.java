package com.masai.model;



import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDto {

	
	
	@NotNull(message = "Mobile is mandatory")
	private String mobileNo;
	
	@NotNull(message = "Password is mandatory")
	private String password;


	
	
}
