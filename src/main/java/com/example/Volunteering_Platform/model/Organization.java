package com.example.Volunteering_Platform.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String website;
    private String location;
    private String email;
    private String phoneNumber;
    private String password;
    
}
