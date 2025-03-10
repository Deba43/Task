package com.example.Volunteering_Platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Volunteering_Platform.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    
} 