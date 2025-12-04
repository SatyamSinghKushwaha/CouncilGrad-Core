package com.councilgrad.councilgrad.dto;

import com.councilgrad.councilgrad.model.enums.SpecializationType;
import lombok.Data;

@Data
public class CreateSpecializationRequest {
    private Long courseId;
    private SpecializationType name;
}
