package com.example.Volunteering_Platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Volunteering_Platform.model.Organization;
import com.example.Volunteering_Platform.repository.OrganizationRepository;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public boolean existsById(Long org_id) {
        return organizationRepository.existsById(org_id);
    }

    public void saveOrganization(Organization org) {
        try {
            organizationRepository.save(org);

        } catch (Exception e) {
            System.out.println("Error saving organization: " + e.getMessage());
            throw new RuntimeException("Could not create organization: " + e.getMessage());
        }
    }
}
