package com.cts.service;

import com.cts.dtos.UserRequestDto;
import com.cts.entity.User;

public interface UserService {
	public void addUser(UserRequestDto userRequestDto);

	public String verify(UserRequestDto userRequestDto);

	
}
