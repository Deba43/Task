package com.example.Volunteering_Platform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "organization", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "email"),
           @UniqueConstraint(columnNames = "phoneNumber"),
           @UniqueConstraint(columnNames = "website")
       })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Provide value for name")
    @Column(length=50)
    private String name;

    @NotEmpty(message = "Provide value for website")
    @Pattern(regexp = "^(http(s)?://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(/.*)?$", 
             message = "Invalid website URL format")
    @Column(nullable = false)
    private String website;

    @NotEmpty(message = "Provide value for location")
    @Column(length = 100, nullable = false)
    private String location;

    @NotEmpty(message = "Provide value for email")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Provide value for phoneNumber")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number must be 10 digits")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @NotEmpty(message = "Provide value for password")
    @Size(min = 8, message = "Your Password must be at least 8 character long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$", message = "Password must contain at least one letter, one number, and one special character")
    @Column(nullable = false)
    private String password;
    
}
