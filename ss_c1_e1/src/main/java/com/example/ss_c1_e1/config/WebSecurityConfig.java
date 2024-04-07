package com.example.ss_c1_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig
{
    @Bean
    public UserDetailsService userDetailsService()
    {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // if we create our own user management, spring security doesn't provide us randomly generated password anymore

        UserDetails userDetails = User
                .withUsername("bill")
                .password("12345")
                .authorities("read")
                .build();

        manager.createUser(userDetails);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}

