package com.sonu.security.controller;

import com.sonu.security.model.StudentDetails;
import com.sonu.security.repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class StudentDetailsController {

    @Autowired
    StudentDetailsRepository repository;

    @GetMapping("/non-secure-api")
    public String getNonSecureURL() {
        return "Hello Non Secured API";
    }

    @GetMapping("/secure-api")
    public String getSecureURL(Principal principal) {
        return "Hello Secured API & User is : " + principal.getName();
    }

    @PostMapping("/save-student-details")
    public StudentDetails saveStudentDetails(@RequestBody StudentDetails studentDetails) {
        return repository.save(studentDetails);
    }
}
