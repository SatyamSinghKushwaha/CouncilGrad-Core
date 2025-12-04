package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.Course;
import com.councilgrad.councilgrad.model.Specialization;
import com.councilgrad.councilgrad.repository.CourseRepository;
import com.councilgrad.councilgrad.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final CourseRepository courseRepository;

    public SpecializationService(SpecializationRepository specializationRepository,
                                 CourseRepository courseRepository) {
        this.specializationRepository = specializationRepository;
        this.courseRepository = courseRepository;
    }

    public List<Specialization> getByCourse(Long courseId) {
        return specializationRepository.findByCourseId(courseId);
    }

    public Specialization create(Specialization s, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        s.setCourse(course);
        return specializationRepository.save(s);
    }

    public void delete(Long id) {
        specializationRepository.deleteById(id);
    }
}
