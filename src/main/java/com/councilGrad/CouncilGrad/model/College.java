package com.councilGrad.CouncilGrad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "college_eligibility")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class College {
    public College() {
    }

    public College(Long id, String collegeName, Double minMarks10, Double minMarks12, String category, String course, Integer fees) {
        this.id = id;
        this.collegeName = collegeName;
        this.minMarks10 = minMarks10;
        this.minMarks12 = minMarks12;
        this.category = category;
        this.course = course;
        this.fees = fees;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String collegeName;

    public Double getMinMarks10() {
        return minMarks10;
    }

    public void setMinMarks10(Double minMarks10) {
        this.minMarks10 = minMarks10;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Double getMinMarks12() {
        return minMarks12;
    }

    public void setMinMarks12(Double minMarks12) {
        this.minMarks12 = minMarks12;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    private Double minMarks10;
    private Double minMarks12;
    private String category;
    private String course;
    private Integer fees;
}

