package com.councilGrad.CouncilGrad.service;

import com.councilGrad.CouncilGrad.model.College;
import com.councilGrad.CouncilGrad.model.Student;
import com.councilGrad.CouncilGrad.repository.CollegeRepository;
import org.springframework.data.jpa.domain.Specification;
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
    Specification<College> spec = Specification.where(null);

    if (student.getDesiredCourse() != null) {
        spec = spec.and((root, query, cb) ->
                cb.equal(cb.lower(root.get("course")), student.getDesiredCourse().toLowerCase()));
    }
    if (student.getTenthMarks() != null) {
        spec = spec.and((root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("minTenthMarks"), student.getTenthMarks()));
    }
    if (student.getTwelfthMarks() != null) {
        spec = spec.and((root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("minTwelfthMarks"), student.getTwelfthMarks()));
    }
    if (student.getBudget() != null) {
        spec = spec.and((root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("maxBudget"), student.getBudget()));
    }

    return collegeRepository.findAll(spec);
}

}


