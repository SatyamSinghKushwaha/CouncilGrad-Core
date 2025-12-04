package com.councilgrad.councilgrad.dto;

import lombok.Data;

@Data
public class CreateCollegeCourseRequest {
    private Long collegeId;
    private Long courseId;
    private Double feePerYear;
    private Integer durationYears;
    private Integer seats;
}
