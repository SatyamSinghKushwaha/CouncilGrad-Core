package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.dto.SpecializationDto;
import com.councilgrad.councilgrad.model.Specialization;
import com.councilgrad.councilgrad.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public List<SpecializationDto> getByCourse(Long courseId) {
        return specializationRepository.findByCourseId(courseId)
                .stream()
                .map(s -> new SpecializationDto(s.getId(), s.getName()))
                .collect(Collectors.toList());
    }
}
