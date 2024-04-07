package com.example.ss_c4_e1.config.filter;

import com.example.ss_c4_e1.config.authentication.ApiKeyAuthentication;
import com.example.ss_c4_e1.config.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter
{
    private final String key;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException
    {

        CustomAuthenticationManager manager = new CustomAuthenticationManager(key);

        String requestKey = request.getHeader("x-api-key");

        if(requestKey == null || "null".equals(requestKey)){
            filterChain.doFilter(request, response);
        }

        ApiKeyAuthentication auth = new ApiKeyAuthentication(requestKey);

        try{
            Authentication a = manager.authenticate(auth);
            if(a.isAuthenticated())
            {
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            }

        } catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
