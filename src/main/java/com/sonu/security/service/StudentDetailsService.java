package com.sonu.security.service;

import com.sonu.security.model.StudentDetails;
import com.sonu.security.repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentDetailsService implements UserDetailsService {

    @Autowired
    StudentDetailsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<StudentDetails> byId = repository.findById(username);
        if (!byId.isPresent()) {
            throw new UsernameNotFoundException("User Not found");
        }
        StudentDetails studentDetails = byId.get();
        return new User(studentDetails.getUsername(),
                getPasswordEncoder().encode(studentDetails.getPassword()),
                studentDetails.isEnabled(),
                studentDetails.isAccountNonExpired(),
                studentDetails.isCredentialsNonExpired(),
                studentDetails.isAccountNonLocked(),
                studentDetails.getAuthorities());
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
