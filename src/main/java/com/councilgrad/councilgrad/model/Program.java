package com.councilgrad.councilgrad.model;

import com.councilgrad.councilgrad.model.enums.ProgramType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // use enum, stored as VARCHAR in "name" column
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private ProgramType name;

    private String description;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @Builder.Default
    private List<Course> courses = new ArrayList<>();
}
