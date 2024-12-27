package com.cts.service;

import java.util.List;
import com.cts.dtos.StudentRequestDto;
import com.cts.entity.Student;

public interface StudentService 
{
	List<Student>getAllStudent();
	
	void createStudent(StudentRequestDto student);
	
	Student updateStudent(long id, Student student);
	
	void deleteStudent(long id);

	void enroll(long studentId, long courseId);

	void delete(long studentId, long courseId);

}
