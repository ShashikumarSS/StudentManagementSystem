package com.cts.controller;

import com.cts.entity.Course;
import com.cts.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping("/")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@PostMapping("/addCourse")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}

	@PutMapping("/updateCourse/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
		return courseService.updateCourse(id, course);
	}

	@DeleteMapping("/deleteCourse/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return new ResponseEntity<String>("Course is Deleted Succesfully", HttpStatus.OK);
	}
}
