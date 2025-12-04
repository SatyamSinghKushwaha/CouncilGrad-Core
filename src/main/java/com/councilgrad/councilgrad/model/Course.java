package com.councilgrad.councilgrad.model;

import com.councilgrad.councilgrad.model.enums.CourseType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // enum in "name" column
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private CourseType name;

    // optional: UG/PG etc, keep as string for now
    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    @ToString.Exclude
    private Program program;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = false)
    @Builder.Default
    @ToString.Exclude
    private List<Specialization> specializations = new ArrayList<>();
}
