package com.example.ss_c4_e1.config.manager;

import com.example.ss_c4_e1.config.provider.ApiKeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager
{
    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        ApiKeyProvider provider = new ApiKeyProvider(key);

        if(provider.supports(authentication.getClass()))
        {
            provider.authenticate(authentication);
        }

        return authentication;
    }
}
