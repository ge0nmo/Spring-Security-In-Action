package com.example.ss_c2_e1.security;

import com.example.ss_c2_e1.entity.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityAuthority implements GrantedAuthority
{
    private final Authority authority;

    @Override
    public String getAuthority()
    {
        return authority.getName();
    }
}
