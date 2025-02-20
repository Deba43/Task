package com.example.Volunteering_Platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Volunteering_Platform.model.Organization;
import com.example.Volunteering_Platform.repository.OrganizationRepository;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public Optional<Organization> findOrganizationById(Long id) {
        return organizationRepository.findById(id);
    }

    public void saveOrganization(Organization org) {
        try {
            
            org.setPassword(passwordEncoder.encode(org.getPassword()));

            
            organizationRepository.save(org); 

        } catch (Exception e) {
            System.out.println("Error saving organization: " + e.getMessage());
            throw new RuntimeException("Could not create organization: " + e.getMessage());
        }
    }
}
