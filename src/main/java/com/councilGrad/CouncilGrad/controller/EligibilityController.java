package com.councilGrad.CouncilGrad.controller;

import com.councilGrad.CouncilGrad.model.Student;
import com.councilGrad.CouncilGrad.service.EligibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EligibilityController {

    private final EligibilityService eligibilityService;

    @PostMapping("/eligibility")
    public ResponseEntity<String> checkEligibility(@RequestBody Student student) {
        String aiResponse = eligibilityService.getEligibleColleges(student);
        return ResponseEntity.ok(aiResponse);
    }
}

