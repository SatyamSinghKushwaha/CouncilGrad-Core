package com.councilGrad.CouncilGrad.repository;

import com.councilGrad.CouncilGrad.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

