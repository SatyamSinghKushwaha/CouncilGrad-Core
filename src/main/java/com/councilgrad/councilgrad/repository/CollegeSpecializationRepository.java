package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.CollegeSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeSpecializationRepository extends JpaRepository<CollegeSpecialization, Long> {
    List<CollegeSpecialization> findByCollegeId(Long collegeId);
    List<CollegeSpecialization> findBySpecializationId(Long specializationId);
}
