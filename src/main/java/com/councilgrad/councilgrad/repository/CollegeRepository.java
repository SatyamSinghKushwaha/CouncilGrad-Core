package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
}
