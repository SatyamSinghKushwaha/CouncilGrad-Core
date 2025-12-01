package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CollegeCourseDto;
import com.councilgrad.councilgrad.model.College;
import com.councilgrad.councilgrad.service.CollegeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

    private final CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }

    @GetMapping("/{id}")
    public College getCollegeById(@PathVariable Long id) {
        return collegeService.getCollegeById(id)
                .orElseThrow(() -> new RuntimeException("College not found"));
    }

    @PostMapping
    public College addCollege(@RequestBody College college) {
        return collegeService.addCollege(college);
    }

    @PutMapping("/{id}")
    public College updateCollege(@PathVariable Long id, @RequestBody College college) {
        return collegeService.updateCollege(id, college);
    }

    @DeleteMapping("/{id}")
    public void deleteCollege(@PathVariable Long id) {
        collegeService.deleteCollege(id);
    }

    // NEW: all courses offered by a college
    @GetMapping("/{id}/courses")
    public List<CollegeCourseDto> getCoursesOfCollege(@PathVariable Long id) {
        return collegeService.getCoursesForCollege(id);
    }
}
