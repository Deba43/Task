package com.example.Volunteering_Platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/addTask").permitAll()
                        .requestMatchers("/Organizations/**").permitAll()
                        .requestMatchers("/Organization/{org_id}/addTask").permitAll()
                        .requestMatchers("/viewAllTasks").permitAll()
                        .requestMatchers("/searchTasks").permitAll()
                        .requestMatchers("/getTaskByName/{title}").permitAll()
                        .requestMatchers("/getTaskByLocation/{location}").permitAll()
                        .requestMatchers("/getTaskById/{taskId}").permitAll()
                        .requestMatchers("/getTaskByCategory/{category}").permitAll()
                        .requestMatchers("/getTaskByDate/{eventDate}").permitAll()
                        .requestMatchers("/Organization/{org_id}/update/{taskId}").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
