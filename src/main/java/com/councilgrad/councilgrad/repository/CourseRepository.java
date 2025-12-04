package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.Course;
import com.councilgrad.councilgrad.model.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProgramId(Long programId);
    Optional<Course> findByProgramIdAndName(Long programId, String name);
    List<Course> findByName(CourseType name);

}
