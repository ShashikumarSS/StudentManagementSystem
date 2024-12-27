package com.cts.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto 
{
	
	@NotBlank(message = "Name is mandatory")
	private String username;
	
	@NotEmpty(message = "Password must not be empty")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
	
	@NotEmpty(message = "Email must not be empty") 
	@Email(message = "Email should be valid")
	private String email;
	
	@NotEmpty(message = "Phone number must not be empty")
	@Pattern( regexp = "^\\d{10}$", message = "Phone number must be 10 digits" )
	private String phone;
	
	
	@NotEmpty(message = "Gender must not be empty")
	@Pattern( regexp = "Male|Female|Other", message = "Gender must be 'Male', 'Female', or 'Other'" )
    private String gender;
	
	@NotEmpty(message = "Role must not be empty") 
	@Pattern( regexp = "ADMIN|STUDENT", message = "Role must be 'ADMIN','STUDENT'" )
	private String role;
}
