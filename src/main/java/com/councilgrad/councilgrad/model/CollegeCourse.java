package com.councilgrad.councilgrad.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "college_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollegeCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id", nullable = false)
    @ToString.Exclude
    private College college;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @ToString.Exclude
    private Course course;

    private Double feePerYear;

    private Integer durationYears;

    private Integer seats;

    @OneToMany(mappedBy = "collegeCourse", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EligibilityRule> eligibilityRules;
}
