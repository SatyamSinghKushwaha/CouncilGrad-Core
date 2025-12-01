package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CourseCollegeDto;
import com.councilgrad.councilgrad.dto.SpecializationDto;
import com.councilgrad.councilgrad.model.Course;
import com.councilgrad.councilgrad.service.CollegeService;
import com.councilgrad.councilgrad.service.CourseService;
import com.councilgrad.councilgrad.service.SpecializationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CollegeService collegeService;
    private final SpecializationService specializationService;

    public CourseController(CourseService courseService,
                            CollegeService collegeService,
                            SpecializationService specializationService) {
        this.courseService = courseService;
        this.collegeService = collegeService;
        this.specializationService = specializationService;
    }

    @GetMapping
    public List<Course> getCourses(@RequestParam(value = "programId", required = false) Long programId) {
        if (programId != null) {
            return courseService.getCoursesByProgram(programId);
        }
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}/colleges")
    public List<CourseCollegeDto> getCollegesForCourse(@PathVariable Long courseId) {
        return collegeService.getCollegesForCourse(courseId);
    }

    @GetMapping("/{courseId}/specializations")
    public List<SpecializationDto> getSpecializationsForCourse(@PathVariable Long courseId) {
        return specializationService.getByCourse(courseId);
    }
}
