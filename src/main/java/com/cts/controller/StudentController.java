package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dtos.StudentRequestDto;
import com.cts.entity.Student;
import com.cts.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public List<Student> getAllStudent()
	{
		return studentService.getAllStudent();
	}
	
	@PostMapping("/addStudent")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void createStudent(@RequestBody @Valid StudentRequestDto student)
	{
		
		studentService.createStudent(student);
	}
	
	@PutMapping("/updateStudent/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Student updateStudent(@PathVariable long id)
	{
		return studentService.updateStudent(id);
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public void deleteStudent(@PathVariable long id)
	{
		studentService.deleteStudent(id);
	}
	
	@PostMapping("/{student_id}/enroll/{course_id}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<String> enroll(@PathVariable("student_id") long studentId,@PathVariable("course_id") long courseId){
		
		studentService.enroll(studentId,courseId);
		return new ResponseEntity<String>("Student is enrolled to the Course",HttpStatus.OK);
	}
	
	@DeleteMapping("/{student_id}/delete/{course_id}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<String> delete(@PathVariable("student_id") long studentId,@PathVariable("course_id") long courseId){
		
		studentService.delete(studentId,courseId);
		return new ResponseEntity<String>("Course is deleted from student",HttpStatus.OK);
	}

}
