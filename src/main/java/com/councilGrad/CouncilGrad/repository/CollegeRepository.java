package com.councilGrad.CouncilGrad.repository;

import com.councilGrad.CouncilGrad.model.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CollegeRepository extends JpaRepository<College, Long>, JpaSpecificationExecutor<College> {}



