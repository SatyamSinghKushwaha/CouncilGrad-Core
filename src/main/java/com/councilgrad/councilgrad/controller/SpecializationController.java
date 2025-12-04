package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CreateSpecializationRequest;
import com.councilgrad.councilgrad.model.Specialization;
import com.councilgrad.councilgrad.service.SpecializationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    private final SpecializationService service;

    public SpecializationController(SpecializationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Specialization> list(@RequestParam(value = "courseId", required = false) Long courseId) {
        if (courseId != null) return service.getByCourse(courseId);
        return service.getByCourse(null); // will throw - prefer client to send courseId
    }

    @GetMapping("/course/{courseId}")
    public List<Specialization> byCourse(@PathVariable Long courseId) {
        return service.getByCourse(courseId);
    }

    @PostMapping
    public Specialization create(@RequestBody CreateSpecializationRequest req) {
        Specialization s = Specialization.builder().name(req.getName()).build();
        return service.create(s, req.getCourseId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
