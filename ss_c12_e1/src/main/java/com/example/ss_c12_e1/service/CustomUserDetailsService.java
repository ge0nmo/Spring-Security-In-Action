package com.example.ss_c12_e1.service;

import com.example.ss_c12_e1.entity.User;
import com.example.ss_c12_e1.model.SecurityUserDetails;
import com.example.ss_c12_e1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        return new SecurityUserDetails(user);
    }
}
