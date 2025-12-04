package com.councilgrad.councilgrad.dto;

import com.councilgrad.councilgrad.model.enums.ProgramType;
import lombok.Data;

@Data
public class CreateProgramRequest {
    private ProgramType name;
    private String description;
}
