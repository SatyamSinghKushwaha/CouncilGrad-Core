package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.Program;
import com.councilgrad.councilgrad.model.enums.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<Program> findByName(ProgramType name);
}
