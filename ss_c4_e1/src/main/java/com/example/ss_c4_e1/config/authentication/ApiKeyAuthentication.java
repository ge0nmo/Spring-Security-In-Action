package com.example.ss_c4_e1.config.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class ApiKeyAuthentication implements Authentication
{
    private boolean authenticated;
    private final String key;

    @Override
    public boolean isAuthenticated()
    {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException
    {
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public Object getCredentials()
    {
        return null;
    }

    @Override
    public Object getDetails()
    {
        return null;
    }

    @Override
    public Object getPrincipal()
    {
        return null;
    }

    @Override
    public boolean implies(Subject subject)
    {
        return Authentication.super.implies(subject);
    }

    @Override
    public String getName()
    {
        return null;
    }
}
