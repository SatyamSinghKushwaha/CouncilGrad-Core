package com.councilgrad.councilgrad.dto;

import lombok.Data;

@Data
public class CreateCollegeProgramRequest {
    private Long collegeId;
    private Long programId;
}
