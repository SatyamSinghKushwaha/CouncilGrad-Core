package com.councilgrad.councilgrad.repository;

import com.councilgrad.councilgrad.model.EligibilityRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EligibilityRuleRepository extends JpaRepository<EligibilityRule, Long> {
    List<EligibilityRule> findByCollegeCourseId(Long collegeCourseId);
}
