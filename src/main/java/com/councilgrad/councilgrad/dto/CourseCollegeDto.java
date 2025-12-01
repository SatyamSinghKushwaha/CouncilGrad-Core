package com.councilgrad.councilgrad.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCollegeDto {

    private Long collegeCourseId;
    private Long collegeId;
    private String collegeName;
    private String location;
    private Double feePerYear;
    private Integer durationYears;
}
