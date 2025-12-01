package com.councilgrad.councilgrad.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollegeCourseDto {

    private Long collegeCourseId;
    private Long courseId;
    private String programName;
    private String courseName;
    private Double feePerYear;
    private Integer durationYears;
}
