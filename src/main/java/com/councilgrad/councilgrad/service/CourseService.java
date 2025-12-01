package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.Course;
import com.councilgrad.councilgrad.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByProgram(Long programId) {
        return courseRepository.findByProgramId(programId);
    }
}
