package com.councilGrad.CouncilGrad.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double tenthMarks;
    private Double twelfthMarks;
    private Double budget;
    private String desiredCourse;

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDesiredCourse() {
        return desiredCourse;
    }

    public void setDesiredCourse(String desiredCourse) {
        this.desiredCourse = desiredCourse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTenthMarks() {
        return tenthMarks;
    }

    public void setTenthMarks(Double tenthMarks) {
        this.tenthMarks = tenthMarks;
    }

    public Double getTwelfthMarks() {
        return twelfthMarks;
    }

    public void setTwelfthMarks(Double twelfthMarks) {
        this.twelfthMarks = twelfthMarks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "budget=" + budget +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", tenthMarks=" + tenthMarks +
                ", twelfthMarks=" + twelfthMarks +
                ", desiredCourse='" + desiredCourse + '\'' +
                '}';
    }
}

