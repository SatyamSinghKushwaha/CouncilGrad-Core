package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CreateCollegeSpecializationRequest;
import com.councilgrad.councilgrad.model.CollegeSpecialization;
import com.councilgrad.councilgrad.service.CollegeSpecializationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/college-specializations")
public class CollegeSpecializationController {

    private final CollegeSpecializationService service;

    public CollegeSpecializationController(CollegeSpecializationService service) {
        this.service = service;
    }

    @PostMapping
    public CollegeSpecialization create(@RequestBody CreateCollegeSpecializationRequest req) {
        CollegeSpecialization cs = CollegeSpecialization.builder().build();
        return service.create(cs, req.getCollegeId(), req.getSpecializationId());
    }

    @GetMapping("/by-college/{collegeId}")
    public List<CollegeSpecialization> byCollege(@PathVariable Long collegeId) {
        return service.findByCollege(collegeId);
    }

    @GetMapping("/by-specialization/{specializationId}")
    public List<CollegeSpecialization> bySpecialization(@PathVariable Long specializationId) {
        return service.findBySpecialization(specializationId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
