package com.councilgrad.councilgrad.dto;

import com.councilgrad.councilgrad.model.enums.CourseType;
import com.councilgrad.councilgrad.model.enums.ProgramType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollegeCourseDto {
    private Long collegeCourseId;
    private Long courseId;
    private ProgramType programName;
    private CourseType courseName;
    private Double feePerYear;
    private Integer durationYears;
}
