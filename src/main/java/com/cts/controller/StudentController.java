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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.dtos.StudentRequestDto;
import com.cts.entity.Student;
import com.cts.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController 
{
	
	@Autowired
	private StudentService studentService;

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@GetMapping("/students")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@PostMapping("/addStudent")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void createStudent(@RequestBody @Valid StudentRequestDto student) 
	{
		logger.info("Received request to create student with details: {}", student);

		try {
			studentService.createStudent(student);
			logger.info("Student created successfully with details: {}", student);
		} catch (Exception e) {
			logger.error("Error occurred while creating student with details: {}", student, e);
			throw e;
		}
	}

	@PutMapping("/updateStudent/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student)
	{
		logger.info("Received request to update student with ID: {}", id);
		Student updatedStudent = studentService.updateStudent(id, student);
		logger.info("Student with ID: {} updated successfully", id);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	@DeleteMapping("/deleteStudent/{id}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public void deleteStudent(@PathVariable long id) 
	{
		logger.info("Received request to delete student with ID: {}", id);
		studentService.deleteStudent(id);
		logger.info("Student with ID: {} deleted successfully", id);
	}

	@PostMapping("/{student_id}/enroll/{course_id}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<String> enroll(@PathVariable("student_id") long studentId,@PathVariable("course_id") long courseId) 
	{
		logger.info("Received request to enroll student with ID: {} to course with ID: {}", studentId, courseId);
		studentService.enroll(studentId, courseId);
		logger.info("Student with ID: {} enrolled to course with ID: {}", studentId, courseId);
		return new ResponseEntity<>("Student is enrolled to the Course", HttpStatus.OK);
	}

	@DeleteMapping("/{student_id}/delete/{course_id}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<String> delete(@PathVariable("student_id") long studentId,@PathVariable("course_id") long courseId)
	{
		logger.info("Received request to delete course with ID: {} from student with ID: {}", courseId, studentId);
		studentService.delete(studentId, courseId);
		logger.info("Course with ID: {} deleted from student with ID: {}", courseId, studentId);
		return new ResponseEntity<>("Course is deleted from student", HttpStatus.OK);
	}
}
