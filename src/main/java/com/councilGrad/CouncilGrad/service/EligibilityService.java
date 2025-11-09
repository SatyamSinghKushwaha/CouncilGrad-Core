package com.councilGrad.CouncilGrad.service;

import com.councilGrad.CouncilGrad.model.College;
import com.councilGrad.CouncilGrad.model.Student;
import com.councilGrad.CouncilGrad.repository.CollegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EligibilityService {

    private final CollegeRepository collegeRepository;

    public EligibilityService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public List<College> findEligibleColleges(Student student) {
        return collegeRepository.findAll()
                .stream()
                .filter(c ->
                        c.getCourse().equalsIgnoreCase(student.getDesiredCourse()) &&
                                student.getTenthMarks() >= c.getMinTenthMarks() &&
                                student.getTwelfthMarks() >= c.getMinTwelfthMarks() &&
                                student.getBudget() <= c.getMaxBudget()
                )
                .collect(Collectors.toList());
    }
}


