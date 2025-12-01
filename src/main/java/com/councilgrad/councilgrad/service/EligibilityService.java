package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.*;
import com.councilgrad.councilgrad.repository.CollegeCourseRepository;
import com.councilgrad.councilgrad.repository.CourseRepository;
import com.councilgrad.councilgrad.repository.EligibilityRuleRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EligibilityService {

    private final CourseRepository courseRepository;
    private final CollegeCourseRepository collegeCourseRepository;
    private final EligibilityRuleRepository eligibilityRuleRepository;

    public EligibilityService(CourseRepository courseRepository,
                              CollegeCourseRepository collegeCourseRepository,
                              EligibilityRuleRepository eligibilityRuleRepository) {
        this.courseRepository = courseRepository;
        this.collegeCourseRepository = collegeCourseRepository;
        this.eligibilityRuleRepository = eligibilityRuleRepository;
    }

    public List<College> findEligibleColleges(Student student) {

        // 1) Find courses matching desiredCourse (by name, case-insensitive)
        List<Course> matchingCourses = courseRepository
                .findByNameIgnoreCase(student.getDesiredCourse());

        if (matchingCourses.isEmpty()) {
            return List.of();
        }

        Set<Long> courseIds = matchingCourses.stream()
                .map(Course::getId)
                .collect(Collectors.toSet());

        // 2) Get all college_course entries for these courses
        List<CollegeCourse> ccList = collegeCourseRepository.findAll()
                .stream()
                .filter(cc -> courseIds.contains(cc.getCourse().getId()))
                .collect(Collectors.toList());

        if (ccList.isEmpty()) {
            return List.of();
        }

        // 3) For each college_course, check eligibility
        Double tenth = student.getTenthMarks();
        Double twelfth = student.getTwelfthMarks();
        Double budget = student.getBudget();

        Map<Long, College> eligibleColleges = new LinkedHashMap<>();

        for (CollegeCourse cc : ccList) {
            // basic budget filter (optional if feePerYear null)
            if (budget != null && cc.getFeePerYear() != null &&
                    cc.getFeePerYear() > budget) {
                continue;
            }

            List<EligibilityRule> rules =
                    eligibilityRuleRepository.findByCollegeCourseId(cc.getId());

            if (rules.isEmpty()) {
                // If no specific rule, we can still consider it if fee matches
                eligibleColleges.putIfAbsent(cc.getCollege().getId(), cc.getCollege());
                continue;
            }

            for (EligibilityRule rule : rules) {
                boolean ok = true;

                if (tenth != null && rule.getMinTenthMarks() != null &&
                        tenth < rule.getMinTenthMarks()) {
                    ok = false;
                }

                if (twelfth != null && rule.getMinTwelfthMarks() != null &&
                        twelfth < rule.getMinTwelfthMarks()) {
                    ok = false;
                }

                if (budget != null) {
                    Double effectiveMax =
                            rule.getMaxBudgetPerYear() != null ? rule.getMaxBudgetPerYear()
                                    : cc.getFeePerYear();
                    if (effectiveMax != null && effectiveMax > budget) {
                        ok = false;
                    }
                }

                if (ok) {
                    eligibleColleges.putIfAbsent(cc.getCollege().getId(), cc.getCollege());
                    break;
                }
            }
        }

        return new ArrayList<>(eligibleColleges.values());
    }
}
