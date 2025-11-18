package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.College;
import com.councilgrad.councilgrad.repository.CollegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;

    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public Optional<College> getCollegeById(Long id) {
        return collegeRepository.findById(id);
    }

    public College addCollege(College college) {
        return collegeRepository.save(college);
    }

    public College updateCollege(Long id, College updatedCollege) {
        return collegeRepository.findById(id)
                .map(c -> {
                    c.setName(updatedCollege.getName());
                    c.setLocation(updatedCollege.getLocation());
                    c.setCourse(updatedCollege.getCourse());
                    c.setMinTenthMarks(updatedCollege.getMinTenthMarks());
                    c.setMinTwelfthMarks(updatedCollege.getMinTwelfthMarks());
                    c.setMaxBudget(updatedCollege.getMaxBudget());
                    return collegeRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("College not found"));
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }
}

