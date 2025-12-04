package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.Program;
import com.councilgrad.councilgrad.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Optional<Program> getById(Long id) {
        return programRepository.findById(id);
    }

    public Program create(Program p) {
        return programRepository.save(p);
    }

    public Program update(Long id, Program incoming) {
        return programRepository.findById(id).map(existing -> {
            existing.setName(incoming.getName());
            existing.setDescription(incoming.getDescription());
            return programRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Program not found"));
    }

    public void delete(Long id) {
        programRepository.deleteById(id);
    }
}
