package com.councilgrad.councilgrad.dto;

import com.councilgrad.councilgrad.model.enums.CourseType;
import lombok.Data;

@Data
public class CreateCourseRequest {
    private Long programId;
    private CourseType name;
    private String level; // optional: UG/PG etc
}
