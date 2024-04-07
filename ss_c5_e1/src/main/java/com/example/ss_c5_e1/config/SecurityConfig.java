package com.example.ss_c5_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request

                        //.anyRequest().permitAll()
                        //.anyRequest().denyAll()
                        //.anyRequest().hasAnyAuthority("read", "write")
                        //.anyRequest().hasAnyRole("ADMIN", "MANAGER")
                        .anyRequest().authenticated()) // endpoint level authorization
                .build();

        // matcher method + authorization rule
        // 1. which matcher methods should you use and how
        // 2. how to apply different authorization rules
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

        UserDetails userDetails1 = User
                .withUsername("bill")
                .password(passwordEncoder().encode("12345"))
                .roles("MANAGER") // equivalent with and authority named ROLE_MANAGER
                .build();

        UserDetails userDetails2 = User
                .withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();

        uds.createUser(userDetails1);
        uds.createUser(userDetails2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
