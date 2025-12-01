package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.CollegeCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeCourseRepository extends JpaRepository<CollegeCourse, Long> {

    List<CollegeCourse> findByCollegeId(Long collegeId);

    List<CollegeCourse> findByCourseId(Long courseId);
}
