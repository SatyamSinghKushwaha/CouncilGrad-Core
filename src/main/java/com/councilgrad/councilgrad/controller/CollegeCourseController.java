package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CreateCollegeCourseRequest;
import com.councilgrad.councilgrad.model.CollegeCourse;
import com.councilgrad.councilgrad.service.CollegeCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/college-courses")
public class CollegeCourseController {

    private final CollegeCourseService service;

    public CollegeCourseController(CollegeCourseService service) {
        this.service = service;
    }

    @PostMapping
    public CollegeCourse create(@RequestBody CreateCollegeCourseRequest req) {
        CollegeCourse cc = CollegeCourse.builder()
                .feePerYear(req.getFeePerYear())
                .durationYears(req.getDurationYears())
                .seats(req.getSeats())
                .build();
        return service.create(cc, req.getCollegeId(), req.getCourseId());
    }

    @GetMapping("/by-college/{collegeId}")
    public List<CollegeCourse> byCollege(@PathVariable Long collegeId) {
        return service.findByCollege(collegeId);
    }

    @GetMapping("/by-course/{courseId}")
    public List<CollegeCourse> byCourse(@PathVariable Long courseId) {
        return service.findByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
