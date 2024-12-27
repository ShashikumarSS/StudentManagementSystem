package com.cts.test;

import com.cts.entity.Course;
import com.cts.repository.CourseRepository;
import com.cts.serviceImpl.CourseServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseImplTest
{

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setId(1L);
        course.setCourseName("Math");
        course.setCreditHours(4);
    }

    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Collections.singletonList(course));

        List<Course> courses = courseService.getAllCourses();
        assertFalse(courses.isEmpty());
        assertEquals(1, courses.size());
        assertEquals(course.getCourseName(), courses.get(0).getCourseName());
    }

    @Test
    void testCreateCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course createdCourse = courseService.createCourse(course);
        assertNotNull(createdCourse);
        assertEquals(course.getCourseName(), createdCourse.getCourseName());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testUpdateCourse() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course updatedCourse = courseService.updateCourse(1L, course);
        assertNotNull(updatedCourse);
        assertEquals(course.getCourseName(), updatedCourse.getCourseName());
        assertEquals(course.getCreditHours(), updatedCourse.getCreditHours());
        verify(courseRepository, times(1)).findById(anyLong());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testUpdateCourse_NotFound() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseService.updateCourse(1L, course);
        });

        String expectedMessage = "Course not found with id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(courseRepository, times(1)).findById(anyLong());
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseRepository).deleteById(anyLong());

        courseService.deleteCourse(1L);
        verify(courseRepository, times(1)).deleteById(anyLong());
    }
}

