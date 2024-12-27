package com.cts.test; 


import com.cts.entity.Course;
import com.cts.entity.Student; 
import com.cts.repository.CourseRepository; 
import com.cts.repository.StudentRepository; 
import com.cts.serviceImpl.StudentServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.extension.ExtendWith; 
import org.mockito.InjectMocks; 
import org.mockito.Mock; 
import org.mockito.junit.jupiter.MockitoExtension; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import java.util.Collections; 
import static org.junit.jupiter.api.Assertions.*; 
 
import static org.mockito.Mockito.*; 

@ExtendWith(MockitoExtension.class) 
public class ServiceImplTest 
{
	
	@Mock
	private StudentRepository studentRepository; 
	
	@Mock 
	private CourseRepository courseRepository; 
	
	@Mock 
	private PasswordEncoder encoder; 
	
	@InjectMocks 
	private StudentServiceImpl studentService; 
	
	private Student student; 
	
	private Course course; 
	
	@BeforeEach 
	void setUp() 
	{
		student = Student.builder() 
				.name("John Doe") 
				.percentage(85.5f) 
				.branch("CSE") 
				.email("john@gmail.com") 
				.password("password") 
				.role("STUDENT")
				.build(); 
		course = new Course();
		course.setId(1L); 
		course.setCourseName("Math"); 
} 
	@Test 
	void testGetAllStudent()
	{ 
		when(studentRepository.findAll()).thenReturn(Collections.singletonList(student)); 
		assertFalse(studentService.getAllStudent().isEmpty());
	}
}

