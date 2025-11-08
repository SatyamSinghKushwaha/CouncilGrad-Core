package com.councilGrad.CouncilGrad.controller;

import com.councilGrad.CouncilGrad.model.Student;
import com.councilGrad.CouncilGrad.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepo;

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student saved = studentRepo.save(student);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepo.findAll());
    }
}

