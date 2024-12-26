package com.cts.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.validation.annotation.Validated; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import com.cts.dtos.UserRequestDto;
import com.cts.service.UserService; 
import jakarta.validation.Valid; 

@RestController 
@RequestMapping("/user") 
@Validated 
public class UserController
{
	@Autowired private UserService service; 
	
	@PostMapping("/signup") 
	public ResponseEntity<String> add( @Valid @RequestBody UserRequestDto userRequestDto)
	{ 
		service.addUser(userRequestDto); 
		return new ResponseEntity<>("User is Created Successfully", HttpStatus.OK); 
	} 
	
	@PostMapping("/signin") 
	public ResponseEntity<String> login(@RequestBody UserRequestDto userRequestDto)
	{ 
		return new ResponseEntity<String>(service.verify(userRequestDto),HttpStatus.OK); 
	} 
}
