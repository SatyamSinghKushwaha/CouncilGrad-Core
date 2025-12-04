package com.councilgrad.councilgrad.controller;

import com.councilgrad.councilgrad.dto.CreateProgramRequest;
import com.councilgrad.councilgrad.model.Program;
import com.councilgrad.councilgrad.service.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService service;

    public ProgramController(ProgramService service) {
        this.service = service;
    }

    @GetMapping
    public List<Program> list() {
        return service.getAllPrograms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Program> get(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Program create(@RequestBody CreateProgramRequest req) {
        Program p = Program.builder().name(req.getName()).description(req.getDescription()).build();
        return service.create(p);
    }

    @PutMapping("/{id}")
    public Program update(@PathVariable Long id, @RequestBody CreateProgramRequest req) {
        Program p = Program.builder().name(req.getName()).description(req.getDescription()).build();
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
