package com.councilgrad.councilgrad.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "college_programs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollegeProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id", nullable = false)
    @ToString.Exclude
    private College college;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    @ToString.Exclude
    private Program program;
}
