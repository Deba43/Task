package com.example.Volunteering_Platform.model;

import java.time.*;

import com.example.Volunteering_Platform.enums.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Provide value for title")
    @Column(length = 50, nullable = false)
    private String title;

    @NotEmpty(message = "Provide value for description")
    @Column(length = 255, nullable = false)
    private String description;

    @NotEmpty(message = "Provide value for location")
    @Column(length = 100, nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // Enum for pending, in progress, completed

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority; // Enum for low, medium, high

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category; // Enum for education, health, environment

    @NotEmpty(message = "Provide value for event date")
    @Column(nullable = false)
    @FutureOrPresent(message = "Event start date should be either current or future date")
    private LocalDate eventDate;

    @Column(nullable = false)
    @FutureOrPresent(message = "Event End date should be either current or future date")
    private LocalDate endDate;

    // @ManyToOne
    // @JoinColumn(name = "org_id")
    // private Organization org;

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (eventDate != null && endDate != null && endDate.isBefore(eventDate)) {
            throw new IllegalArgumentException("End date cannot be before event date.");
        }
    }
}
