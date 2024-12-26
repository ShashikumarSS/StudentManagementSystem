package com.cts.exception;

public class StudentNotFound extends RuntimeException{
	
	private String message;

	public StudentNotFound(String message) {
		super(message);
		this.message = message;
	}
	
	

}
