package com.cts.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.dtos.UserRequestDto;
import com.cts.entity.User;
import com.cts.repository.UserRepository;
import com.cts.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Override
	public void addUser(UserRequestDto userRequestDto) {
		User user =new User();
		user.setUsername(userRequestDto.getUsername());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		user.setEmail(userRequestDto.getEmail());
		user.setPhone(userRequestDto.getPhone());
		user.setGender(userRequestDto.getGender());
		user.setRole(userRequestDto.getRole());
	    userRepository.save(user);
	}

	@Override
	public String verify(UserRequestDto userRequestDto) {
		Authentication authentication  = manager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.getEmail(), userRequestDto.getPassword()));
		
		if(authentication.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return "User logged in successfully";
		}
		
		return "User is not found";
	}

}
