package com.example.ss_c4_e1.config;

import com.example.ss_c4_e1.config.filter.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig
{
    @Value("${the.secret}")
    private String key;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http
                .httpBasic(Customizer.withDefaults())
                //.authenticationManager() or by adding a bean of type AuthenticationManager
                //.authenticationProvider() it doesn't override the AuthenticationProvider. It adds one more to the collection
                .addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request ->
                        request
                                .anyRequest().authenticated())
                .build();
    }

}
