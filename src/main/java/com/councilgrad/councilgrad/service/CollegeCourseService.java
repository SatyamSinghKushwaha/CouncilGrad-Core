package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.CollegeCourse;
import com.councilgrad.councilgrad.model.College;
import com.councilgrad.councilgrad.model.Course;
import com.councilgrad.councilgrad.repository.CollegeCourseRepository;
import com.councilgrad.councilgrad.repository.CollegeRepository;
import com.councilgrad.councilgrad.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeCourseService {

    private final CollegeCourseRepository collegeCourseRepository;
    private final CollegeRepository collegeRepository;
    private final CourseRepository courseRepository;

    public CollegeCourseService(CollegeCourseRepository collegeCourseRepository,
                                CollegeRepository collegeRepository,
                                CourseRepository courseRepository) {
        this.collegeCourseRepository = collegeCourseRepository;
        this.collegeRepository = collegeRepository;
        this.courseRepository = courseRepository;
    }

    public CollegeCourse create(CollegeCourse cc, Long collegeId, Long courseId) {
        College college = collegeRepository.findById(collegeId)
                .orElseThrow(() -> new RuntimeException("College not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        cc.setCollege(college);
        cc.setCourse(course);
        return collegeCourseRepository.save(cc);
    }

    public List<CollegeCourse> findByCollege(Long collegeId) {
        return collegeCourseRepository.findByCollegeId(collegeId);
    }

    public List<CollegeCourse> findByCourse(Long courseId) {
        return collegeCourseRepository.findByCourseId(courseId);
    }

    public void delete(Long id) {
        collegeCourseRepository.deleteById(id);
    }
}
