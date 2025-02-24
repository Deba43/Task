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
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Organizations/**").permitAll()
                        .requestMatchers("/addTask/").permitAll()
                        .requestMatchers("/getAllTasks/").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
