package com.example.Volunteering_Platform.model;

import java.time.*;

import com.example.Volunteering_Platform.enums.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    private Status status; // Enum for pending, in progress, completed

    @Enumerated(EnumType.STRING)
    private Priority priority; // Enum for low, medium, high

    @Enumerated(EnumType.STRING)
    private Category category; // Enum for education, health, environment

    private LocalDate eventDate;

    private LocalDate createdAt;

    private LocalDate endAt;

    private LocalDate updatedAt;

    // @ManyToOne
    // @JoinColumn(name = "org_id")
    // private Organization org;
}
