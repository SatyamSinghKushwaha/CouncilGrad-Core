package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.CollegeProgram;
import com.councilgrad.councilgrad.model.College;
import com.councilgrad.councilgrad.model.Program;
import com.councilgrad.councilgrad.repository.CollegeProgramRepository;
import com.councilgrad.councilgrad.repository.CollegeRepository;
import com.councilgrad.councilgrad.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeProgramService {

    private final CollegeProgramRepository repo;
    private final CollegeRepository collegeRepository;
    private final ProgramRepository programRepository;

    public CollegeProgramService(CollegeProgramRepository repo,
                                 CollegeRepository collegeRepository,
                                 ProgramRepository programRepository) {
        this.repo = repo;
        this.collegeRepository = collegeRepository;
        this.programRepository = programRepository;
    }

    public CollegeProgram create(CollegeProgram cp, Long collegeId, Long programId) {
        College college = collegeRepository.findById(collegeId)
                .orElseThrow(() -> new RuntimeException("College not found"));
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));
        cp.setCollege(college);
        cp.setProgram(program);
        return repo.save(cp);
    }

    public List<CollegeProgram> findByCollege(Long collegeId) {
        return repo.findByCollegeId(collegeId);
    }

    public List<CollegeProgram> findByProgram(Long programId) {
        return repo.findByProgramId(programId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
