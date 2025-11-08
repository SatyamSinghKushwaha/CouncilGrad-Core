package com.councilGrad.CouncilGrad.repository;

import com.councilGrad.CouncilGrad.model.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeRepository extends JpaRepository<College, Long> {
    List<College> findByCourse(String course);
}

