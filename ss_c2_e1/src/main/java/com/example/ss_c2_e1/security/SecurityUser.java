package com.example.ss_c2_e1.security;

import com.example.ss_c2_e1.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails
{
    private final User user;

    @Override
    public String getUsername()
    {
        return user.getName();
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return user.getAuthorities()
                .stream()
                .map(SecurityAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
