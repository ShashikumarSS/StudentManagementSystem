package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> 
{
	
}
