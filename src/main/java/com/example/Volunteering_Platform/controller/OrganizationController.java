package com.example.Volunteering_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Volunteering_Platform.model.Organization;
import com.example.Volunteering_Platform.service.OrganizationService;

@RestController
@RequestMapping("/Organizations")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping("/addOrganizations")
    public ResponseEntity<Organization> registerOrganization(@RequestBody @Validated Organization org) {
        try {
            organizationService.saveOrganization(org);
            return new ResponseEntity<>(org, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
