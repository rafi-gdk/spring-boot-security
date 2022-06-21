package com.sonu.security.repository;

import com.sonu.security.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, String> {
}
