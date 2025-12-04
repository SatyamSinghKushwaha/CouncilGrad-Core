package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CreateCollegeProgramRequest;
import com.councilgrad.councilgrad.model.CollegeProgram;
import com.councilgrad.councilgrad.service.CollegeProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/college-programs")
public class CollegeProgramController {

    private final CollegeProgramService service;

    public CollegeProgramController(CollegeProgramService service) {
        this.service = service;
    }

    @PostMapping
    public CollegeProgram create(@RequestBody CreateCollegeProgramRequest req) {
        CollegeProgram cp = CollegeProgram.builder().build();
        return service.create(cp, req.getCollegeId(), req.getProgramId());
    }

    @GetMapping("/by-college/{collegeId}")
    public List<CollegeProgram> byCollege(@PathVariable Long collegeId) {
        return service.findByCollege(collegeId);
    }

    @GetMapping("/by-program/{programId}")
    public List<CollegeProgram> byProgram(@PathVariable Long programId) {
        return service.findByProgram(programId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
