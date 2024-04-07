package com.example.ss_c3_e1.config.security.filter;

import com.example.ss_c3_e1.config.security.authentication.CustomAuthentication;
import com.example.ss_c3_e1.config.security.manager.CustomAuthenticationManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter
{
    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException
    {
        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain
        String key = String.valueOf(request.getHeader("key"));
        log.info("key = {}", key);

        CustomAuthentication ca = new CustomAuthentication(false, key);

        Authentication a = customAuthenticationManager.authenticate(ca);

        if(a.isAuthenticated())
        {
            SecurityContextHolder.getContext().setAuthentication(a);

            filterChain.doFilter(request, response); // only when authentication worked
        }


    }
}
