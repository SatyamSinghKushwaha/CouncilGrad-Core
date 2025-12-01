package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    List<Specialization> findByCourseId(Long courseId);
}
