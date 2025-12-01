package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProgramId(Long programId);

    Optional<Course> findByProgramIdAndNameIgnoreCase(Long programId, String name);

    List<Course> findByNameIgnoreCase(String name);
}
