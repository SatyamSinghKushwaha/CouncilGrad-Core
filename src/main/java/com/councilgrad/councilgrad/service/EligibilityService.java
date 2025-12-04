package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.*;
import com.councilgrad.councilgrad.model.enums.CourseType;
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

        // 1️⃣ Convert UI string → ENUM
        CourseType desiredType;
        try {
            desiredType = CourseType.valueOf(student.getDesiredCourse());
        } catch (Exception e) {
            return List.of(); // Invalid course sent
        }

        // 2️⃣ Now fetch courses by enum name (no ignore case needed)
        List<Course> matchingCourses = courseRepository.findByName(desiredType);

        if (matchingCourses.isEmpty()) {
            return List.of();
        }

        Set<Long> courseIds = matchingCourses.stream()
                .map(Course::getId)
                .collect(Collectors.toSet());

        // 3️⃣ Fetch college_course rows for these courseIds
        List<CollegeCourse> ccList = collegeCourseRepository.findAll()
                .stream()
                .filter(cc -> courseIds.contains(cc.getCourse().getId()))
                .collect(Collectors.toList());

        if (ccList.isEmpty()) {
            return List.of();
        }

        Double tenth = student.getTenthMarks();
        Double twelfth = student.getTwelfthMarks();
        Double budget = student.getBudget();

        Map<Long, College> eligible = new LinkedHashMap<>();

        for (CollegeCourse cc : ccList) {

            if (budget != null && cc.getFeePerYear() != null &&
                    cc.getFeePerYear() > budget) {
                continue;
            }

            List<EligibilityRule> rules =
                    eligibilityRuleRepository.findByCollegeCourseId(cc.getId());

            if (rules.isEmpty()) {
                eligible.putIfAbsent(cc.getCollege().getId(), cc.getCollege());
                continue;
            }

            for (EligibilityRule rule : rules) {
                boolean ok = true;

                if (tenth != null && rule.getMinTenthMarks() != null &&
                        tenth < rule.getMinTenthMarks()) ok = false;

                if (twelfth != null && rule.getMinTwelfthMarks() != null &&
                        twelfth < rule.getMinTwelfthMarks()) ok = false;

                if (budget != null) {
                    Double effectiveMax = rule.getMaxBudgetPerYear() != null
                            ? rule.getMaxBudgetPerYear()
                            : cc.getFeePerYear();

                    if (effectiveMax != null && effectiveMax > budget) ok = false;
                }

                if (ok) {
                    eligible.putIfAbsent(cc.getCollege().getId(), cc.getCollege());
                    break;
                }
            }
        }

        return new ArrayList<>(eligible.values());
    }

}
