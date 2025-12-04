package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.model.CollegeSpecialization;
import com.councilgrad.councilgrad.model.College;
import com.councilgrad.councilgrad.model.Specialization;
import com.councilgrad.councilgrad.repository.CollegeSpecializationRepository;
import com.councilgrad.councilgrad.repository.CollegeRepository;
import com.councilgrad.councilgrad.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeSpecializationService {

    private final CollegeSpecializationRepository repo;
    private final CollegeRepository collegeRepository;
    private final SpecializationRepository specializationRepository;

    public CollegeSpecializationService(CollegeSpecializationRepository repo,
                                        CollegeRepository collegeRepository,
                                        SpecializationRepository specializationRepository) {
        this.repo = repo;
        this.collegeRepository = collegeRepository;
        this.specializationRepository = specializationRepository;
    }

    public CollegeSpecialization create(CollegeSpecialization cs, Long collegeId, Long specializationId) {
        College college = collegeRepository.findById(collegeId)
                .orElseThrow(() -> new RuntimeException("College not found"));
        Specialization spec = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new RuntimeException("Specialization not found"));
        cs.setCollege(college);
        cs.setSpecialization(spec);
        return repo.save(cs);
    }

    public List<CollegeSpecialization> findByCollege(Long collegeId) {
        return repo.findByCollegeId(collegeId);
    }

    public List<CollegeSpecialization> findBySpecialization(Long specializationId) {
        return repo.findBySpecializationId(specializationId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
