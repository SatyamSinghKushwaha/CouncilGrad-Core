package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.Course;
import com.councilgrad.councilgrad.model.Program;
import com.councilgrad.councilgrad.repository.CourseRepository;
import com.councilgrad.councilgrad.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ProgramRepository programRepository;

    public CourseService(CourseRepository courseRepository,
                         ProgramRepository programRepository) {
        this.courseRepository = courseRepository;
        this.programRepository = programRepository;
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public List<Course> getByProgram(Long programId) {
        return courseRepository.findByProgramId(programId);
    }

    public Course create(Course course, Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));
        course.setProgram(program);
        return courseRepository.save(course);
    }

    public Course update(Long id, Course updated) {
        return courseRepository.findById(id).map(c -> {
            c.setName(updated.getName());
            c.setLevel(updated.getLevel());
            return courseRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
