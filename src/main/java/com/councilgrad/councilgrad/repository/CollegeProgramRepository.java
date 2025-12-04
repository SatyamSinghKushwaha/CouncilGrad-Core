package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.College;
import com.councilgrad.councilgrad.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import com.councilgrad.councilgrad.model.CollegeProgram;

import java.util.List;

public interface CollegeProgramRepository extends JpaRepository<CollegeProgram, Long> {
    List<CollegeProgram> findByCollegeId(Long collegeId);
    List<CollegeProgram> findByProgramId(Long programId);
}
