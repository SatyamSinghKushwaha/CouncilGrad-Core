package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CreateCourseRequest;
import com.councilgrad.councilgrad.model.Course;
import com.councilgrad.councilgrad.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> list(@RequestParam(value = "programId", required = false) Long programId) {
        if (programId != null) return service.getByProgram(programId);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return service.getAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @PostMapping
    public Course create(@RequestBody CreateCourseRequest req) {
        Course c = Course.builder().name(req.getName()).level(req.getLevel()).build();
        return service.create(c, req.getProgramId());
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody CreateCourseRequest req) {
        Course c = Course.builder().name(req.getName()).level(req.getLevel()).build();
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
