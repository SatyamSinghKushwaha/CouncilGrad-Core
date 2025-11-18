package com.councilgrad.councilgrad.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String course;
    private Double minTenthMarks;
    private Double minTwelfthMarks;
    private Double maxBudget;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(Double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public Double getMinTenthMarks() {
        return minTenthMarks;
    }

    public void setMinTenthMarks(Double minTenthMarks) {
        this.minTenthMarks = minTenthMarks;
    }

    public Double getMinTwelfthMarks() {
        return minTwelfthMarks;
    }

    public void setMinTwelfthMarks(Double minTwelfthMarks) {
        this.minTwelfthMarks = minTwelfthMarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

