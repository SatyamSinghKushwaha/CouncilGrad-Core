package com.councilgrad.councilgrad.dto;

import lombok.Data;

@Data
public class CreateCollegeSpecializationRequest {
    private Long collegeId;
    private Long specializationId;
}
