package com.councilGrad.CouncilGrad.controller;

import com.councilGrad.CouncilGrad.model.College;
import com.councilGrad.CouncilGrad.model.Student;
import com.councilGrad.CouncilGrad.service.StudentService;
import com.councilGrad.CouncilGrad.service.EligibilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final EligibilityService eligibilityService;

    public StudentController(StudentService studentService, EligibilityService eligibilityService) {
        this.studentService = studentService;
        this.eligibilityService = eligibilityService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/eligible-colleges")
    public ResponseEntity<List<College>> getEligibleColleges(@RequestBody Student student) {
        List<College> colleges = eligibilityService.findEligibleColleges(student);
        return ResponseEntity.ok(colleges);
    }
}

