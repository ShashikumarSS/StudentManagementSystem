package com.cts.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.cts.dtos.StudentRequestDto;
import com.cts.entity.Course;
import com.cts.entity.Student;
import com.cts.exception.StudentNotFound;
import com.cts.repository.CourseRepository;
import com.cts.repository.StudentRepository;
import com.cts.service.StudentService;

@Service
@Validated
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<Student> getAllStudent() {
		
		return studentRepository.findAll();
	}

	@Override
	public void createStudent(StudentRequestDto student) {
		
		Student stu=Student.builder()
				.name(student.getName())
				.percentage(student.getPercentage())
				.branch(student.getBranch())
				.email(student.getEmail())
				.password(encoder.encode(student.getPassword()))
				.role("STUDENT")
				.build();
		studentRepository.save(stu);
		
	}

	@Override
	public void deleteStudent(long id) {
		this.studentRepository.deleteById(id);
		
	}

	@Override
	public void enroll(long studentId, long courseId) {
		
		Student student = studentRepository.findById(studentId).orElseThrow(
				()->new StudentNotFound("Student Id you given is Does Not exist"));
		Course course = courseRepository.findById(courseId).get();
		Set<Course> courseSet = student.getCourses();
		courseSet.add(course);
		student.setCourses(courseSet);
		
		studentRepository.save(student);
		
		
		
	}

	@Override
	public void delete(long studentId, long courseId) {
		Student student = studentRepository.findById(studentId).orElseThrow(
				()->new StudentNotFound("Student Id you given is Does Not exist"));
		Course course = courseRepository.findById(courseId).get();
		Set<Course> courseSet = student.getCourses();
		courseSet.remove(course);
		student.setCourses(courseSet);
		
		studentRepository.save(student);
	}

	@Override
	public Student updateStudent(long id, Student student) {
	    Optional<Student> optionalStudent = studentRepository.findById(id);
	    if (optionalStudent.isPresent()) {
	        Student existingStudent = optionalStudent.get();
	        existingStudent.setName(student.getName());
	        existingStudent.setPercentage(student.getPercentage());
	        existingStudent.setBranch(student.getBranch());
	        existingStudent.setEmail(student.getEmail());
	        existingStudent.setPassword(student.getPassword());
	        existingStudent.setRole(student.getRole());
	        return studentRepository.save(existingStudent);
	    } else {
	        throw new StudentNotFound("Student not found with id: " + id);
	    }
	}

}
