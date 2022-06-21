package com.sonu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class StudentDetailsConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .and()
                .cors()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/non**").permitAll()
                .antMatchers("/save-student-details").permitAll()
                .antMatchers("/h2-console/**").hasAuthority("ADMIN")
                .and().csrf().ignoringAntMatchers("/h2-console/**", "/save-student-details")
                .and().headers().frameOptions().disable()
                .and().authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        return http.build();
    }
}
