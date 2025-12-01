package com.councilgrad.councilgrad.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eligibility_rules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_course_id", nullable = false)
    @ToString.Exclude
    private CollegeCourse collegeCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id")
    @ToString.Exclude
    private Specialization specialization;  // optional

    private Double minTenthMarks;
    private Double minTwelfthMarks;

    // Optional max budget override; usually you'll compare Student.budget with feePerYear
    private Double maxBudgetPerYear;

    private String requiredExam;
    private Double minExamScore;
}
